package ru.nsu.romankin.snake;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Class representing View in MVC.
 */
public class View {
    private final GraphicsContext gc;
    private final Canvas gameCanvas;
    private Model model;
    private final int cellSize;

    /**
     * Class constructor.
     */
    public View (GraphicsContext gc, Canvas gameCanvas, Model model, int cellSize) {
        this.gc = gc;
        this.gameCanvas = gameCanvas;
        this.model = model;
        this.cellSize = cellSize;
    }

    public void draw() {
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
}
