package ru.nsu.romankin.snake;


/**
 * Class representing a coordinate on a field.
 */
public class Coordinate {

    private int x, y;

    /**
     * Class constructor.
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Coordinate) {
            return (this.x == ((Coordinate) object).getX() &&
                    this.y == ((Coordinate) object).getY());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
