package me.commands;

import me.board.Card;
import me.board.Game;
import me.exceptions.DeckSizeException;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.ArrayList;

public class Play extends Game implements ICommands
{
    private ArrayList<Integer> tIndex = new ArrayList<>();
    private ArrayList<Card> lCard = new ArrayList<>();

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event)
    {
        String[] messageReceived = event.getMessage().getContentRaw().split("\\s+");

        if (messageReceived[0].equalsIgnoreCase(command()))
        {
            if (event.getAuthor().getId().equals(isPlaying.getId()))
            {
                switch (gameState)
                {
                    case Playing:
                        lCard.clear();
                        tIndex.clear();
                        if (messageReceived.length <= 1)
                        {
                            sendMessage(event, getAuthorAsMention(event) + " You need arguments");
                        }

                        else
                        {
                            for (int i = 1; i<messageReceived.length; i++)
                            {
                                tIndex.add(Integer.parseInt(messageReceived[i]) - 1);
                            }
                            for (Integer index : tIndex)
                            {
                                lCard.add(isPlaying.getHand().getDeck().get(index));
                            }

                            if (canPlay(lCard))
                            {
                                for(Card card : lCard)
                                {
                                    try
                                    {
                                        toPlay.pick(card, isPlaying.getHand());
                                        System.out.print(card.toString());
                                    }
                                    catch (DeckSizeException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }

                                try
                                {
                                    showPlayed(lCard);

                                    if (hasWon())
                                    {
                                        endGame();
                                    }

                                    else
                                    {

                                        nextTurn(true);
                                    }
                                }
                                catch (IOException e)
                                {
                                    e.printStackTrace();
                                }
                            }

                            else
                            {
                                sendMessage("You can't play these cards");
                            }
                        }
                    break;

                    case Waiting:
                        sendMessage(event, "Please wait for the game to be launched");
                        break;

                    case End:
                        sendMessage(event, "No game is running");
                        break;
                }

            }

            else
            {
                sendMessage(event, getAuthorAsMention(event)+" It's not your turn");
            }
        }
    }

    @Override
    public String command()
    {
        return prefix+"play";
    }

    @Override
    public String help()
    {
        return "Use to play cards";
    }

    private boolean checkIndex(ArrayList<Integer> lCardIndex)
    {
        for(Integer integer : lCardIndex)
        {
            if (integer > lCardIndex.size())
            {
                sendMessage("Please check your indexes");
                return false;
            }

            else if(integer < 0)
            {
                sendMessage("Please check your indexes");
                return false;
            }
        }

        return true;
    }
}
