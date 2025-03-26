package ru.nsu.romankin.snake;

public class Coordinate {

    private int x, y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Coordinate coordinate) {
        return (this.x == coordinate.x && this.y == coordinate.y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
