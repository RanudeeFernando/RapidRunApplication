package com.example.rapidrunapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class SHDController implements NavigationController, AlertController{

    // Creating ArrayLists for each group
    public List<Horse> groupA = new ArrayList<>();
    public List<Horse> groupB = new ArrayList<>();
    public List<Horse> groupC = new ArrayList<>();
    public List<Horse> groupD = new ArrayList<>();


    // Method to handle navigation to the main screen
    @FXML
    @Override
    public void handleGoToMain(ActionEvent event) {
        try {
            MenuItem menuItem = (MenuItem) event.getSource();
            Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));

            // Getting the current stage
            Stage stage = (Stage) menuItem.getParentPopup().getOwnerWindow();

            // Creating a new scene and set the root
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to sort registered horses into their respective groups
    public void sortHorsesByGroup() {

        // Adding horses to the corresponding group ArrayList
        for (Horse horse : AHDController.registeredHorses) {
            switch (horse.getGroup()) {
                case "A":
                    groupA.add(horse);
                    break;
                case "B":
                    groupB.add(horse);
                    break;
                case "C":
                    groupC.add(horse);
                    break;
                case "D":
                    groupD.add(horse);
                    break;
                default:
                    System.out.println("Unexpected group value.");
                    break;
            }
        }

        // Sorting horses in each group by ID
        sortRegisteredHorses(groupA);
        sortRegisteredHorses(groupB);
        sortRegisteredHorses(groupC);
        sortRegisteredHorses(groupD);


    }

    // Method to save horse details to a text file
    @FXML
    public void saveHorseDetails() {
        sortHorsesByGroup();

        // Write sorted horse details to a text file
        try (PrintWriter writer = new PrintWriter(new FileWriter("horse_details.txt"))) {

            writeGroupDetails(writer, "Group A", groupA);
            writeGroupDetails(writer, "Group B", groupB);
            writeGroupDetails(writer, "Group C", groupC);
            writeGroupDetails(writer, "Group D", groupD);

            showAlert("Horse details have been successfully saved to \"horse_details.txt\"");


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    // Method to sort registered horses by their horse ID is ascending order
    public static void sortRegisteredHorses(List<Horse> horses) {
        for (int i = 0; i < horses.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < horses.size(); j++) {
                if (horses.get(j).getId() < horses.get(minIndex).getId()) {
                    minIndex = j;
                }
            }
            // Swap horses at i and minIndex
            Horse temporary = horses.get(i);
            horses.set(i, horses.get(minIndex));
            horses.set(minIndex, temporary);
        }
    }

    // Method to write horse details to a text file
    private static void writeGroupDetails(PrintWriter writer, String groupName, List<Horse> group) {
        writer.println(groupName + " :");
        for (Horse groupHorse : group) {
            // Writing Horse details to file
            writer.println(groupHorse.toString());
        }
        writer.println(); // Empty line for separation
    }

    // Method to show alert box
    @Override
    public void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Save Horse Details");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
