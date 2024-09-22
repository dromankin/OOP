package ru.nsu.romankin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;


import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    void cardConstructor() {
        Card card = new Card(Rank.ACE, "Трефы");
        assertSame("Трефы", card.getSuit());
        assertEquals(11, card.getValue());
    }

    @Test
    void cardConstructorFalse() {
        Card card = new Card(Rank.ACE, "Трефы");

        assertFalse(card.getHiddenCard());
        assertFalse(card.getValue() != 11);
    }
}