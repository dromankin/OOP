package ru.nsu.romankin.snake;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.net.URL;

/**
 * Main class extending application.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL fxmlUrl = getClass().getResource("/intro.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Snake");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Main method.
     */
    public static void main(String[] args) {
        launch(args);
    }
}