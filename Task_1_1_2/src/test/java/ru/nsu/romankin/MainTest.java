package ru.nsu.romankin;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        String output  = runGame("-1\n");
        String subOutput = output.substring(0, 28);
        assertEquals("Добро пожаловать в Блэкджек!", subOutput);
        String subOutput2  = output.substring(28, 57);
        assertEquals("\r\nРаунд 1\nДилер раздал карты\n", subOutput2);
        String subOutput3  = output.substring(57, 68);
        assertEquals("Ваши карты:", subOutput3);
    }

    @Test
    void anotherInputTest() {
        String output  = runGame("0\n0\n0\n0\n0\n4\n");
        String subOutput = output.substring(output.length() - 58);
        assertEquals("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться\n", subOutput);
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

        assertEquals(2, main.playerWonRounds);
        assertEquals(3, main.currentRound);
    }

    @Test
    void looseFunction() {
        Main main = new Main();
        main.player_loose();
        main.player_loose();
        main.player_loose();
        assertEquals(3, main.dealerWonRounds);
        assertEquals(4, main.currentRound);
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
        Main main = new Main();
        int rounds = main.playerWonRounds;
        main.checkWin(player, dealer);
        assertTrue(main.playerWonRounds >= rounds);
    }
}