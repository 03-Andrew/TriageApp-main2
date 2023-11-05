package com.dsa.triageapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {private double x = 0, y = 0;
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("triageForm-view.fxml"));
        Scene scene = new Scene(root);

        root.setOnMousePressed((MouseEvent event) ->{
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent event) ->{
            stage.setX(event.getScreenX()  - x);
            stage.setY(event.getScreenY()  - y);

            stage.setOpacity(.9);
        });

        root.setOnMouseReleased((MouseEvent event) -> stage.setOpacity(1));
        // This is a draggable window with no control bar
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Triage");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {launch();}
    /*
    @Override
    public void start(Stage stage) throws IOException {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("triageForm-view.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            //scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("jetbrains://idea/navigate/reference?project=TriageApp&path=com/dsa/triageapp/CSS/Style.css")).toExternalForm());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

     */
}


