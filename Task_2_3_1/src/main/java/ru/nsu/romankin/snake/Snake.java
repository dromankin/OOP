package ru.nsu.romankin.snake;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<Coordinate> snake = new ArrayList<>();
    public Snake(int startX, int startY) {
        snake.add(new Coordinate(startX, startY));
    }
    public int getLength() {
        return snake.size();
    }

    public void add(int x, int y) {
        snake.add(new Coordinate(x, y));
    }

    public List<Coordinate> getSnakeBody() {
        return snake;
    }

    public Coordinate getSnakeHead() {
        return snake.get(0);
    }

    public void setSnakeHead(Coordinate head) {
        snake.add(0, head);
    }

    public void removeTail() {
        snake.remove(snake.size() - 1);
    }


}
