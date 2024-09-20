package ru.nsu.romankin;

import java.util.Scanner;

/** This is a main game class.
 * It implements the input and output of the game and has some additional functions*/
public class Main {
    static int currentRound = 1; //store the current round
    static int playerWonRounds = 0; //amount of rounds won by player
    static int dealerWonRounds = 0; //amount of rounds won by dealer
    private static final int blackjack = 21;
    private static final int dealerStop = 17;
    static void player_loose() {
        dealerWonRounds++;
        System.out.print("Вы проиграли раунд! ");
        System.out.printf("Счёт %d:%d ",
                playerWonRounds, dealerWonRounds);
        if (playerWonRounds > dealerWonRounds) {
            System.out.print("в вашу пользу.");
        }
        if (playerWonRounds < dealerWonRounds) {
            System.out.print("в пользу дилера.");
        }
        System.out.print("\n");
        currentRound++;

    }

    static boolean checkBlackjack(Player player) {
        return player.getHandSize() == 2 && player.getPoints() == blackjack;
    }

    static void player_win() {
        playerWonRounds++;
        System.out.print("Вы выиграли раунд! ");
        System.out.printf("Счёт %d:%d ",
                playerWonRounds, dealerWonRounds);
        if (playerWonRounds > dealerWonRounds) {
            System.out.print("в вашу пользу.");
        }
        if (playerWonRounds < dealerWonRounds) {
            System.out.print("в пользу дилера.");
        }
        System.out.print("\n");
        currentRound++;
    }

    static void checkWin(Player player, Player dealer) {
        if (dealer.getPoints() > blackjack) {
            player_win();
            return;
        }
        if (player.getPoints() <= 21 && dealer.getPoints() <= 21) {
            if (player.getPoints() > dealer.getPoints()) {
                player_win();
            }
            if (player.getPoints() < dealer.getPoints()) {
                player_loose();

            }
            if (player.getPoints() == dealer.getPoints()) {
                System.out.print("Ничья! ");
                System.out.printf("Счёт %d:%d ",
                        playerWonRounds, dealerWonRounds);
                if (playerWonRounds > dealerWonRounds) {
                    System.out.print("в вашу пользу.");
                }
                if (playerWonRounds < dealerWonRounds) {
                    System.out.print("в пользу дилера.");
                }
                System.out.print("\n");
            }


        }
    }

    static void printCards(Player player, Player dealer) {
        System.out.print("Ваши карты: [");
        for (int i = 0; i < player.getHandSize(); i++) {
            if (i == player.getHandSize() - 1) {
                player.getCardByIndex(i).printCard();
                System.out.print("]");

            } else {
                player.getCardByIndex(i).printCard();
                System.out.print(", ");
            }
        }
        System.out.printf(" -> %d\n", player.getPoints());
        System.out.print("Карты дилера: [");
        for (int i = 0; i < dealer.getHandSize(); i++) {
            if (i == dealer.getHandSize() - 1) {
                dealer.getCardByIndex(i).printCard();
                System.out.print("]");
            } else {
                dealer.getCardByIndex(i).printCard();
                System.out.print(", ");
            }
        }
        if (!dealer.hidden) {
            System.out.printf(" -> %d\n", dealer.getPoints());
        } else {
            System.out.print("\n");
        }
    }

    /**main method processing player input and displaying the game.*/
    public static void main(String[] args) {

        System.out.println("Добро пожаловать в Блэкджек!");
        Scanner scan = new Scanner(System.in);
        while (true) {
            Deck deck = new Deck();
            Player player = new Player();
            Player dealer = new Player();
            player.takeCard(deck);
            player.takeCard(deck);

            dealer.takeCard(deck);
            dealer.takeCard(deck);
            dealer.getCardByIndex(dealer.getHandSize() - 1).hidden = true;
            dealer.hidden = true;
            System.out.printf("Раунд %d\nДилер раздал карты\n", currentRound);
            printCards(player, dealer);
            if (checkBlackjack(player)) {
                player_win();
                continue;
            }
            System.out.print("Ваш ход\n-------\n");
            int playerInput = 1;
            while (playerInput != 0) {
                if (player.getPoints() > blackjack) {
                    player_loose();
                    break;
                }
                System.out.print("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться\n");
                playerInput = scan.nextInt();
                if (playerInput == -1){
                    return;
                }
                if (playerInput == 1) {
                    player.takeCard(deck);
                    System.out.print("Вы открыли карту ");
                    player.getCardByIndex(player.getHandSize() - 1).printCard();
                    System.out.print("\n");
                    printCards(player, dealer);
                } else {
                    break;
                }
            }
            if (player.getPoints() > blackjack) {
                continue;
            }
            System.out.print("Ход дилера\n-------\n");
            dealer.getCardByIndex(dealer.getHandSize() - 1).hidden = false;
            dealer.hidden = false;
            System.out.print("Дилер открывает закрытую карту ");
            dealer.getCardByIndex(dealer.getHandSize() - 1).printCard();
            System.out.print("\n");
            printCards(player, dealer);
            if (checkBlackjack(dealer)) {
                player_loose();
                continue;
            }
            while (dealer.getPoints() < dealerStop) {
                dealer.takeCard(deck);
                System.out.print("Дилер открыл карту ");
                dealer.getCardByIndex(dealer.getHandSize() - 1).printCard();
                System.out.print("\n");
                printCards(player, dealer);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("got interrupted!");
                }
            }
            checkWin(player, dealer);
            System.out.print("\n\n\n");
        }
    }
}