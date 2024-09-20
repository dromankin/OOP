package ru.nsu.romankin;

import java.util.ArrayList;
import java.util.Collections;

/** This class describes a deck of cards.
 * It consists of ArrayList of class Card instances.*/
public class Deck {
    ArrayList<Card> list = new ArrayList<>();;
    /** Class constructor.*/

    public Deck() {
        String[] suitNames = new String[] {"Червы", "Пики", "Бубны", "Трефы"};
        Rank[] ranks = new Rank[] {Rank.TW0, Rank.THREE, Rank.FOUR, Rank.FIVE,
                Rank.SIX, Rank.SEVEN, Rank.EIGHT, Rank.NINE, Rank.TEN,
                Rank.JACK, Rank.QUEEN, Rank.KING, Rank.ACE};
        for (String suit : suitNames) { //this loop responds for filling the deck
            for (Rank rank : ranks) {
                list.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(list); //shuffling deck;
    }
    /** Operation of removing card from the top of the deck.*/

    public void removeCard() {
        list.remove(0);
    }
}
