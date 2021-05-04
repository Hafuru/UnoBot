package me.board;

import me.exceptions.DeckSizeException;
import me.imageManager.Image;
import me.players.Player;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Game extends ListenerAdapter
{
    protected static ArrayList<Player> players = new ArrayList<>();
    protected static GameState gameState = GameState.End;
    protected static Deck pile = new Deck();
    protected static Deck toPlay = new Deck();
    protected static Player isPlaying;
    protected static TextChannel textChannel;
    protected static Color color;
    protected static int toPick=0;
    protected static boolean waitingForColor = false;
    private static int count = 0;

    protected void setGameState(GameState gameState)
    {
        Game.gameState = gameState;
    }

    protected void setGame(GuildMessageReceivedEvent event) throws DeckSizeException
    {
        do
        {
            pile.getDeck().clear();
            setPile();
            pile.shuffle(6);
            setToPlay();
        }
        while (pile.getLast().getColor() == Color.Black);
        setTextChannel(event);
        isPlaying = players.get(0);
        setGameState(GameState.Waiting);
    }

    protected void setTextChannel(GuildMessageReceivedEvent event)
    {
        textChannel = event.getChannel();
    }

    protected void endGame()
    {
        players.clear();
        setGameState(GameState.End);
        pile.getDeck().clear();
        toPlay.getDeck().clear();
        count=0;
        toPick=0;
        waitingForColor=false;

    }

    protected void setToPlay() throws DeckSizeException
    {
        toPlay.pickFirst(pile);
    }

    protected void  setPile() throws DeckSizeException
    {
        for (Color c : Color.values())
        {
            if (c == Color.Black)
            {
                pile.add(new Card(Number.Joker, c));
                pile.add(new Card(Number.Joker, c));
                pile.add(new Card(Number.Joker, c));
                pile.add(new Card(Number.Joker, c));
                pile.add(new Card(Number.PlusFour, c));
                pile.add(new Card(Number.PlusFour, c));
                pile.add(new Card(Number.PlusFour, c));
                pile.add(new Card(Number.PlusFour, c));
            }

            else
            {
                for (Number n : Number.values())
                {
                    switch (n)
                    {
                        case Zero:
                            pile.add(new Card(n, c));
                            break;

                        case PlusFour:

                        case Joker:
                            break;

                        default:
                            pile.add(new Card(n, c));
                            pile.add(new Card(n, c));
                            break;
                    }
                }
            }
        }
    }

    protected void printPile()
    {
        for (Card card: pile.getDeck())
        {
            System.out.println("[" + card.getNumber()+", "+card.getColor()+"]");
        }
    }

    protected void checkPile() throws DeckSizeException
    {
        if (pile.getDeck().size()==0)
        {
            sendMessage("Pile is empty, refilling it...");

            for (int i = 0; i<toPlay.getDeck().size()-1; i++)
            {
                pile.pick(toPlay.getDeck().get(i), toPlay);
            }

            pile.shuffle(10);
            sendMessage("... refilling done !");
        }
    }

    protected void countUp()
    {
        if (count < players.size()-1)
            count++;
        else
            count=0;
    }

    protected void nextPlayer()
    {
        countUp();

        sendMessage(isPlaying.getName()+" has "+isPlaying.getHand().getDeck().size()+" cards");
        isPlaying = players.get(count);
        sendMessage(isPlaying.getAsMention()+" It's your turn !");
    }

    protected void sendMessage(String message)
    {
        textChannel.sendMessage(new EmbedBuilder().setDescription(message).build()).queue();
    }

    protected boolean canPlay(Card card)
    {
        if (toPick!=0)
        {
            return card.getNumber() == toPlay.getLast().getNumber();
        }

        else if (card.getColor() == color || card.getColor() == Color.Black)
        {
            return true;
        }

        return card.getNumber() == toPlay.getLast().getNumber();
    }

    protected boolean canPlay(ArrayList<Card> lCard)
    {
        if (canPlay(lCard.get(0)))
        {
            if (lCard.get(0).getColor() == Color.Black && lCard.size()>1)
            {
                return false;
            }

            for (Card card : lCard)
            {
                if (card.getNumber() != lCard.get(0).getNumber())
                {
                    return false;
                }
                switch (card.getNumber())
                {
                    case Reverse:
                            System.out.println("Changement de sens");
                            ArrayList<Player> tempPlayers = new ArrayList<>();
                            for (int j = count; j >= 0; j--)
                            {
                                tempPlayers.add(players.get(j));
                            }
                            for (int j = players.size() - 1; j > count; j--)
                            {
                                tempPlayers.add(players.get(j));
                            }
                            players = tempPlayers;
                            sendMessage("Changement de sens !");
                            break;

                    case PlusTwo:
                            toPick += 2;
                            System.out.println("+2");
                        break;

                    case Joker:
                            System.out.println("4 couleurs");
                            waitingForColor = true;
                            sendMessage(isPlaying.getAsMention() + " Choose a color with !color [Color name]" +
                                    "\n- Red" +
                                    "\n- Blue" +
                                    "\n- Green" +
                                    "\n- Yellow");
                        break;

                    case Blocked:
                            System.out.println("Interdit de jouer");
                            countUp();
                            if (players.get(count).getId().equals(isPlaying.getId()))
                            {
                                countUp();
                            }
                        break;

                    case PlusFour:
                            System.out.println("+4");
                            toPick += 4;
                            waitingForColor = true;
                            sendMessage(isPlaying.getAsMention() + " Choose a color with !color [Color name]" +
                                    "\n- Red" +
                                    "\n- Blue" +
                                    "\n- Green" +
                                    "\n- Yellow");
                        break;

                    default:
                        System.out.print("Case canPlay");
                        break;
                }

            }

            return true;
        }

        return false;
    }

    protected void showLast(boolean bColor)
    {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setImage(toPlay.getLast().getURL());
        textChannel.sendMessage(embedBuilder.build()).queue();
        if (bColor)
        {
            color = toPlay.getLast().getColor();
            sendMessage("Color is "+color);
        }
    }

    protected void showPlayed(ArrayList<Card> cards) throws IOException
    {
        File file = new File(Image.playedCard(cards));
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setDescription(isPlaying.getName()+" Played");
        textChannel.sendFile(file).embed(embedBuilder.build()).queue();

        if (file.delete())
        {
            System.out.print("File deleted successfully");
        }
        else
        {
            System.out.print("File has not been deleted");
        }
    }

    protected boolean hasWon()
    {
        if (isPlaying.getHand().getDeck().size() == 0)
        {
            sendMessage(isPlaying.getAsMention()+" Won the game !");
            setGameState(GameState.End);
            return true;
        }

        return false;
    }

    protected void nextTurn(boolean bColor) throws IOException
    {
        if (!waitingForColor)
        {
            isPlaying.sendHand();
            showLast(bColor);
            nextPlayer();
            System.out.println("Color = "+color);
        }
    }

    protected boolean isPlayer(User user)
    {
        for (Player player: players)
        {
            if (user.getId().equals(player.getId()))
                return true;
        }
        return false;
    }

    protected Player getPlayerByID(String id)
    {
        for(Player player : players)
        {

            if (player.getId().equals(id))
            {
                return player;
            }

        }

        return null;
    }
}
