package me.commands;

import me.board.Color;
import me.board.Game;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nonnull;
import java.io.IOException;

public class ColorCommand extends Game implements ICommands
{
    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event)
    {
        String[] messageReceived = event.getMessage().getContentRaw().split("\\s+");

        if (messageReceived[0].equalsIgnoreCase(command()))
        {
            if (event.getAuthor().getId().equals(isPlaying.getId()))
            {
                if (waitingForColor)
                {
                    switch (messageReceived[1].toLowerCase())
                    {
                        case "red":
                            color = Color.Red;
                            break;

                        case "green":
                            color = Color.Green;
                            break;

                        case "blue":
                            color = Color.Blue;
                            break;

                        case "yellow":
                            color = Color.Yellow;
                            break;

                        default:
                            sendMessage("Please choose a valid color" +
                                "\n- Red" +
                                "\n- Blue" +
                                "\n- Green" +
                                "\n- Yellow");
                        break;
                    }

                    waitingForColor=false;
                    try
                    {
                        nextTurn(false);
                    }

                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public String command()
    {
        return prefix+"color";
    }

    @Override
    public String help()
    {
        return "Use to specify a color when playing a black card";
    }
}
