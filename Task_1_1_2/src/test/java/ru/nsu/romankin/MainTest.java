package ru.nsu.romankin;

import static org.junit.jupiter.api.Assertions.assertFalse;
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
        runGame("0\n0\n0\n0\n-1\n");
        assertTrue(true);
    }

    @Test
    void blackjackFunction() {
        Deck deck = new Deck();

        Player player = new Player();
        player.takeCard(deck);
        player.takeCard(deck);
        if (player.getPoints() == 21) {
            assertTrue(Main.checkBlackjack(player));
        } else {
            assertFalse(Main.checkBlackjack(player));
        }
    }

    @Test
    void winFunction() {
        Main.playerWonRounds = 0;
        Main.currentRound = 1;
        Main.player_win();
        Main.player_win();

        assertTrue(Main.playerWonRounds == 2);
        assertTrue(Main.currentRound == 3);
    }

    @Test
    void looseFunction() {

        Main.player_loose();
        Main.player_loose();
        Main.player_loose();
        assertTrue(Main.dealerWonRounds == 3);
        assertTrue(Main.currentRound == 4);
    }

    @Test
    void checkWinTest() {
        Player player = new Player();
        Player dealer = new Player();
        Deck deck = new Deck();
        player.takeCard(deck);
        player.takeCard(deck);
        dealer.takeCard(deck);
        dealer.takeCard(deck);
        int rounds = Main.playerWonRounds;
        Main.checkWin(player, dealer);
        assertTrue(Main.playerWonRounds >= rounds);
    }
}