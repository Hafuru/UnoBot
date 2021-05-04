package me.commands;

import me.board.Game;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

import javax.annotation.Nonnull;
import java.io.IOException;

public class SortC extends Game implements ICommands
{
    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event)
    {
        super.onMessageReceived(event);
    }

    @Override
    public void onPrivateMessageReceived(@Nonnull PrivateMessageReceivedEvent event)
    {
        String[] messageReceived = event.getMessage().getContentRaw().split("\\s+");

        if (messageReceived[0].equalsIgnoreCase(command()))
        {
            System.out.println("SortC");

            if (isPlayer(event.getAuthor()))
            {
                getPlayerByID(event.getAuthor().getId()).sortByColor();
                try
                {
                    getPlayerByID(event.getAuthor().getId()).sendHand();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                event.getChannel().sendMessage("You are not playing");
            }
        }
    }

    @Override
    public String command()
    {
        return prefix+"SortC";
    }

    @Override
    public String help()
    {
        return "Sort hand's cards by color";
    }
}
