module org.joshuaharris.visualisation {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens org.joshuaharris.visualisation to javafx.fxml;
    exports org.joshuaharris.visualisation;
}