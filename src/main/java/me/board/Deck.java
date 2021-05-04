package me.board;

import me.exceptions.DeckSizeException;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Representing a full deck, which is a list of Card
 */
public class Deck
{
    /**
     * Representing a deck
     */
    private ArrayList<Card> deck;

    /**
     * Default constructor, empty deck
     */
    public Deck()
    {
        deck = new ArrayList<>();
    }

    /**
     * Constructor. Creating a deck from a list of card
     * @param deck
     */
    public Deck(ArrayList<Card> deck)
    {
        this.deck = deck;
    }

    /**
     * Adding a card to the deck
     * @param card
     * @throws DeckSizeException
     * @see DeckSizeException
     */
    public void add(Card card) throws DeckSizeException
    {
        deck.add(card);
        if (deck.size()>108)
        {
            throw new DeckSizeException(deck.size());
        }
    }

    /**
     * remove a card from the deck
     * @param card
     */
    public void remove(Card card)
    {
        deck.remove(card);
        //Add listener to check if the deck is empty (then refill it) or not
    }

    /**
     * Check if the deck is empty
     * @return true if the deck is empty, false otherwise
     */
    public boolean isEmpty()
    {
        return deck.isEmpty();
    }

    /**
     * Shuffle a deck
     * @param count Number of time to shuffle
     */
    public void shuffle(int count)
    {
        for (int i=0; i<count; i++)
        {
            Collections.shuffle(deck);
        }
    }

    /**
     * Take a card from a deck
     * @param card
     * @param deck
     * @throws DeckSizeException
     */
    public void pick(Card card, Deck deck) throws DeckSizeException
    {
        add(card);
        deck.remove(card);
    }

    /**
     * Getter for the deck
     * @return deck attribute
     */
    public ArrayList<Card> getDeck()
    {
        return deck;
    }

    /**
     * Take the first card of the deck to another one
     * @param deck the deck to receive the card
     * @throws DeckSizeException
     */
    public void pickFirst(Deck deck) throws DeckSizeException
    {
        add(deck.getDeck().get(0));
        deck.remove(deck.getDeck().get(0));
    }

    /**
     * @return the last card of the deck
     */
    public Card getLast()
    {
        int size = getDeck().size();
        return getDeck().get(size-1);
    }
}
