package com.example.rapidrunapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class RACEController implements NavigationController, AlertController{

    // The Race RACEController Class handles 4 functionalities (One functionality in each Tab)

    // 1. SDD Functionality - Responsible for reading the text file and randomly selecting 4 horses

    // FXML elements
    @FXML
    public TabPane tabPane;
    @FXML
    public Tab SDD;
    @FXML
    public Tab START;
    @FXML
    public Tab WHD;
    @FXML
    public Tab VWH;


    // FXML Table elements in Tab 1
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

    // Flag to indicate if horses have been generated
    private static boolean horsesGenerated = false;
    public static List<Horse> selectedHorses;

    // Initializing Array lists for each horse group
    public List<Horse> groupA = new ArrayList<>();
    public List<Horse> groupB = new ArrayList<>();
    public List<Horse> groupC = new ArrayList<>();
    public List<Horse> groupD = new ArrayList<>();


    // Booleans that indicate whether a random horse was selected from relevant horse group
    boolean groupASelected = false;
    boolean groupBSelected = false;
    boolean groupCSelected = false;
    boolean groupDSelected = false;


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

    // Method to read horse details from a text file
    public void readHorseDetails() {
        try (BufferedReader reader = new BufferedReader(new FileReader("horse_details.txt"))) {
            String line;
            String currentGroup = null;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("Group")) {
                    currentGroup = line.split("\\s+")[1];
                }
                else if (!line.isEmpty() && currentGroup != null) {

                    String[] parts = line.split(", ");
                    int id = Integer.parseInt(parts[0].split(": ")[1]);
                    String name = parts[1].split(": ")[1];
                    String jockeyName = parts[2].split(": ")[1];
                    int age = Integer.parseInt(parts[3].split(": ")[1]);
                    String breed = parts[4].split(": ")[1];
                    String raceRecord = parts[5].split(": ")[1];
                    String imagePath = parts[7].split(": ")[1];

                    Image thumbnailImage = new Image(imagePath);


                    Horse horse = new Horse(id, name, jockeyName, age, breed, raceRecord, currentGroup, thumbnailImage, imagePath);

                    // Add the horse to the corresponding group
                    switch (currentGroup) {
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
                    }
                }
            }


        } catch (IOException e) {
            System.out.println("An error occurred while attempting read the text file.");
        }
    }

    // Method to select random horses from each group
    void selectRandomHorsesFromEachGroup() {
        Random random = new Random();
        Horse randomHorseGroupA = null;
        Horse randomHorseGroupB = null;
        Horse randomHorseGroupC = null;
        Horse randomHorseGroupD = null;

        if (!groupASelected) {
            randomHorseGroupA = groupA.get(random.nextInt(groupA.size()));
            groupASelected = true;
        }

        if (!groupBSelected) {
            randomHorseGroupB = groupB.get(random.nextInt(groupB.size()));
            groupBSelected = true;
        }

        if (!groupCSelected) {
            randomHorseGroupC = groupC.get(random.nextInt(groupC.size()));
            groupCSelected = true;
        }

        if (!groupDSelected) {
            randomHorseGroupD = groupD.get(random.nextInt(groupD.size()));
            groupDSelected = true;
        }

        assert randomHorseGroupA != null;
        assert randomHorseGroupB != null;
        assert randomHorseGroupC != null;
        assert randomHorseGroupD != null;

        selectedHorses = List.of(randomHorseGroupA, randomHorseGroupB, randomHorseGroupC, randomHorseGroupD);

    }

    // Method to verify random horse details and populate the table
    public boolean verifyRandomHorseDetails() {
        if (!groupASelected || !groupBSelected || !groupCSelected || !groupDSelected) {
            // Call readHorseDetails() to populate the group lists if not already done
            readHorseDetails();

            // Check if each group has exactly 5 horses
            if (groupA.size() != 5 || groupB.size() != 5 || groupC.size() != 5 || groupD.size() != 5) {
                showAlert("Each group should have 5 horses. Please populate the text file properly to simulate the random draw!");
                return false;
            }

            selectRandomHorsesFromEachGroup();
            initializeSDDTable();
            horseTable.getItems().setAll(selectedHorses);

            horsesGenerated = true;
            return true;
        }

        // Return false if all groups are already selected
        return false;
    }

    // Method to initialize random draw
    @FXML
    private void initializeRandomDraw() {
        if (!horsesGenerated) {
            if (verifyRandomHorseDetails()) {
                System.out.println();
            }
        } else {
            showAlert("Random horses have already been generated. You cannot generate them again for this round.");
        }
    }

    // Method to initialize table in SDD tab
    @FXML
    public void initializeSDDTable() {
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




    // 2. START Functionality - Responsible for starting the race

    public static boolean raceStarted = false;

    // Method to start the race
    @FXML
    private void startRace() {
        if (selectedHorses == null || selectedHorses.isEmpty()) {
            showAlert("Please select horses from the random draw before starting the race.");
            return;
        }

        // Alert box to get race start confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Start");
        alert.setHeaderText(null);
        alert.setContentText("Do you wish to start the race?");

        ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(yesButton, cancelButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == yesButton) {

            if (!raceStarted) {
                raceStarted = true;
                showAlert("The race has successfully started!");


            } else {
                showAlert("The race has already started. You cannot start again!");

            }
        }
    }


    // 3. WHD Functionality - Responsible for assigning random race times to selected horses and declaring the winners

    // FXML Table elements
    @FXML
    private TableView<Horse> horseTable2;

    @FXML
    private TableColumn<Horse, String> horsePlaceColumn;

    @FXML
    private TableColumn<Horse, Integer> horseRaceTimeColumn;

    @FXML
    private TableColumn<Horse, Integer> horseIdColumn2;

    @FXML
    private TableColumn<Horse, String> horseNameColumn2;

    @FXML
    private TableColumn<Horse, String> jockeyNameColumn2;

    @FXML
    private TableColumn<Horse, Integer> horseAgeColumn2;

    @FXML
    private TableColumn<Horse, String> horseBreedColumn2;

    @FXML
    private TableColumn<Horse, String> horseRaceRecordColumn2;

    @FXML
    private TableColumn<Horse, String> horseGroupColumn2;

    @FXML
    private TableColumn<Horse, Image> horseImageColumn2;

    // Initializing lists for raceHorses and winners
    public static List<Horse> raceHorses = new ArrayList<>();
    public static List<Horse> winners = new ArrayList<>();


    private static boolean winnersTablePopulated = false;


    // Method to populate table with winning horses in WHD tab
    @FXML
    public void populateTableWithWinningHorses() {
        if (!raceStarted) {
            showAlert("The race has not started yet. Please start race in order to see Winning Horses' Details!");
        } else {
            if (!winnersTablePopulated) {
                raceHorses.clear();
                winners.clear();
                assignRandomRaceTimesToSelectedHorses();
                sortHorsesByRaceTime();
                selectWinners();
                initializeWHDTable();
                horseTable2.getItems().setAll(winners);
                winnersTablePopulated = true; // Setting the flag to true to indicate that the table is populated
            } else {
                showAlert("The Winners for this round have already been displayed.");
            }
        }
    }

    // Method to assign random race times to selected horses
    public void assignRandomRaceTimesToSelectedHorses() {
        // Assigning random race times to selected horses
        for (Horse horse : selectedHorses) {
            int raceTime = new Random().nextInt(90) + 1;
            horse.setRaceTime(raceTime);
            raceHorses.add(horse);
        }
    }

    // Method to sort horses by race time
    public static void sortHorsesByRaceTime() {
        selectionSort(raceHorses);
    }

    // Sorting algorithm used to sort horses by race times
    public static void selectionSort(List<Horse> horses) {
        for (int i = 0; i < horses.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < horses.size(); j++) {
                if (horses.get(j).getRaceTime() < horses.get(minIndex).getRaceTime()) {
                    minIndex = j;
                }
            }
            Horse temp = horses.get(i);
            horses.set(i, horses.get(minIndex));
            horses.set(minIndex, temp);
        }
    }

    // Method to select winners
    public static void selectWinners() {
        // Selecting the 1st, 2nd, and 3rd place winners
        String[] placeLabels = {"1st Place", "2nd Place", "3rd Place"};
        for (int i = 0; i < raceHorses.size() - 1; i++) {
            winners.add(raceHorses.get(i));
            winners.get(i).setPlace(placeLabels[i]);
        }
    }


    // Method to initialize table in WHD tab
    @FXML
    public void initializeWHDTable() {
        // Initialize the table columns
        horsePlaceColumn.setCellValueFactory(new PropertyValueFactory<>("place"));
        horseRaceTimeColumn.setCellValueFactory(new PropertyValueFactory<>("raceTime"));
        horseIdColumn2.setCellValueFactory(new PropertyValueFactory<>("id"));
        horseNameColumn2.setCellValueFactory(new PropertyValueFactory<>("name"));
        jockeyNameColumn2.setCellValueFactory(new PropertyValueFactory<>("jockeyName"));
        horseAgeColumn2.setCellValueFactory(new PropertyValueFactory<>("age"));
        horseBreedColumn2.setCellValueFactory(new PropertyValueFactory<>("breed"));
        horseRaceRecordColumn2.setCellValueFactory(new PropertyValueFactory<>("raceRecord"));
        horseGroupColumn2.setCellValueFactory(new PropertyValueFactory<>("group"));
        horseImageColumn2.setCellValueFactory(new PropertyValueFactory<>("thumbnailImage"));

        horseImageColumn2.setCellFactory(parameter -> new TableCell<>() {
            private final ImageView imageView2 = new ImageView();
            {
                imageView2.setFitHeight(100);
                imageView2.setFitWidth(150);
                setGraphic(imageView2);
            }
            @Override
            protected void updateItem(Image item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    imageView2.setImage(null);
                } else {
                    imageView2.setImage(item);
                }
            }
        });

    }


    // 4. VWH Functionality - Responsible for visualizing the race times of the winners in a bar chart

    @FXML
    private BarChart<String, Number> barChart;

    // Initializing boolean that indicates whether bar chart was visualized
    private static boolean barChartDataVisualized = false;

    // Method to initialize bar chart in VWH tab
    @FXML
    private void initializeBarChart() {
        // Checking if the winning horses list is empty
        if (winners.isEmpty()) {
            showAlert("Please execute WHD and select winning horses before visualizing the race times.");
            return;
        }

        // Checking if the bar chart is already initialized
        if (barChartDataVisualized) {
            // Results are already visualized, show an alert
            showAlert("The results for this round have already been visualized. You cannot visualize it again.");
            return; // Exit the method
        }

        // If the bar chart is not already initialized, proceed to initialize it
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Time Taken");

        for (Horse horse : winners) {
            series.getData().add(new XYChart.Data<>(horse.getPlace(), horse.getRaceTime()));
        }

        barChart.getData().add(series);
        barChartDataVisualized = true;
    }

    // Method to display alert boxes
    @Override
    public void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Race Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();


    }
}
