package me;

import me.commands.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class TestUnoBot
{
    public static void main(String[] args) throws LoginException
    {
        JDA jda = new JDABuilder("NzEzODExMTAyMTY0NzEzNTMz.XslidA.lXrG4J0oJmOIZe13HoYifGIl0-U").build();
        jda.addEventListener(new Join());
        jda.addEventListener(new Go());
        jda.addEventListener(new Pick());
        jda.addEventListener(new Play());
        jda.addEventListener(new ColorCommand());
        jda.addEventListener(new SortC());
        jda.addEventListener(new SortN());
    }


}
