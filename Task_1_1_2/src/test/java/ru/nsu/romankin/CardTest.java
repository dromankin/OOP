package ru.nsu.romankin;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    void cardConstructor() {
        Card card = new Card("Туз", "Трефы", 11);
        assertTrue(card.rank.equals("Туз"));
        assertTrue(card.suit.equals("Трефы"));
        assertTrue(card.value == 11);
    }

    @Test
    void cardConstructorFalse() {
        Card card = new Card("Туз", "Трефы", 11);
        assertFalse(!card.rank.equals("Туз"));
        assertFalse(card.hidden);
        assertFalse(card.value != 11);
    }
}