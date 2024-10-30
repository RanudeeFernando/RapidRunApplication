module com.example.rapidrunapplication {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.rapidrunapplication to javafx.fxml;
    exports com.example.rapidrunapplication;
}