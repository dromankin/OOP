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
        Main main = new Main();
        player.takeCard(deck);
        player.takeCard(deck);
        if (player.getPoints() == 21) {
            assertTrue(main.checkBlackjack(player));
        } else {
            assertFalse(main.checkBlackjack(player));
        }
    }

    @Test
    void winFunction() {
        Main main = new Main();

        main.player_win();
        main.player_win();

        assertTrue(main.playerWonRounds == 2);
        assertTrue(main.currentRound == 3);
    }

    @Test
    void looseFunction() {
        Main main = new Main();
        main.player_loose();
        main.player_loose();
        main.player_loose();
        assertTrue(main.dealerWonRounds == 3);
        assertTrue(main.currentRound == 4);
    }

    @Test
    void checkWinTest() {
        Player player = new Player();
        Player dealer = new Player();
        Deck deck = new Deck();
        Main main = new Main();
        player.takeCard(deck);
        player.takeCard(deck);
        dealer.takeCard(deck);
        dealer.takeCard(deck);
        int rounds = main.playerWonRounds;
        main.checkWin(player, dealer);
        assertTrue(main.playerWonRounds >= rounds);
    }
}