package ru.nsu.romankin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;


class DeckTest {
    @Test
    void deckConstructor() {
        Deck deck = new Deck();
        assertEquals(52, deck.getDeckSize());
    }

    @Test
    void deckConstructorFalse() {
        Deck deck = new Deck();
        assertFalse(deck.getDeckSize() != 52);
    }

    @Test
    void deckRemove() {
        Deck deck = new Deck();
        deck.removeCard();
        deck.removeCard();
        deck.removeCard();
        assertEquals(49, deck.getDeckSize());
    }
}