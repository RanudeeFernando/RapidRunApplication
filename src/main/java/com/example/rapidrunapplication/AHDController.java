package com.example.rapidrunapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AHDController implements NavigationController, AlertController, Clearable{

    // List to store registered horses
    public static List<Horse> registeredHorses = new ArrayList<>();

    // Count of registered horses
    public static int horseCount = 0;

    // Maximum number of horses that can be registered
    public static final int MAX_HORSES = 20;

    // FXML fields for horse registration form
    @FXML
    public TextField horseIdField;

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

    @FXML
    private Button chooseImageButton;


    // Method to handle navigation to the main screen
    @FXML
    @Override
    public void handleGoToMain(ActionEvent event) {
        try {
            MenuItem menuItem = (MenuItem) event.getSource();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main.fxml")));

            Stage stage = (Stage) menuItem.getParentPopup().getOwnerWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to register horse details
    public static String registerHorseDetails(Integer horseId, String horseName, String jockeyName, Integer age, String breed,
                                              String raceRecord, String group, Image image) {
        // Check if race has already started
        if (RACEController.raceStarted) {
            return "Sorry, the registration is closed. The race has already started.";
        }
        // Check if maximum horse limit has been reached
        if (horseCount >= MAX_HORSES) {
            return "You have reached the maximum limit for Registered Horses.\nYou cannot register more horses beyond this limit.";
        }
        // Validating horse ID
        try {
            Integer.parseInt(String.valueOf(horseId));
        } catch (NumberFormatException e) {
            return "Please enter a valid integer for Horse ID.";
        }

        if (horseId <= 0) {
            return "Please enter a positive integer for Horse ID.";
        }
        // Validating horse age
        try {
            Integer.parseInt(String.valueOf(age));
        } catch (NumberFormatException e) {
            return "Please enter a valid integer for Age.";
        }

        if (age <= 0 || age > 50) {
            return "The entered age " + age + " is incorrect. Please enter a valid age.";
        }
        // Validating the other fields
        if (horseName == null || horseName.isEmpty()) {
            return "Horse Name Field cannot be empty.";
        }

        if (jockeyName == null || jockeyName.isEmpty()) {
            return "Jockey Name Field cannot be empty.";
        }

        if (breed == null || breed.isEmpty()) {
            return "Breed Field cannot be empty.";
        }

        if (raceRecord == null || raceRecord.isEmpty()) {
            return "Race Record Field cannot be empty.";
        }

        if (group == null || group.isEmpty()) {
            return "Group Field cannot be empty.";
        }

        if (image == null) {
            return "Image Field cannot be empty.";
        }

        // Check if horse ID is unique
        if (!isIdUnique(horseId)) {
            return "This Horse ID already exists. Please enter a unique Horse ID!";
        }
        // Check if group is full
        if (isGroupFull(group)) {
            return "Group " + group + " is full. You cannot register more horses in this group.";
        }
        // Registering the horse when all inputs are valid
        Horse newHorse = new Horse(horseId, horseName, jockeyName, age, breed, raceRecord, group, image, image.getUrl());
        registeredHorses.add(newHorse);
        horseCount++;
        return "Horse has been successfully registered!";
    }

    // Method to register horse details from the form
    @FXML
    public void registerHorseDetails() {
        if (RACEController.raceStarted) {
            showAlert("Sorry, the registration is closed. The race has already started.");
            clearFields();
            return;
        }
        // Try - catch block in case any exceptions occur when horse ID and age is entered
        try {
            int horseId = Integer.parseInt(horseIdField.getText());
            String horseName = horseNameField.getText();
            String jockeyName = jockeyNameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String breed = breedField.getValue();
            String raceRecord = raceRecordField.getText();
            String group = groupField.getValue();
            Image image = horseImageView.getImage();

            String resultMessage = registerHorseDetails(horseId, horseName, jockeyName, age, breed, raceRecord, group, image);
            showAlert(resultMessage);
            if (resultMessage.equals("You have reached the maximum limit for Registered Horses.\nYou cannot register more horses beyond this limit.")) {
                clearFields();
            }

            if (resultMessage.equals("Horse has been successfully registered!")) {
                clearFields();
            }

        } catch (NumberFormatException e) {
            showAlert("Please enter valid integer values for Horse ID and Age.");
        }
    }

    // Method to check if the Horse ID is Unique
    private static boolean isIdUnique(int id) {
        for (Horse horse : registeredHorses) {
            if (horse.getId() == id) {
                return false;
            }
        }
        return true;
    }

    // Method to check if horse group is full
    private static boolean isGroupFull(String group) {
        int groupCount = 0;
        for (Horse horse : registeredHorses) {
            if (horse.getGroup().equals(group)) {
                groupCount++;
            }
        }
        return groupCount >= 5;
    }

    // Method to clear fields in registration form
    @Override
    public void clearFields() {
        horseIdField.clear();
        horseNameField.clear();
        jockeyNameField.clear();
        ageField.clear();
        breedField.getSelectionModel().clearSelection();
        raceRecordField.clear();
        groupField.getSelectionModel().clearSelection();
        horseImageView.setImage(null);
    }

    // Method for setting the chooseImageButton with the functionality of selecting and displaying image
    @FXML
    private void initializeImageButton() {
        chooseImageButton.setOnAction(event -> {
            File imageFile = chooseImageFile();
            if (imageFile != null) {
                Image image = new Image(imageFile.toURI().toString());
                horseImageView.setImage(image);
            }
        });
    }

    // Method to open file chooser and select an image file
    private File chooseImageFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.jpeg")
        );
        return fileChooser.showOpenDialog(new Stage());
    }

    // Method for Initializing choice box for horse groups
    @FXML
    public void initializeGroupChoiceBox() {
        ObservableList<String> choices = FXCollections.observableArrayList("A", "B", "C", "D");
        groupField.setItems(choices);
    }

    // Method for Initializing choice box for horse breeds
    @FXML
    public void initializeBreedChoiceBox() {
        ObservableList<String> breeds = FXCollections.observableArrayList("Thoroughbred", "Standardbred", "Arabian horse", "Quarter horse", "Other");
        breedField.setItems(breeds);
    }

    // Method to show alert box
    @Override
    public void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Horse Registration");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }




}
