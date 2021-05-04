package me.commands;

import me.board.Game;
import me.exceptions.DeckSizeException;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nonnull;
import java.io.IOException;

public class Pick extends Game implements ICommands
{

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
                    case End:
                        sendMessage(event, getAuthorAsMention(event)+" No game is running");
                        return;

                    case Waiting:
                        sendMessage(event, getAuthorAsMention(event)+" Please wait for the game to be launched");
                        return;

                    case Playing:
                        try
                        {
                            System.out.println("\n\nToPick = "+toPick);
                            int i=0;
                            do
                            {
                                isPlaying.getHand().pickFirst(pile);
                                i++;
                            }
                            while (i<toPick);
                            toPick=0;
                            checkPile();
                            nextTurn(false);
                        }
                        catch (DeckSizeException | IOException e)
                        {
                            e.printStackTrace();
                        }

                        break;

                    default:
                        System.out.println("Wtf issue !Pick");
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
        return prefix+"pick";
    }

    @Override
    public String help()
    {
        return "Use to pick a card from the pile";
    }
}
