package ru.nsu.romankin;

import java.util.Scanner;

/** This is a main game class.
 * It implements the input and output of the game and has some additional functions*/
public class Main {
    static int currentRound = 1; //store the current round
    static int playerWonRounds = 0; //amount of rounds won by player
    static int dealerWonRounds = 0; //amount of rounds won by dealer

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
        return player.hand.size() == 2 && player.points == 21;
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
        if (dealer.points > 21) {
            player_win();
            return;
        }
        if (player.points <= 21 && dealer.points <= 21) {
            if (player.points > dealer.points) {
                player_win();
            }
            if (player.points < dealer.points) {
                player_loose();

            }
            if (player.points == dealer.points) {
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
        for (int i = 0; i < player.hand.size(); i++) {
            if (i == player.hand.size() - 1) {
                System.out.printf("%s %s (%d)]",
                        player.hand.get(i).rank,
                        player.hand.get(i).suit,
                        player.hand.get(i).value);

            } else {
                System.out.printf("%s %s (%d), ",
                        player.hand.get(i).rank,
                        player.hand.get(i).suit,
                        player.hand.get(i).value);
            }
        }
        System.out.printf(" -> %d\n", player.points);
        System.out.print("Карты дилера: [");
        for (int i = 0; i < dealer.hand.size(); i++) {
            if (dealer.hand.get(i).hidden) {
                System.out.print("<закрытая карта>]");
                break;
            }
            if (i == dealer.hand.size() - 1) {
                System.out.printf("%s %s (%d)]",
                        dealer.hand.get(i).rank,
                        dealer.hand.get(i).suit,
                        dealer.hand.get(i).value);

            } else {
                System.out.printf("%s %s (%d), ",
                        dealer.hand.get(i).rank,
                        dealer.hand.get(i).suit,
                        dealer.hand.get(i).value);
            }
        }
        if (!dealer.hidden) {
            System.out.printf(" -> %d\n", dealer.points);
        } else {
            System.out.print("\n");
        }
    }

    /**main method processing player input and displaying the game.*/
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в Блэкджек!");
        Scanner scan = new Scanner(System.in);
        while (currentRound < 5) {
            Deck deck = new Deck();
            Player player = new Player();
            Player dealer = new Player();
            player.getCard(deck);
            player.getCard(deck);

            dealer.getCard(deck);
            dealer.getCard(deck);
            dealer.hidden = true;
            dealer.hand.get(dealer.hand.size() - 1).hidden = true;
            System.out.printf("Раунд %d\nДилер раздал карты\n", currentRound);
            printCards(player, dealer);
            if (checkBlackjack(player)) {
                player_win();
                continue;
            }
            System.out.print("Ваш ход\n-------\n");
            int playerInput = 1;
            while (playerInput != 0) {
                if (player.points > 21) {
                    player_loose();
                    break;
                }
                System.out.print("Введите “1”, чтобы взять карту, и “0”, чтобы остановиться\n");
                playerInput = scan.nextInt();
                if (playerInput == 1) {
                    player.getCard(deck);
                    System.out.printf("Вы открыли карту %s %s (%d)\n",
                            player.hand.get(player.hand.size() - 1).rank,
                            player.hand.get(player.hand.size() - 1).suit,
                            player.hand.get(player.hand.size() - 1).value);
                    printCards(player, dealer);
                } else {
                    break;
                }
            }
            if (player.points > 21) {
                continue;
            }
            System.out.print("Ход дилера\n-------\n");
            dealer.hand.get(dealer.hand.size() - 1).hidden = false;
            dealer.hidden = false;
            System.out.printf("Дилер открывает закрытую карту %s %s (%d)\n",
                    dealer.hand.get(dealer.hand.size() - 1).rank,
                    dealer.hand.get(dealer.hand.size() - 1).suit,
                    dealer.hand.get(dealer.hand.size() - 1).value);

            printCards(player, dealer);
            if (checkBlackjack(dealer)) {
                player_loose();
                continue;
            }
            while (dealer.points <= 17) {
                dealer.getCard(deck);
                System.out.printf("Дилер открыл карту %s %s (%d)\n",
                        dealer.hand.get(dealer.hand.size() - 1).rank,
                        dealer.hand.get(dealer.hand.size() - 1).suit,
                        dealer.hand.get(dealer.hand.size() - 1).value);
                printCards(player, dealer);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("got interrupted!");
                }
            }
            checkWin(player, dealer);
        }
    }
}