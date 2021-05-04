package me.commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public interface ICommands
{
    String prefix = "!";

    String command();

    String help();

    default String[] getMessage(GuildMessageReceivedEvent event)
    {
        return event.getMessage().getContentRaw().split("\\s+");
    }

    default String getAuthorAsMention(GuildMessageReceivedEvent event)
    {
        return event.getAuthor().getAsMention();
    }

    default void sendMessage(GuildMessageReceivedEvent event, String message)
    {
        event.getChannel().sendMessage(message).queue();
    }
}
