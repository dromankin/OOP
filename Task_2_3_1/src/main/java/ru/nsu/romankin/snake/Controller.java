package ru.nsu.romankin.snake;

import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;


public class Controller {
    @FXML private Canvas gameCanvas;
    @FXML private Text gameStatusText;
    public final static long HARD_SPEED = 40_000_000;
    public final static long NORMAL_SPEED = 100_000_000;
    public final static long EASY_SPEED = 160_000_000;
    public static final int WIN_COUNT = 5 + 1;
    private Model model;
    private AnimationTimer gameLoop;
    private final int cellSize = 20;
    private GraphicsContext gc;
    private BooleanProperty gameStatus = new SimpleBooleanProperty(true);
    private View view;
    private long speed = NORMAL_SPEED;
    private int record = 0;
    private boolean played = false;
    @FXML
    public void initialize() {
        gc = gameCanvas.getGraphicsContext2D();
        int width = (int)(gameCanvas.getWidth() / cellSize);
        int height = (int)(gameCanvas.getHeight() / cellSize);
        gameCanvas.setFocusTraversable(true);
        gameCanvas.setOnKeyPressed(this::handleKeyPressed);
        gameStatusText.visibleProperty();
        model = new Model(width, height, 5, WIN_COUNT);
        view = new View(gc, gameCanvas, model, cellSize);
        startGame();
    }

    public void setDifficulty(long speed) {
        this.speed = speed;
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
                if (now - lastTick > speed) {
                    lastTick = now;
                    updateGame();

                }
                view.draw();



            }
        };
        gameLoop.start();
    }


    long prevTime = 0;
    @FXML
    private void handleKeyPressed(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {
            restartGame();
            event.consume();
            return;
        }
        KeyCode code = event.getCode();
        if (code == KeyCode.UP) {
            model.getSnake().setDirection(Direction.UP);
        } else if (code == KeyCode.DOWN) {
            model.getSnake().setDirection(Direction.DOWN);
        } else if (code == KeyCode.LEFT) {
            model.getSnake().setDirection(Direction.LEFT);
        } else if (code == KeyCode.RIGHT) {
            model.getSnake().setDirection(Direction.RIGHT);
        }

    }

    private void updateGame() {
        model.movement();


        if (model.isGameOver() || model.isWon()) {

            if (record < model.getSnake().getLength() - 1) {
                record = model.getSnake().getLength() - 1;
            }
            gameStatusText.setText(model.isWon() ?
                    "You win! Score: " + (model.getSnake().getLength() - 1) + " Press ENTER to restart":
                    "Game over! Press ENTER to restart");
            gameLoop.stop();
            played = true;
            return;
        }

        if (!played) {
            gameStatusText.setText("Score: " + (model.getSnake().getLength() - 1));
        } else {

            gameStatusText.setText("Score: " + (model.getSnake().getLength() - 1) + "\t\tRecord: " + record);

        }
    }

    private void restartGame() {
        if (gameLoop != null) {
            gameLoop.stop();
            gameLoop = null;
        }

        initialize();

    }
}
