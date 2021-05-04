package me.commands;

import me.board.Game;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

import javax.annotation.Nonnull;
import java.io.IOException;

public class SortN extends Game implements ICommands
{
    @Override
    public void onPrivateMessageReceived(@Nonnull PrivateMessageReceivedEvent event)
    {
        String[] messageReceived = event.getMessage().getContentRaw().split("\\s+");

        if (messageReceived[0].equalsIgnoreCase(command()))
        {
            System.out.println("SortN");

            if (isPlayer(event.getAuthor()))
            {
                System.out.println("Author is player");
                getPlayerByID(event.getAuthor().getId()).sortByNumber();
                try
                {
                    getPlayerByID(event.getAuthor().getId()).sendHand();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

            else
            {
                System.out.println("You are not playing");
            }
        }
    }

    @Override
    public String command()
    {
        return prefix+"SortN";
    }

    @Override
    public String help()
    {
        return "Sort hand by number";
    }
}
