package com.example.rapidrunapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class VHDController implements NavigationController, AlertController{

    // Flag to track whether data has been refreshed
    private boolean dataRefreshed = false;

    // FXML elements
    @FXML
    private TableView<Horse> horseTable;

    @FXML
    private TableColumn<Horse, Integer> horseIdColumn;

    @FXML
    private TableColumn<Horse, String> horseNameColumn;

    @FXML
    private TableColumn<Horse, String> jockeyNameColumn;

    @FXML
    private TableColumn<Horse, Integer> horseAgeColumn;

    @FXML
    private TableColumn<Horse, String> horseBreedColumn;

    @FXML
    private TableColumn<Horse, String> horseRaceRecordColumn;

    @FXML
    private TableColumn<Horse, String> horseGroupColumn;

    @FXML
    private TableColumn<Horse, Image> horseImageColumn;


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

    // Defining a function to sort the registered horses by their Horse ID
    public void sortRegisteredHorses() {
        for (int i = 0; i < AHDController.registeredHorses.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < AHDController.registeredHorses.size(); j++) {
                if (AHDController.registeredHorses.get(j).getId() < AHDController.registeredHorses.get(minIndex).getId()) {
                    minIndex = j;
                }
            }
            // Swap horses at i and minIndex
            Horse temporary = AHDController.registeredHorses.get(i);
            AHDController.registeredHorses.set(i, AHDController.registeredHorses.get(minIndex));
            AHDController.registeredHorses.set(minIndex, temporary);
        }
    }

    // Initialize method called after FXML file loaded to add values to the table view
    @FXML
    private void initialize() {
        horseIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        horseNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        jockeyNameColumn.setCellValueFactory(new PropertyValueFactory<>("jockeyName"));
        horseAgeColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        horseBreedColumn.setCellValueFactory(new PropertyValueFactory<>("breed"));
        horseRaceRecordColumn.setCellValueFactory(new PropertyValueFactory<>("raceRecord"));
        horseGroupColumn.setCellValueFactory(new PropertyValueFactory<>("group"));
        horseImageColumn.setCellValueFactory(new PropertyValueFactory<>("thumbnailImage"));

        horseImageColumn.setCellFactory(parameter -> new TableCell<>() {
            private final ImageView imageView = new ImageView();
            {
                imageView.setFitHeight(100);
                imageView.setFitWidth(150);
                setGraphic(imageView);
            }
            @Override
            protected void updateItem(Image item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    imageView.setImage(null);
                } else {
                    imageView.setImage(item);
                }
            }
        });
    }


    // Function to populate the table with sorted horse details
    public void populateTable() {
        if (AHDController.registeredHorses.isEmpty()) {
            showAlert("No horses have been registered yet.");
        } else if (!dataRefreshed) {
            sortRegisteredHorses();
            horseTable.getItems().setAll(AHDController.registeredHorses);
            dataRefreshed = true;
        } else {
            showAlert("This is already the latest version of Registered Horses' Details.");
        }
    }

    // Method to show alert boxes
    @Override
    public void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Viewing Horse Details");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
