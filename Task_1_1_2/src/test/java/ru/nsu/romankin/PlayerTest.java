package ru.nsu.romankin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


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
        Player player = new Player();
        Deck deck = new Deck();
        deck.list.get(0).rank = "Туз";
        deck.list.get(0).value = 11;
        deck.list.get(1).rank = "Туз";
        deck.list.get(1).value = 11;
        deck.list.get(2).rank = "Король";
        deck.list.get(2).value = 10;

        player.getCard(deck);
        player.getCard(deck);
//        System.out.println(player.hand.get(0).value);
//        System.out.println(player.points);
        assertTrue(player.hand.get(0).value == 1);
        assertTrue(player.points == 12);
        player.getCard(deck);
        assertTrue(player.points == 12);
        assertTrue(player.hand.get(1).value == 1);
    }
}