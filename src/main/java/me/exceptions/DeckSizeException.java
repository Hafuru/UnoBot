package me.exceptions;

public class DeckSizeException extends Exception
{
    public DeckSizeException(int size)
    {
        super("Deck size is : "+size+"\nCan't exceed 108");
    }


}
