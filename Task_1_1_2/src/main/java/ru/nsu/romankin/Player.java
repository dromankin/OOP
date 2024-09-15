package ru.nsu.romankin;

import java.util.ArrayList;

public class Player {
    public boolean hidden = false;
    int points = 0;
    ArrayList<Card> hand = new ArrayList<Card>();
    ArrayList<Integer> aceIndex = new ArrayList<Integer>();
    public void getCard(Deck deck){
        hand.add(deck.list.get(0));
        if (hand.get(hand.size() - 1).rank.equals("Туз")){
            aceIndex.add(hand.size() - 1);
        }
        points += hand.get(hand.size() - 1).value;
        deck.removeCard();
        if (points > 21 && !aceIndex.isEmpty()) {
            points -= 10;
            for (int i = 0; i < aceIndex.size(); i++) {
                hand.get(aceIndex.get(i)).value = 1;
            }
        }
    }

}
