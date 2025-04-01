package ru.nsu.romankin.snake;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing snake.
 */

public class Snake {
    private List<Coordinate> snake = new ArrayList<>();
    private Direction direction;
    private Direction lastDirection;

    /**
     * Class constructor.
     *
     * @param startX - x-coordinate
     *
     * @param startY - y-coordinate
     */
    public Snake(int startX, int startY) {
        snake.add(new Coordinate(startX, startY));
        direction = Direction.RIGHT;
        lastDirection = Direction.RIGHT;
    }

    public int getLength() {
        return snake.size();
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        if (direction == Direction.RIGHT && lastDirection != Direction.LEFT) {
            this.direction = direction;
        } else if (direction == Direction.LEFT && lastDirection != Direction.RIGHT) {
            this.direction = direction;
        } else if (direction == Direction.UP && lastDirection != Direction.DOWN) {
            this.direction = direction;
        } else if (direction == Direction.DOWN && lastDirection != Direction.UP) {
            this.direction = direction;
        }

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

    /**
     * Function responsible for growing after eating food.
     */
    public void grow() {
        Coordinate tail = snake.get(snake.size() - 1);
        snake.add(snake.size() - 1, new Coordinate(tail.getX(), tail.getY()));

    }

    /**
     * Function responsible for moving snake by direction and model.
     */
    public boolean move(Direction direction, Model model) {
        lastDirection = direction;
        Coordinate newHead = calculateNewHead(getSnakeHead(), getDirection());
        if (checkCollision(model, newHead)) {
            return false;
        }
        setSnakeHead(newHead);
        snake.remove(snake.size() - 1);
        return true;

    }

    /**
     * Function returning true if collision happened, false else.
     */
    public boolean checkCollision(Model model, Coordinate head) {

        if (head.getX() >= model.getWidth() || head.getX() < 0 ||
            head.getY() >= model.getHeight() || head.getY() < 0) {
            return true;
        }
        for (Coordinate coordinate : getSnakeBody()) {
            if (coordinate.equals(head)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Function that calculates new head by direction.
     */
    public Coordinate calculateNewHead(Coordinate head, Direction direction) {
        switch (direction) {
            case UP: return new Coordinate(head.getX(), head.getY() - 1);
            case DOWN: return new Coordinate(head.getX(), head.getY() + 1);
            case LEFT: return new Coordinate(head.getX() - 1, head.getY());
            case RIGHT: return new Coordinate(head.getX() + 1, head.getY());
            default: throw new IllegalStateException();
        }
    }

}
