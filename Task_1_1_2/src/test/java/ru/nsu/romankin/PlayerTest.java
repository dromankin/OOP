package ru.nsu.romankin;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void playerConstructor() {
        Player player = new Player();
        assertTrue(!player.hidden);
        assertTrue(player.hand.size() == 0);
        assertFalse(player.points != 0);

    }

    @Test
    void getCardTest() {
        Player player = new Player();
        Deck deck = new Deck();
        player.getCard(deck);
        player.getCard(deck);
        player.getCard(deck);
        assertTrue(player.hand.size() == 3);
        assertTrue(player.points < 31);
    }

    @Test
    void getAceTest() {
        final Player player = new Player();
        Deck deck = new Deck();
        deck.list.get(0).rank = "Туз";
        deck.list.get(0).value = 11;
        deck.list.get(1).rank = "Туз";
        deck.list.get(1).value = 11;
        deck.list.get(2).rank = "Король";
        deck.list.get(2).value = 10;

        player.getCard(deck);
        player.getCard(deck);

        assertTrue(player.hand.get(0).value == 1);
        assertTrue(player.points == 12);
        player.getCard(deck);
        assertTrue(player.points == 12);
        assertTrue(player.hand.get(1).value == 1);
    }
}