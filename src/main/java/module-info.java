module com.example.proyectjava {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.proyectjava to javafx.fxml;
    exports com.example.proyectjava;
}