package ru.nsu.romankin.snake;


/**
 * Class representing a coordinate on a field.
 */
public class Coordinate {

    private int xvar;
    private int yvar;

    /**
     * Class constructor.
     */
    public Coordinate(int x, int y) {
        this.xvar = x;
        this.yvar = y;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Coordinate) {
            return (this.xvar == ((Coordinate) object).getX()
                    && this.yvar == ((Coordinate) object).getY());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public int getX() {
        return xvar;
    }

    public int getY() {
        return yvar;
    }
}
