module colecciones {
    requires javafx.controls;
    requires javafx.fxml;

    opens colecciones.controller to javafx.fxml;
    opens colecciones to javafx.fxml;
    opens colecciones.model to javafx.base;

    exports colecciones;
}
