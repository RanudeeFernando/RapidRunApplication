package com.example.rapidrunapplication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Objects;

public class MainController {
    @FXML
    private Button addHorseDetailsButton;

    @FXML
    private Button deleteHorseDetailsButton;

    @FXML
    private Button updateHorseDetailsButton;

    @FXML
    private Button viewHorseDetailsButton;

    @FXML
    private Button saveHorseDetailsButton;

    @FXML
    private Button conductingRaceButton;


    @FXML
    private Button exitButton;

    @FXML
    private ImageView imageViewLogo;

    public void initialize() {
        // Loading the image
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/icon.png")));
        // Setting the image to the ImageView
        imageViewLogo.setImage(image);
    }


    // Handles action when the addHorseDetailsButton button is clicked
    @FXML
    private void handleAddHorseDetailsButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ahd.fxml"));
            Parent root = loader.load();

            Scene scene = addHorseDetailsButton.getScene();
            scene.setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
            String errorMessage = "An error occurred while handling the add horse details button";
            System.out.println(errorMessage);
        }
    }

    // Handles action when the deleteHorseDetailsButton button is clicked
    @FXML
    private void handleDeleteDetailsButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dhd.fxml"));
            Parent root = loader.load();

            Scene scene = deleteHorseDetailsButton.getScene();
            scene.setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
            String errorMessage = "An error occurred while handling the delete horse details button";
            System.out.println(errorMessage);
        }
    }

    // Handles action when the updateHorseDetailsButton button is clicked
    @FXML
    private void handleUpdateDetailsButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("uhd.fxml"));
            Parent root = loader.load();

            Scene scene = updateHorseDetailsButton.getScene();
            scene.setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
            String errorMessage = "An error occurred while handling the update horse details button";
            System.out.println(errorMessage);
        }
    }

    // Handles action when the viewHorseDetailsButton is clicked
    @FXML
    private void handleViewDetailsButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("vhd.fxml"));
            Parent root = loader.load();

            Scene scene = viewHorseDetailsButton.getScene();
            scene.setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
            String errorMessage = "An error occurred while handling the view horse details button";
            System.out.println(errorMessage);
        }
    }


    // Handles action when the saveHorseDetailsButton is clicked
    @FXML
    private void handleSaveDetailsButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("shd.fxml"));
            Parent root = loader.load();

            Scene scene = saveHorseDetailsButton.getScene();
            scene.setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
            String errorMessage = "An error occurred while handling the save details button";
            System.out.println(errorMessage);
        }
    }

    // Handles action when the conductingRaceButton is clicked
    @FXML
    private void handleConductingRaceButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("race.fxml"));
            Parent root = loader.load();

            Scene scene = conductingRaceButton.getScene();
            scene.setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
            String errorMessage = "An error occurred while handling the conducting race button";
            System.out.println(errorMessage);
        }
    }

    // Handles action when the exitButton is clicked
    @FXML
    private void handleExitButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("esc.fxml"));
            Parent root = loader.load();

            Scene scene = exitButton.getScene();
            scene.setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
            String errorMessage = "An error occurred while handling the exit button";
            System.out.println(errorMessage);
        }
    }


}