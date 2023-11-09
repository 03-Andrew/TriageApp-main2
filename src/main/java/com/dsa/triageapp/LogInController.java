package com.dsa.triageapp;

import com.dsa.triageapp.ADT.TriageQueue;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LogInController {
    @FXML
    private PasswordField passWord;

    @FXML
    private TextField userName;
    double x, y;

    private TriageQueue triageQueue = new TriageQueue();
    private int[] counter = new int[5];
    private boolean[] isStationAvailable = new boolean[]{true, true, true};


    private boolean isLoginValid(String username, String password) {
        return username.equals("Admin") && password.equals("Admin123");
    }

    public void loadView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("patientTicketing-view.fxml"));
        Parent root = loader.load();
        PatientTicketingController triageFC = loader.getController();
        triageFC.setTriageQueue(triageQueue);
        triageFC.setCounterAndStation(counter, isStationAvailable);
        Stage newStage = new Stage(); // Create a new stage for the new scene

        Scene newScene = new Scene(root);
        newStage.setScene(newScene);
        newStage.centerOnScreen();
        newStage.setResizable(false);
        newStage.initStyle(StageStyle.TRANSPARENT);
        newStage.setTitle("Triage Queue");
        newStage.show();

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        root.setOnMousePressed(event1 -> {
            x = event1.getSceneX();
            y = event1.getSceneY();
        });

        root.setOnMouseDragged(event1 -> {
            newStage.setX(event1.getScreenX() - x);
            newStage.setY(event1.getScreenY() - y);
        });

        root.setOnMouseReleased(event1 -> newStage.setOpacity(1));
    }

    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        String username = userName.getText();
        String password = passWord.getText();

        if (isLoginValid(username, password)) {
            // If login is valid, load another scene
            loadView(event);
        } else {
            // If login is not valid, show an alert
            showAlert("Invalid Login", "Please check your username and password.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    @FXML
    private void close(ActionEvent event) {
        Platform.exit();
    }
}
