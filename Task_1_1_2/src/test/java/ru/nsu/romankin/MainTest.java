package ru.nsu.romankin;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;


class MainTest {
    private String runGame(String input) {

        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));
        InputStream inputStream = System.in;
        PrintStream outputStream = System.out;
        Main.main(null);
        System.setIn(inputStream);
        System.setOut(outputStream);
        return myOut.toString();
    }

    @Test
    void inputTest() {
        runGame("0\n0\n0\n0");
        assertTrue(true);
    }

    @Test
    void blackjackFunction() {
        Player player = new Player();
        Deck deck = new Deck();
        deck.list.get(0).rank = "Туз";
        deck.list.get(0).value = 11;
        deck.list.get(1).rank = "Король";
        deck.list.get(1).value = 10;
        player.getCard(deck);
        player.getCard(deck);
        assertTrue(Main.checkBlackjack(player));
    }

    @Test
    void looseFunction() {
        Main.player_loose();
        Main.player_loose();
        assertTrue(Main.dealerWonRounds == 2);
    }
}