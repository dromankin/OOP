package ru.nsu.romankin;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


class DeckTest {
    @Test
    void deckConstructor() {
        Deck deck = new Deck();
        assertTrue(deck.getDeckSize() == 52);
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
        assertTrue(deck.getDeckSize() == 49);
    }
}