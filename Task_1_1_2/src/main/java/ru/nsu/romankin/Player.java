package ru.nsu.romankin;

import java.util.ArrayList;

/** Player class with takeCard function.*/
public class Player {
    private int points = 0; //amount of points in current round
    private ArrayList<Card> hand = new ArrayList<Card>();
    //array of indexes of aces for correct getting aces processing
    private ArrayList<Integer> aceIndex = new ArrayList<Integer>();
    private boolean hidden = false;

    /**gets value of hidden flag.*/
    public boolean getHidden() {
        return hidden;
    }

    /**sets value of hidden flag.*/
    public void setHiddenPlayer(boolean bool) {
        hidden = bool;
    }
    /**gets card by its index in player's hand.*/

    public Card getCardByIndex(int index) {
        return hand.get(index);
    }
    /**gets player's points.*/

    public int getPoints() {
        return points;
    }

    /**gets player's hand size.*/
    public int getHandSize() {
        return hand.size();
    }

    /**Function of taking card from the top of the deck.
     * It also has a check for changing the value of aces in the hand*/

    public void takeCard(Deck deck) {
        hand.add(deck.getTopCard());
        if ((hand.get(hand.size() - 1)).getRank() == Rank.ACE) {
            aceIndex.add(hand.size() - 1); //adding current index if we added ace
        }
        points += hand.get(hand.size() - 1).getValue();
        deck.removeCard();
        if (points > 21 && !aceIndex.isEmpty()) {
            for (int i = 0; i < aceIndex.size(); i++) {
                if (getCardByIndex(aceIndex.get(i)).getValue() == 11) {
                    points -= 10;
                }
            }

            for (int i = 0; i < aceIndex.size(); i++) {
                hand.get(aceIndex.get(i)).setAceValue(); //changing value of aces
            }
        }
    }

}
