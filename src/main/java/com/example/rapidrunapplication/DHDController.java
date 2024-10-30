package com.example.rapidrunapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class DHDController implements NavigationController, AlertController, Clearable{

    // FXML element for horse ID field
    @FXML
    private TextField horseIdField;

    // Method to handle navigation to the main screen
    @FXML
    @Override
    public void handleGoToMain(ActionEvent event) {
        try {
            MenuItem menuItem = (MenuItem) event.getSource();
            Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));

            // Get the current stage
            Stage stage = (Stage) menuItem.getParentPopup().getOwnerWindow();

            // Create a new scene and set the root
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to delete horse details by horse ID
    public static String deleteHorseDetails(int horseId) {
        if (RACEController.raceStarted) {
            return "Sorry, you cannot delete horse details. The race has already started.";
        }

        for (int i = 0; i < AHDController.registeredHorses.size(); i++) {
            if (AHDController.registeredHorses.get(i).getId() == horseId) {
                AHDController.registeredHorses.remove(i);
                decrementHorseCount();

                return "Horse details deleted successfully!";
            }
        }
        return "Horse not found. Please enter a registered Horse ID.";
    }

    // Method to delete horse details in GUI
    @FXML
    private void deleteHorseDetails() {
        if (RACEController.raceStarted) {
            showAlert("Sorry, you cannot delete horse details. The race has already started.");
            clearFields();
            return;
        }
        try {
            int horseId = Integer.parseInt(horseIdField.getText());
            String result = deleteHorseDetails(horseId);
            showAlert(result);
        } catch (NumberFormatException e) {
            showAlert("Please enter a valid integer for Horse ID.");
        }
        clearFields();
    }


    // Method to decrement the horse count
    public static void decrementHorseCount() {
        AHDController.horseCount--;
    }


    // Method to clear the horse ID field
    @Override
    public void clearFields() {
        horseIdField.clear();
    }


    // Method to show an alert box
    @Override
    public void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Delete Horse Details");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
