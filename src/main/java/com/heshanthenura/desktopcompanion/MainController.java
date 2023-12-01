package com.heshanthenura.desktopcompanion;

import com.heshanthenura.desktopcompanion.Animation.Animations;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    @FXML
    private AnchorPane bg;
    Stage stage;
    @FXML
    private ImageView img;
    @FXML
    private HBox imgContainer;

    Screen screen = Screen.getPrimary();
    Rectangle2D bounds = screen.getVisualBounds();
    double screenWidth = bounds.getWidth();
    double screenHeight = bounds.getHeight();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Platform.runLater(() -> {
            stage = (Stage) bg.getScene().getWindow();
            stage.setX(0);
            stage.setY(screenHeight- stage.getHeight());
            stage.setWidth(bg.getWidth());
            stage.setHeight(bg.getHeight());
            new Animations().move(stage,img,screenWidth,screenHeight);

        });
    }

}
