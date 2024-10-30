package com.example.rapidrunapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class UHDController implements NavigationController, AlertController, Clearable{

    // FXML elements
    // FXML fields for horse update form
    @FXML
    private TextField horseIdField;

    @FXML
    public TextField horseNameField;

    @FXML
    public TextField jockeyNameField;

    @FXML
    public TextField ageField;

    @FXML
    public ChoiceBox<String> breedField;

    @FXML
    public TextField raceRecordField;

    @FXML
    public ChoiceBox<String> groupField;

    @FXML
    public ImageView horseImageView;


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

    // Method to update horse details
    public String updateHorseDetails(Horse horse, String horseName, String jockeyName, String ageText, String breed, String raceRecord) {
        // Validating fields
        if (horseName.isEmpty() || jockeyName.isEmpty() || ageText.isEmpty() || raceRecord.isEmpty() || breed == null) {
            return "Horse details were not updated. Please fill in all fields.";
        }

        // Validating the age field
        try {
            int age = Integer.parseInt(ageText);
            if (0 < age && 50 > age) {
                horse.setAge(age);
            } else {
                return "Horse details were not updated. The entered age " + age + " is incorrect. Please enter a valid age.";
            }

        } catch (NumberFormatException e) {
            return "Horse details were not updated. Please enter a valid integer for Age.";
        }

        // Updating the horse's details if all validations pass
        horse.setName(horseName);
        horse.setJockeyName(jockeyName);
        horse.setBreed(breed);
        horse.setRaceRecord(raceRecord);

        return "Horse details updated successfully!";
    }

    // Method to retrieve horse details for updating
    @FXML
    private void getUpdateHorseDetails() {
        // Checking if race has started
        if (RACEController.raceStarted) {
            showAlert("Sorry, you cannot update horse details after the race has started.");
            clearFields();
            return;
        }

        // Retrieving horse details for updating
        String horseIdText = horseIdField.getText();
        try {
            int horseId = Integer.parseInt(horseIdText);
            boolean found = false;
            for (int i = 0; i < AHDController.registeredHorses.size(); i++) {
                Horse horse = AHDController.registeredHorses.get(i);
                if (horse.getId() == horseId) {
                    found = true;
                    horseNameField.setText(horse.getName());
                    jockeyNameField.setText(horse.getJockeyName());
                    ageField.setText(Integer.toString(horse.getAge()));
                    breedField.setValue(horse.getBreed());
                    raceRecordField.setText(horse.getRaceRecord());
                    groupField.setValue(horse.getGroup());
                    groupField.setDisable(true);
                    horseImageView.setImage(horse.getThumbnailImage());
                    break;
                }
            }
            if (!found) {
                showAlert("Horse not found. Please enter a registered Horse ID.");
                clearFields();
            }
        } catch (NumberFormatException e) {
            showAlert("Please enter a valid integer for Horse ID.");
            clearFields();
        }

    }

    // Method to update horse details
    @FXML
    private void updateHorseDetails() {
        // Checking if the race has started
        if (RACEController.raceStarted) {
            showAlert("Sorry, you cannot update horse details after the race has started.");
            clearFields();
            return;
        }
        String horseIdText = horseIdField.getText();
        // Try - catch block in case any exceptions occur when entering horse ID
        try {
            int horseId = Integer.parseInt(horseIdText);
            boolean horseFound = false;
            for (int i = 0; i < AHDController.registeredHorses.size(); i++) {
                Horse horse = AHDController.registeredHorses.get(i);
                if (horse.getId() == horseId) {
                    horseFound = true;
                    String message = updateHorseDetails(horse, horseNameField.getText(), jockeyNameField.getText(), ageField.getText(), breedField.getValue(), raceRecordField.getText());
                    showAlert(message);
                    if (message.equals("Horse details updated successfully!")) {
                        clearFields();
                    }
                    break;
                }
            }
            if (!horseFound) {
                showAlert("Horse not found. Please enter a valid Horse ID.");
                clearFields();
            }
        } catch (NumberFormatException e) {
            showAlert("Please enter a valid integer for Horse ID.");
            clearFields();
        }
    }

    // Method to show alert box
    @Override
    public void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Horse Update Details");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Method to initialize the group choice box
    @FXML
    public void initializeGroupChoiceBox() {
        ObservableList<String> choices = FXCollections.observableArrayList("A", "B", "C", "D");
        groupField.setItems(choices);
    }

    // Method to initialize the breed choice box
    @FXML
    public void initializeBreedChoiceBox() {
        ObservableList<String> breeds = FXCollections.observableArrayList("Thoroughbred", "Standardbred", "Arabian horse", "Quarter horse", "Other");
        breedField.setItems(breeds);

    }

    // Method to clear all fields
    @Override
    public void clearFields() {
        horseIdField.clear();
        horseNameField.clear();
        jockeyNameField.clear();
        ageField.clear();
        breedField.setValue(null);
        raceRecordField.clear();
        groupField.setValue(null);
        horseImageView.setImage(null);
    }
}
