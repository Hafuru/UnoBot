package me.commands;

import me.board.Game;
import me.exceptions.DeckSizeException;
import me.players.Player;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nonnull;

public class Join extends Game implements ICommands
{
    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event)
    {
        String[] messageReceived = event.getMessage().getContentRaw().split("\\s+");

        if (event.getAuthor().isBot())
        {
            return;
        }

        if (messageReceived.length>1 && messageReceived[0].equals(command()))
        {
            sendMessage(event, getAuthorAsMention(event)+help());
            return;
        }

        if (messageReceived[0].equalsIgnoreCase(command()))
        {
            Player newPlayer = new Player(event.getAuthor(), event.getAuthor().getId());

            for (Player p : players)
            {
                if (p.getId().equals(newPlayer.getId()))
                {
                    sendMessage(event, getAuthorAsMention(event)+"You already joined");
                    return;
                }
            }

            players.add(newPlayer);
            event.getChannel().sendMessage(event.getAuthor().getName()+" joined the party").queue();

            switch (gameState)
            {
                case End:
                    try
                    {
                        setGame(event);
                    }
                    catch (DeckSizeException e)
                    {
                        e.printStackTrace();
                    }
                    event.getChannel().sendMessage("<@"+players.get(0).getId()+"> is the game master. Type \"!go\" to launch the game !").queue();
                    break;

                case Playing:
                    sendMessage(event, "The game is already running, you can't join");
                    break;

                default:
                    System.out.println("Play default");
                    break;
            }

        }

    }

    public String command()
   {
       return prefix+"join";
   }

    public String help()
    {
        return "The command is \"!play\", it allows you to join the party to play Uno ! The first player to join will be the game master";
    }
}
