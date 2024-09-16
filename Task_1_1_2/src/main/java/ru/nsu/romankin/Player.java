package ru.nsu.romankin;

import java.util.ArrayList;

/** Player class with getCard function.*/
public class Player {
    public boolean hidden = false; //flag that shows if player has hidden cards
    int points = 0; //amount of points in current round
    ArrayList<Card> hand = new ArrayList<Card>();
    //array of indexes of aces for correct getting aces processing
    ArrayList<Integer> aceIndex = new ArrayList<Integer>();

    /**Function of taking card from the top of the deck.
     * It also has a check for changing the value of aces in the hand*/
    public void getCard(Deck deck) {
        hand.add(deck.list.get(0));
        if (hand.get(hand.size() - 1).rank.equals("Туз")) {
            aceIndex.add(hand.size() - 1); //adding current index if we added ace
        }
        points += hand.get(hand.size() - 1).value;
        deck.removeCard();
        if (points > 21 && !aceIndex.isEmpty()) {
            points -= 10;
            for (int i = 0; i < aceIndex.size(); i++) {
                hand.get(aceIndex.get(i)).value = 1; //changing value of aces
            }
        }
    }

}
