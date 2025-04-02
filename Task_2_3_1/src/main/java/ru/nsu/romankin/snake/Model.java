package ru.nsu.romankin.snake;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class representing Model in MVC, contains main game logic.
 */
public class Model {
    private final int width;
    private final int height;
    private final int winCount;

    private Snake snake;
    private List<Food> food = new ArrayList<>();
    private boolean gameOver;
    private boolean won;
    private Random random = new Random();


    public int getWidth() {
        return width;
    }

    public int getScore() {
        return getSnake().getLength() - 1;
    }

    public int getFoodCount() {
        return food.size();
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

    /**
     * Class constructor.
     */
    public Model(int width, int height, int foodCount, int winCount) {
        this.width = width;
        this.height = height;
        this.winCount = winCount;
        snake = new Snake(width / 2, height / 2);
        gameOver = false;
        won = false;
        for (int i = 0; i < foodCount; i++) {
            spawnFood();
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

    /**
     * Movement of snake in model.
     */
    public void movement() {


        if (!snake.move(snake.getDirection(), this)) {
            gameOver = true;
            return;
        }

        if (eatFood(snake.getSnakeHead())) {
            snake.grow();
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



}
