module com.heshanthenura.desktopcompanion {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.heshanthenura.desktopcompanion to javafx.fxml;
    exports com.heshanthenura.desktopcompanion;
    exports com.heshanthenura.desktopcompanion.Animation;
    opens com.heshanthenura.desktopcompanion.Animation to javafx.fxml;
}