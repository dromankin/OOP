package ru.nsu.romankin;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void playerConstructor() {
        Player player = new Player();
        assertTrue(!player.getHidden());
        assertTrue(player.getHandSize() == 0);
        assertFalse(player.getPoints() != 0);

    }

    @Test
    void takeCardTest() {
        Player player = new Player();
        Deck deck = new Deck();
        player.takeCard(deck);
        player.takeCard(deck);
        player.takeCard(deck);
        assertTrue(player.getHandSize() == 3);
        assertTrue(player.getPoints() < 31);
    }

    @Test
    void getAceTest() {
        int aceCount = 0;
        Player player = new Player();
        Deck deck = new Deck();
        while (aceCount < 2) {
            player.takeCard(deck);
            if (player.getCardByIndex(player.getHandSize() - 1).getRank() == Rank.ACE) {
                aceCount++;
            }
        }
        for (int i = 0; i < player.getHandSize(); i++) {
            if (player.getCardByIndex(i).getRank() == Rank.ACE) {
                assertTrue(player.getCardByIndex(i).getValue() == 1);
            }
        }

    }
}