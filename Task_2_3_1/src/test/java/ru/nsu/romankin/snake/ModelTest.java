package ru.nsu.romankin.snake;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import org.junit.jupiter.api.Test;




class ModelTest {
    @Test
    void snakeInitTest() {
        Snake snake = new Snake(12, 12);
        assertEquals(snake.getSnakeBody().size(), 1);
    }

    @Test
    void modelInitTest() {
        Random rand = new Random();
        int width = rand.nextInt(100) + 1;
        int height = rand.nextInt(100) + 1;
        int foodCount = rand.nextInt(100) + 1;
        int winCount = rand.nextInt(100) + 1;
        Model model = new Model(width, height, foodCount, winCount);
        assertTrue(model.getWidth() == width && model.getHeight() == height
                && model.getWinCount() == winCount && model.getFoodCount() == foodCount
                && !model.isWon());
    }

    @Test
    void collisionGameOverTest() {
        Model model = new Model(11, 11, 5, 3);
        for (int i = 0; i < 6; i++) {
            model.movement();
        }
        assertTrue(model.isGameOver());
    }

    @Test
    void growTest() {
        Snake snake = new Snake(12, 12);
        Random random = new Random();
        int num = random.nextInt(1000);
        for (int i = 0; i < num; i++) {
            snake.grow();
        }
        assertEquals(snake.getSnakeBody().size(), num + 1);
    }

    @Test
    void directionTest() {
        Model model = new Model(20, 20, 5, 3);
        Snake snake = new Snake(12,12);
        snake.setDirection(Direction.RIGHT);
        snake.move(snake.getDirection(), model);
        snake.move(snake.getDirection(), model);
        assertTrue(snake.getSnakeHead().getX() == 14 && snake.getSnakeHead().getY() == 12);
        snake.setDirection(Direction.UP);
        snake.move(snake.getDirection(), model);
        snake.move(snake.getDirection(), model);
        assertTrue(snake.getSnakeHead().getX() == 14 && snake.getSnakeHead().getY() == 10);
        snake.setDirection(Direction.LEFT);
        snake.move(snake.getDirection(), model);
        snake.move(snake.getDirection(), model);
        snake.move(snake.getDirection(), model);
        assertTrue(snake.getSnakeHead().getX() == 11 && snake.getSnakeHead().getY() == 10);
        snake.setDirection(Direction.DOWN);
        snake.move(snake.getDirection(), model);
        assertTrue(snake.getSnakeHead().getX() == 11 && snake.getSnakeHead().getY() == 11);
    }

    @Test
    void wrongDirectionTest() {
        Model model = new Model(20, 20, 5, 3);
        Snake snake = new Snake(12, 12);
        snake.setDirection(Direction.RIGHT);
        snake.move(snake.getDirection(), model);
        snake.move(snake.getDirection(), model);
        assertTrue(snake.getSnakeHead().getX() == 14 && snake.getSnakeHead().getY() == 12);
        snake.setDirection(Direction.LEFT);
        snake.move(snake.getDirection(), model);
        snake.move(snake.getDirection(), model);
        assertTrue(snake.getSnakeHead().getX() == 16 && snake.getSnakeHead().getY() == 12);
        snake.setDirection(Direction.UP);
        snake.move(snake.getDirection(), model);
        snake.move(snake.getDirection(), model);
        snake.move(snake.getDirection(), model);
        assertTrue(snake.getSnakeHead().getX() == 16 && snake.getSnakeHead().getY() == 9);
        snake.setDirection(Direction.DOWN);
        snake.move(snake.getDirection(), model);
        assertTrue(snake.getSnakeHead().getX() == 16 && snake.getSnakeHead().getY() == 8);
    }
}