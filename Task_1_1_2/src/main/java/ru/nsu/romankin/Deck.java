package ru.nsu.romankin;

import java.util.ArrayList;
import java.util.Collections;

/** This class describes a deck of cards.
 * It consists of ArrayList of class Card instances.*/
public class Deck {
    ArrayList<Card> list;
    /** Class constructor.*/

    public Deck() {
        list = new ArrayList<>();
        String[] suitNames = new String[] {"Червы", "Пики", "Бубны", "Трефы"};
        String[] rankNames = new String[] {"Двойка", "Тройка", "Четвёрка", "Пятёрка",
            "Шестёрка", "Семёрка", "Восьмёрка", "Девятка", "Десятка", "Валет", "Дама",
            "Король", "Туз"};
        int[] values = new int[] {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11}; //array of values
        int valueIndex = 0; //we use this variable to move by values array for each card suit
        for (String suit : suitNames) { //this loop responds for filling the deck
            for (String rank : rankNames) {
                list.add(new Card(rank, suit, values[valueIndex]));
                valueIndex++;
            }
            valueIndex = 0; //resetting when going to new suit
        }
        Collections.shuffle(list); //shuffling deck;
    }
    /** Operation of removing card from the top of the deck.*/

    public void removeCard() {
        list.remove(0);
    }
}
