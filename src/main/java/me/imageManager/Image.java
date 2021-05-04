package me.imageManager;

import me.board.Card;
import me.players.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Image
{
    public static BufferedImage joinBufferedImage(BufferedImage img1,
                                                  BufferedImage img2) {
        int offset = 2;
        int width = img1.getWidth() + img2.getWidth() + offset;
        int height = Math.max(img1.getHeight(), img2.getHeight());
        BufferedImage newImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newImage.createGraphics();
        Color oldColor = g2.getColor();
        g2.setPaint(Color.BLACK);
        g2.fillRect(0, 0, width, height);
        g2.setColor(oldColor);
        g2.drawImage(img1, null, 0, 0);
        g2.drawImage(img2, null, img1.getWidth() + offset, 0);
        g2.dispose();
        return newImage;
    }

    public static String playerCard(Player player) throws IOException
    {
        String pathname = "src\\main\\java\\me\\imageManager\\cards"+player.getName()+".png";
        BufferedImage image = ImageIO.read(new URL(player.getHand().getDeck().get(0).getURL()));
        for (int i = 1; i<player.getHand().getDeck().size(); i++)
        {
            image = joinBufferedImage(image, ImageIO.read(new URL(player.getHand().getDeck().get(i).getURL())));
        }

        ImageIO.write(image, "png", new File(pathname));
        return pathname;
    }

    public static String playedCard(ArrayList<Card> cards) throws IOException
    {
        String pathname = "src\\main\\java\\me\\imageManager\\cards\\cards.png";
        BufferedImage image = ImageIO.read(new URL(cards.get(0).getURL()));
        for (int i = 1; i<cards.size(); i++)
        {
            image = joinBufferedImage(image, ImageIO.read(new URL(cards.get(i).getURL())));
        }

        ImageIO.write(image, "png", new File(pathname));
        return pathname;
    }
}
