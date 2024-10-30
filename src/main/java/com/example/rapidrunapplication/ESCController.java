package com.example.rapidrunapplication;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

public class ESCController implements NavigationController, AlertController{

    // Method to handle navigation to the main screen
    @FXML
    @Override
    public void handleGoToMain(ActionEvent event) {
        try {
            MenuItem menuItem = (MenuItem) event.getSource();
            Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));

            // Getting the current stage
            Stage stage = (Stage) menuItem.getParentPopup().getOwnerWindow();

            // Creating a new scene and setting the root
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Method to exit the program
    @FXML
    private void exitProgram() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Exit");
        alert.setHeaderText(null);
        alert.setContentText("Do you wish to exit the program?");

        // Adding buttons to the alert confirmation box
        ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(yesButton, cancelButton);

        // Show the alert and wait for user response
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        // If the user clicks "Yes", the user exits the program
        if (result == yesButton) {
            showAlert("You have chosen to exit the program. Thank you for using Rapid Run. See you next time!");
            Platform.exit();
        }
    }

    // Method to show alert box
    @Override
    public void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Exit Program");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



}
