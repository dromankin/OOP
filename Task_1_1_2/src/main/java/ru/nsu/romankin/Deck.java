package ru.nsu.romankin;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    ArrayList<Card> list;

    public Deck() {
        list = new ArrayList<>();
        String[] suitNames = new String[] {"Червы", "Пики", "Бубны", "Трефы"};
        String[] rankNames = new String[] {"Двойка", "Тройка", "Четвёрка", "Пятёрка",
        "Шестёрка", "Семёрка", "Восьмёрка", "Девятка", "Десятка", "Валет", "Дама",
        "Король", "Туз"};
        int[] values = new int[] {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};
        int valueIndex = 0;
        for (String suit: suitNames){
            for (String rank: rankNames){
                list.add(new Card(rank, suit, values[valueIndex]));
                valueIndex++;
            }
            valueIndex=0;
        }
        Collections.shuffle(list);
    }

    public void removeCard(){
        list.remove(0);
    }
}
