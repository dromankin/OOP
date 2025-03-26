package ru.nsu.romankin.snake;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model {
    private final int width;
    private final int height;
    private final int foodCount;
    private final int winCount;

    private Snake snake;
    private List<Food> food = new ArrayList<>();
    private List<Wall> wall = new ArrayList<>();
    private Direction direction;
    private boolean gameOver;
    private boolean won;
    private Random random = new Random();


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isWon() {
        return won;
    }

    public boolean isGameOver() {
        return gameOver;
    }
    public int getWinCount() {
        return winCount;
    }
    public Model(int width, int height, int foodCount, int winCount) {
        this.width = width;
        this.height = height;
        this.foodCount = foodCount;
        this.winCount = winCount;
        snake = new Snake(width/2, height/2);
        direction = Direction.RIGHT;
        gameOver = false;
        won = false;
        for (int i = 0; i < foodCount; i++) {
            spawnFood();
        }
    }


    private boolean checkCollision(Coordinate head) {
        if (head.getX() >= width || head.getX() < 0 || head.getY() >= height || head.getY() < 0) {
            return true;
        }
        for (Coordinate coordinate : snake.getSnakeBody()) {
            if (coordinate.equals(head)) {
                return true;
            }
        }
        return false;
    }

    private Coordinate calculateNewHead(Coordinate head) {
        switch (direction) {
            case UP: return new Coordinate(head.getX(), head.getY() - 1);
            case DOWN: return new Coordinate(head.getX(), head.getY() + 1);
            case LEFT: return new Coordinate(head.getX() - 1, head.getY());
            case RIGHT: return new Coordinate(head.getX() + 1, head.getY());
            default: throw new IllegalStateException();
        }
    }

    private boolean eatFood(Coordinate head) {
        for (int i = 0; i < food.size(); i++) {
            if (food.get(i).equals(head)) {
                food.remove(i);
                spawnFood();
                return true;
            }
        }
        return false;
    }
    private void spawnFood() {
        Food newFood = null;
        boolean validPosition = false;

        while (!validPosition) {
            validPosition = true;
            newFood = new Food(random.nextInt(width), random.nextInt(height));

            for (Coordinate coordinate : snake.getSnakeBody()) {
                if (coordinate.equals(newFood)) {
                    validPosition = false;
                    break;
                }
            }

            for (Food food : food) {
                if (food.equals(newFood)) {
                    validPosition = false;
                    break;
                }
            }
        }


        food.add(newFood);
    }

    public void movement() {
        Coordinate head = snake.getSnakeHead();
        Coordinate newHead = calculateNewHead(head);

        if (checkCollision(newHead)) {
            gameOver = true;
            return;
        }

        snake.setSnakeHead(newHead);

        if (!eatFood(newHead)) {
            snake.removeTail();
        }

        if (snake.getLength() >= winCount) {
            won = true;
        }

    }

    public Snake getSnake() {
        return snake;
    }

    public List<Food> getFood() {
        return food;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }
}
