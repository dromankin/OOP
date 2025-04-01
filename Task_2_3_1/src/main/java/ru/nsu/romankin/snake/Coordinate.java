package ru.nsu.romankin.snake;


/**
 * Class representing a coordinate on a field.
 */
public class Coordinate {

    private int xVar;
    private int yVar;

    /**
     * Class constructor.
     */
    public Coordinate(int x, int y) {
        this.xVar = x;
        this.yVar = y;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Coordinate) {
            return (this.xVar == ((Coordinate) object).getX()
                    && this.yVar == ((Coordinate) object).getY());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public int getX() {
        return xVar;
    }

    public int getY() {
        return yVar;
    }
}
