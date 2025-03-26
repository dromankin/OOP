package ru.nsu.romankin.snake;

import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller {
    @FXML private Canvas gameCanvas;
    @FXML private Text gameStatusText;

    private Model model;
    private AnimationTimer gameLoop;
    private final int cellSize = 20;
    private GraphicsContext gc;
    private BooleanProperty gameStatus = new SimpleBooleanProperty(true);

    @FXML
    public void initialize() {
        gc = gameCanvas.getGraphicsContext2D();
        int width = (int)(gameCanvas.getWidth() / cellSize);
        int height = (int)(gameCanvas.getHeight() / cellSize);
        gameCanvas.setFocusTraversable(true);
        gameCanvas.setOnKeyPressed(this::handleKeyPressed);
        gameStatusText.visibleProperty().bind(gameStatus);
        model = new Model(width, height, 5, 10);
        startGame();
    }

    private void startGame() {
        gameLoop = new AnimationTimer() {
            private long lastTick = 0;

            @Override
            public void handle(long now) {
                if (lastTick == 0) {
                    lastTick = now;
                    return;
                }

                if (now - lastTick > 100000000) {
                    lastTick = now;
                    updateGame();
                    draw();
                }
            }
        };
        gameLoop.start();
    }

    private void draw() {
        clearScreen();
        drawSnake();
        drawFood();
        drawBorders();
    }



    private void clearScreen() {
        gc.setFill(Color.LIGHTGREEN);
        gc.fillRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
    }

    private void drawSnake() {
        for (int i = 0; i < model.getSnake().getLength(); i++) {
            Coordinate p = model.getSnake().getSnakeBody().get(i);
            gc.setFill(i == 0 ? Color.BLUE : Color.LIGHTSEAGREEN);
            gc.fillRect(p.getX() * cellSize, p.getY() * cellSize, cellSize, cellSize);
        }
    }

    private void drawFood() {
        gc.setFill(Color.RED);
        for (Coordinate p : model.getFood()) {
            gc.fillRect(p.getX() * cellSize, p.getY() * cellSize, cellSize, cellSize);
        }
    }

    private void drawBorders() {
        gc.setStroke(Color.YELLOW);
        gc.setLineWidth(1);
        gc.strokeRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
    }

    @FXML
    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            restartGame();
            event.consume();
            return;
        }
        KeyCode code = event.getCode();
        if (code == KeyCode.UP && model.getDirection() != Direction.DOWN) {
            model.setDirection(Direction.UP);
        } else if (code == KeyCode.DOWN && model.getDirection() != Direction.UP) {
            model.setDirection(Direction.DOWN);
        } else if (code == KeyCode.LEFT && model.getDirection() != Direction.RIGHT) {
            model.setDirection(Direction.LEFT);
        } else if (code == KeyCode.RIGHT && model.getDirection() != Direction.LEFT) {
            model.setDirection(Direction.RIGHT);
        }
    }

    private void updateGame() {
        if (model.isGameOver() || model.isWon()) {
            gameStatusText.setText(model.isWon() ?
                    "You win! Score: " + (model.getSnake().getLength() - 1) + " Press ENTER to restart":
                    "Game over! Press ENTER to restart");
            gameLoop.stop();
            return;
        }

        model.movement();
        gameStatusText.setText("Score: " + (model.getSnake().getLength() - 1));
    }

    private void restartGame() {
        if (gameLoop != null) {
            gameLoop.stop();
            gameLoop = null;
        }

        initialize();

        gameCanvas.requestFocus();
    }
}
