package me.commands;

import me.board.Deck;
import me.board.Game;
import me.board.GameState;
import me.exceptions.DeckSizeException;
import me.players.Player;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.ArrayList;

public class Go extends Game implements ICommands
{
    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event)
    {
        String[] messageReceived = getMessage(event);

        if (event.getAuthor().isBot())
        {
            return;
        }

        /*if (!event.getAuthor().getName().equals(players.get(0).getName()) && messageReceived[0].equalsIgnoreCase(command()))
        {
            sendMessage(event, getAuthorAsMention(event)+" Only the game master can start the game !\nThe GM is "+players.get(0).getName());
            return;
        }*/

        if (messageReceived.length!=1 && messageReceived[0].equalsIgnoreCase(command()))
        {
            sendMessage(event, getAuthorAsMention(event)+ "Please do not use any arguments");
            return;
        }

        if (messageReceived[0].equalsIgnoreCase(command()))
        {
            switch (Game.gameState)
            {
                case Waiting:
                    setGameState(GameState.Playing);
                    String message = "The game is starting !\nThe order is the following :\n";
                    for (Player p : players)
                    {
                        message+="- "+p.getName()+"\n";
                    }
                    sendMessage(event, message);

                    message = "Distributing cards. Check your DMs !";
                    sendMessage(event, message);

                    try
                    {
                        distributing(players, pile);
                        for (Player player:players)
                        {
                            player.printHand();
                            player.sendHand();
                        }

                        showLast(true);
                        setGameState(GameState.Playing);
                    }
                    catch (DeckSizeException | IOException e)
                    {
                        e.printStackTrace();
                    }
                    break;

                case Playing:
                    sendMessage(event, "The game is already running");
                    break;

                default:
                    System.out.println("Default");
                    break;
            }

        }
    }

    @Override
    public String command()
    {
        return prefix+"go";
    }

    @Override
    public String help()
    {
        return "To use this command, type \"!go\" can only be user by the game master to start the game";
    }

    protected void distributing(ArrayList<Player> players, Deck pile) throws DeckSizeException
    {
        for (Player player : players)
        {
            for (int i=0; i<7; i++)
            {
                player.getHand().pickFirst(pile);
            }
        }
    }
}
