package me.players;

import me.board.Card;
import me.board.Deck;
import me.imageManager.Image;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

public class Player
{
    private final User user;
    private final String id;
    private boolean isGameMaster = false;
    private Deck hand;

    public Player(User user, String id)
    {
        this.user = user;
        this.id = id;
        hand = new Deck();
    }

    public User getUser()
    {
        return user;
    }

    public String getId()
    {
        return id;
    }

    public boolean isGameMaster()
    {
        return isGameMaster;
    }

    public void setGameMaster(boolean gameMaster)
    {
        isGameMaster = gameMaster;
    }

    public Deck getHand()
    {
        return hand;
    }

    public void setHand(Deck hand)
    {
        this.hand = hand;
    }

    public void sendHand() throws IOException
    {
        File file = new File(Image.playerCard(this));
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setDescription("Here is your hand, you have "+getHand().getDeck().size()+" cards");
        user.openPrivateChannel().complete().sendFile(file).queue();
        user.openPrivateChannel().complete().sendMessage(embedBuilder.build()).queue();


        file.delete();
    }

    public String getName()
    {
        return user.getName();
    }

    public void printHand()
    {
        System.out.println("Player : "+getName());
        for (Card card: hand.getDeck())
        {
            System.out.print("["+card.getNumber()+", "+card.getColor()+"]");
        }
        System.out.println("\n");
    }

    public String getAsMention()
    {
        return user.getAsMention();
    }

    public void sortByColor()
    {
        Collections.sort(hand.getDeck(), new Comparator<Card>()
        {
            @Override
            public int compare(Card o1, Card o2)
            {
                return o1.getColor().compareTo(o2.getColor());
            }
        });
    }

    public void sortByNumber()
    {
        Collections.sort(hand.getDeck(), new Comparator<Card>()
        {
            @Override
            public int compare(Card o1, Card o2)
            {
                return o1.getNumber().compareTo(o2.getNumber());
            }
        });
    }
}
