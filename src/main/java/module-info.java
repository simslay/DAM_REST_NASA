module com.example.dam_rest_nasa {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    requires org.json;

    opens com.example.dam_rest_nasa to javafx.fxml;
    exports com.example.dam_rest_nasa;
}