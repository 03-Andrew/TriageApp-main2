package com.dsa.triageapp;

import com.dsa.triageapp.Classes.Patient1;
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

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.PriorityQueue;

public class TriageFormController {
public javafx.scene.layout.BorderPane BorderPane;
    PriorityQueue<Patient1> patientQueue = new PriorityQueue<>((p1, p2) -> p1.getLevel() - p2.getLevel());
    int[] counter = new int[5];
    private Stage stage;
    private Scene scene;
    private Parent root;

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
    protected void addPatient(ActionEvent event){

        String name = String.format("%s, %s %s", lastName_txtField.getText(), firstName_txtField.getText(), middle_txtField.getText());
        LocalDate date = birthDate_datePicker.getValue();
        Patient1 patient = new Patient1(name, date);
        patient.setGender(gender_cbo.getValue());
        patient.setLevel(setLevel(level_cbo.getValue()));
        patient.setComplaint(complaint_txtField.getText());
        patient.setCondition(condition_txtField.getText());
        count(level_cbo.getValue());
        patientQueue.add(patient);
        JOptionPane.showMessageDialog(null, name+" added");
        clearFields();

    }
    @FXML
    public void viewQueue(ActionEvent event) throws IOException {
        if (patientQueue == null || patientQueue.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No Patients");
            return;
        }

        // Create a copy of the queue without removing elements
        PriorityQueue<Patient1> copyQueue = new PriorityQueue<>(patientQueue);

        StringBuilder sb = new StringBuilder();
        sb.append("Patient Queue (").append(copyQueue.size()).append(" patients):\n");
        String count = String.format("Resuscitation: %d\nEmergency: %d\nUrgent: %d" +
                                    "\nSemi-urgent: %d\nNon-urgent: %d", counter[0], counter[1],
                                      counter[2], counter[3], counter[4]);
        sb.append(count);

        for (Patient1 patient : copyQueue) {
            sb.append(patient.getData()).append("\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }


    @FXML
    public void goToFullForm(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dsa/triageapp/patientRegistrationForm-view.fxml"));
        Parent root = loader.load();
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene newScene = new Scene(root);
        currentStage.setScene(newScene);
        currentStage.centerOnScreen();
        currentStage.show();
    }


    @FXML
    public void exit(){
        Platform.exit();
    }

    public void initialize() {
        // Create an ObservableList of gender options
        ObservableList<String> genderOptions = FXCollections.observableArrayList(
                "Male", "Female"
        );
         // Set the gender options to the ComboBox
        if (gender_cbo != null) {
            gender_cbo.setItems(genderOptions);
        }
        ObservableList<String> priorityOptions = FXCollections.observableArrayList("Resuscitation",
            "Emergency",
            "Urgent",
            "Semi-urgent",
            "Non-urgent"
        );

        // Set the priority options to the ComboBox
        if(level_cbo != null) {
            level_cbo.setItems(priorityOptions);
        }
    }
    public int setLevel(String selectedPriority){
        return switch (selectedPriority) {
            case "Resuscitation" -> 1;
            case "Emergency" -> 2;
            case "Urgent" -> 3;
            case "Semi-urgent" -> 4;
            case "Non-urgent" -> 5;
            default -> 0;
        };
    }
    public void clearFields(){
        firstName_txtField.setText("");
        lastName_txtField.setText("");
        middle_txtField.setText("");
        contact_txtField.setText("");
        complaint_txtField.setText("");
        condition_txtField.setText("");
        gender_cbo.setPromptText("Gender");
        level_cbo.setPromptText("Level");
        birthDate_datePicker.setUserData(LocalDate.now());
    }
    public void count(String selectedPriority){
        switch (selectedPriority) {
            case "Resuscitation"-> counter[0] += 1;
            case "Emergency" -> counter[1] += 1;
            case "Urgent" -> counter[2] += 1;
            case "Semi-urgent" -> counter[3] += 1;
            case "Non-urgent" -> counter[4] += 1;
        };
    }
}
