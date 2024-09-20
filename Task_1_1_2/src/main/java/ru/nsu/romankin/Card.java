package ru.nsu.romankin;

/** This class describes a single card.
 *  */
public class Card {
    boolean hidden = false; //this flag is showing if card hidden or not
    private String suit;
    private Rank rank;
    private int value;
    /** This is a card constructor building a card by input parameters.
     *
     * @param rank -- rank of card (from "Двойка" to "Туз")
     *
     * @param suit -- suit of card ("Червы", "Пики", "Бубны", "Трефы")
     */

    public Card(Rank rank, String suit) {
        this.suit = suit;
        this.rank = rank;
        this.value = rank.value;
    }

    /**gets value of the card.*/

    public int getValue() {
        return value;
    }

    /**set ace value to 1 in case of overflow.*/

    public void setAceValue() {
        if (rank == Rank.ACE) {
            value = 1;
        }
    }

    /**prints card name when it is not closed.*/
    public void printCard() {
        if (hidden) {
            System.out.print("<закрытая карта>");
        } else {
            System.out.printf("%s %s (%d)", rank.rankname, suit, value);
        }
    }

    /**gets rank of the card.*/

    public Rank getRank() {
        return rank;
    }

    /**gets suit of the card.*/

    public String getSuit() {
        return suit;
    }
}
