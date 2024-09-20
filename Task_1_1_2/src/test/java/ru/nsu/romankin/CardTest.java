package ru.nsu.romankin;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    void cardConstructor() {
        Card card = new Card(Rank.ACE, "Трефы");
        assertTrue(card.getSuit() == "Трефы");
        assertTrue(card.getValue() == 11);
    }

    @Test
    void cardConstructorFalse() {
        Card card = new Card(Rank.ACE, "Трефы");

        assertFalse(card.hidden);
        assertFalse(card.getValue() != 11);
    }
}