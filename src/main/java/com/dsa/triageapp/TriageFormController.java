package com.dsa.triageapp;

import com.dsa.triageapp.ADT.PriorityQueue;
import com.dsa.triageapp.ADT.TriageQueue;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;
//import java.util.PriorityQueue;


public class TriageFormController {
    private double x = 0, y = 0;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private TriageQueue triageQueue;
    private int[] counter;
    private boolean[] isStationAvailable;


    @FXML
    private Button add_btn;

    @FXML
    private Button exit_btn;

    @FXML
    private DatePicker birthDate_datePicker;

    @FXML
    private TextField complaint_txtField;

    @FXML
    private TextField condition_txtField;

    @FXML
    private TextField contact_txtField;

    @FXML
    private TextField firstName_txtField;

    @FXML
    private ComboBox<String> gender_cbo;

    @FXML
    private TextField lastName_txtField;

    @FXML
    private ComboBox<String> level_cbo;

    @FXML
    private TextField middle_txtField;

    @FXML
    private Button next_btn;

    @FXML
    private Button openFull_btn;

    @FXML
    private Button viewQueueBtn;

    @FXML
    protected void addPatient(ActionEvent event) {

        String name = String.format("%s, %s %s", lastName_txtField.getText(), firstName_txtField.getText(), middle_txtField.getText());
        LocalDate date = birthDate_datePicker.getValue();
        Patient patient = new Patient(name, gender_cbo.getValue(), date, contact_txtField.getText());
        DataBase db = new DataBase("PatientInfo1");
        db.addToFile(patient.dbString1());
        clearFields();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Patient Added");
        alert.setHeaderText(null);
        alert.setContentText("Patient Added");
        alert.showAndWait();
        //JOptionPane.showMessageDialog(null, "Patient Added");



    }



    @FXML
    public void viewQueue(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TriageQueue-view.fxml"));
        Parent root = loader.load();

        TriageQueueController triageQC = loader.getController();
        triageQC.setCounterAndStation(counter, isStationAvailable);
        triageQC.setTriageQueue(triageQueue);

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
    public void goToFullForm(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("patientRegistrationForm-view.fxml"));
        Parent root = loader.load();

        PatientRegistrationController patientRC = loader.getController();
        patientRC.setCounterAndQueue(counter, triageQueue);

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
    public void goToTicket(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("patientTicketing-view.fxml"));
        Parent root = loader.load();
        PatientTicketingController patientTC = loader.getController();
        patientTC.setTriageQueue(triageQueue);
        patientTC.setCounterAndStation(counter, isStationAvailable);
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

    public void loadView(ActionEvent event, String file) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
        Parent root = loader.load();

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
    public void exit() {
        Platform.exit();
    }


    public void initialize() {
        ObservableList<String> genderOptions = FXCollections.observableArrayList(
                "Male", "Female"
        );
        gender_cbo.setItems(genderOptions);

    }

    public int setLevel(String selectedPriority) {
        return switch (selectedPriority) {
            case "Resuscitation" -> 1;
            case "Emergency" -> 2;
            case "Urgent" -> 3;
            case "Semi-urgent" -> 4;
            case "Non-urgent" -> 5;
            default -> 0;
        };
    }

    public void setTriageQueue(TriageQueue triageQueue){
        this.triageQueue = triageQueue;
        //JOptionPane.showMessageDialog(null, triageQueue.heapCount());
    }
    public void setCounterAndStation(int[] counter, boolean[] isStationAvailable){
        this.counter = counter;
        this.isStationAvailable = isStationAvailable;
        //JOptionPane.showMessageDialog(null, counter.length);
    }

    public void clearFields() {
        firstName_txtField.setText("");
        lastName_txtField.setText("");
        middle_txtField.setText("");
        contact_txtField.setText("");
        gender_cbo.setPromptText("Gender");
        birthDate_datePicker.setUserData(LocalDate.now());
    }
}