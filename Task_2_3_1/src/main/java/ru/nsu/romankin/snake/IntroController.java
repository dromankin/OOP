package ru.nsu.romankin.snake;

import java.io.IOException;
import java.net.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;


/**
 * Controller for intro screen with difficulty select.
 */

public class IntroController {
    @FXML
    ObservableList<String> list = FXCollections.observableArrayList("Easy", "Normal", "Hard");
    @FXML
    private ChoiceBox<String> choiceBox = new ChoiceBox<>(list);
    private Stage stage = new Stage();
    private long speed = Controller.NORMAL_SPEED;

    /**
     * User sets speed and intro controller pass it to game controller.
     */
    public void setDifficulty(ActionEvent event) {
        String difficulty = choiceBox.getValue();
        switch (difficulty) {
            case "Easy": speed = Controller.EASY_SPEED;
                break;
            case "Normal": speed = Controller.NORMAL_SPEED;
                break;
            case "Hard": speed = Controller.HARD_SPEED;
                break;
            default: speed = Controller.NORMAL_SPEED;
                break;
        }
    }

    @FXML
    private void start() throws IOException {
        URL fxmlUrl = getClass().getResource("/game.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        Controller controller = loader.getController();
        controller.setDifficulty(speed);
        stage.setTitle("Snake");
        stage.setResizable(false);
        stage.show();
    }
}
