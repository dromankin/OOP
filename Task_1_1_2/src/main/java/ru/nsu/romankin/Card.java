package ru.nsu.romankin;

/** This class describes a single card.
 *  */
public class Card {
    public boolean hidden = false; //this flag is showing if card hidden or not
    public String suit;
    String rank;
    /** This is a card constructor building a card by input parameters.
     *
     * @param rank -- rank of card (from "Двойка" to "Туз")
     *
     * @param suit -- suit of card ("Червы", "Пики", "Бубны", "Трефы")
     *
     * @param value -- value of card (from 2 to 11)*/

    public Card(String rank, String suit, int value) {
        this.suit = suit;
        this.value = value;
        this.rank = rank;
    }

    protected int value;

}
