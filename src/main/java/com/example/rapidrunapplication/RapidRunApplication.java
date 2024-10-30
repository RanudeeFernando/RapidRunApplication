package com.example.rapidrunapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class RapidRunApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Loading the main FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(RapidRunApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1100,700);

        // Set application logo
        Image icon = new Image(Objects.requireNonNull(RapidRunApplication.class.getResourceAsStream("images/icon.png")));
        stage.getIcons().add(icon);

        // Setting the stage title and scene
        stage.setTitle("Rapid Run Horse Race Management System");
        stage.setScene(scene);

        // Disable resizing of the stage
        stage.setResizable(false);

        // Displaying the stage
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}