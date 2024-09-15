package ru.nsu.romankin;

public class Card {
    public boolean hidden = false;
    public String suit;
    String rank;
    public Card(String rank, String suit, int value) {
        this.suit = suit;
        this.value = value;
        this.rank = rank;
    }
    protected int value;

}
