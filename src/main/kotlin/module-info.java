module projet.echecmartien {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    requires org.controlsfx.controls;
    requires javafx.graphics;
    requires jdk.jfr;
    requires kotlin.stdlib.jdk7;
    requires com.google.gson;

    opens projet.echecmartien to javafx.fxml;
    exports projet.echecmartien;
}