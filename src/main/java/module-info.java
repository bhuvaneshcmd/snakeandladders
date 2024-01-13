module com.example.snakeandladders {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.snakeandladders to javafx.fxml;
    exports com.example.snakeandladders;
}