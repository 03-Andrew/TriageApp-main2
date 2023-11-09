package com.dsa.triageapp.NotInUse;

import javafx.collections.*;
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
import java.util.PriorityQueue;

public class HelloController {
    PriorityQueue<PatientModel> patientQueue;
    StringBuilder sb = new StringBuilder();
    private Stage stage;
    private Scene scene;
    private Parent root;


    public void switchToScene1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToScene2(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("ViewTriageQueue.fxml"));
        stage = ((Stage)((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    ListView<String> historyListView;


    @FXML
    private TextField address_txtField;

    @FXML
    private TextField allergiex_textField;

    @FXML
    private DatePicker birth_datePicker;

    @FXML
    private TextField complaint_txtField;

    @FXML
    private TextField contactNumber_txtField;

    @FXML
    private TextField currMed_textField;

    @FXML
    private TextField firstName_txtField;

    @FXML
    private ComboBox<String> gender_comboBox;

    @FXML
    private TextField lastName_txtField;

    @FXML
    private TextField middleName_txtField;

    @FXML
    private TextField occupation_txtField;

    @FXML
    private ComboBox<String> priorityLevel_comboBox;

    @FXML
    private Button register_btn;

    @FXML
    private ComboBox<String> status_comboBox;

    @FXML
    private Button viewQueue_btn;

    @FXML
    private Button addToQueue;

    @FXML
    private TextField conditon_textField;

    public void initialize() {
        // Create an ObservableList of gender options
        ObservableList<String> genderOptions = FXCollections.observableArrayList(
            "Male",
            "Female",
            "Other"
        );

        // Set the gender options to the ComboBox
       if (gender_comboBox != null) {
           gender_comboBox.setItems(genderOptions);
       }


        ObservableList<String> status = FXCollections.observableArrayList( "Single",
            "Married",
            "Divorced",
            "Widowed",
            "Separated",
            "Other"
        );
       if (gender_comboBox != null) {
           status_comboBox.setItems(status);
       }
        ObservableList<String> priorityOptions = FXCollections.observableArrayList(
            "Resuscitation",
            "Emergency",
            "Urgent",
            "Semi-urgent",
            "Non-urgent"
        );

        // Set the priority options to the ComboBox
        if(priorityLevel_comboBox != null) {
            priorityLevel_comboBox.setItems(priorityOptions);
        }
    }

    @FXML
    protected void registerClick(ActionEvent event) {

        //JOptionPane.showMessageDialog(null, "Patient registered: " + patient.getFirstName() + " " + patient.getLastName());


    }
    @FXML
    protected void addPatient(ActionEvent event){
        /*
        patientQueue = new PriorityQueue<>((p1, p2) -> p1.getLevel() - p2.getLevel());
        String name = String.format("%s, %s %s.", lastName_txtField.getText(), firstName_txtField.getText(), middleName_txtField.getText());
        LocalDate date = birth_datePicker.getValue();
        Patient1 patient = new Patient1(name, date);
        patient.setGender(gender_comboBox.getValue());
        patient.setLevel(setLevel(priorityLevel_comboBox.getValue()));
        patient.setComplaint(complaint_txtField.getText());
        patient.setCondition(conditon_textField.getText());
        patientQueue.add(patient);
        JOptionPane.showMessageDialog(null, "Patient added");
        clearFields();

         */

    }

    @FXML
    public void viewQueue(ActionEvent event) throws IOException {
        if (patientQueue == null || patientQueue.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No Patients");
            return;
        }

        // Create a copy of the queue without removing elements
        PriorityQueue<PatientModel> copyQueue = new PriorityQueue<>(patientQueue);

        StringBuilder sb = new StringBuilder();
        while (!copyQueue.isEmpty()) {
            PatientModel newPatient = copyQueue.poll();
            sb.append(newPatient.getData()).append("\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString());
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
        middleName_txtField.setText("");
        contactNumber_txtField.setText("");
        address_txtField.setText("");
        complaint_txtField.setText("");
        conditon_textField.setText("");
    }

}
/*
        Patient patient = new Patient();
        patient.setLastName(lastName_txtField.getText());
        patient.setFirstName(firstName_txtField.getText());
        patient.setMiddleName(middleName_txtField.getText());
        patient.setContactNum(contactNumber_txtField.getText());
        patient.setGender((String) gender_comboBox.getValue());
        patient.setHomeAddress(address_txtField.getText());
        patient.setCivilStatus((String) status_comboBox.getValue());
        patient.setOccupation(occupation_txtField.getText());
        patient.setPriority(setLevel(priorityLevel_comboBox.getValue()));

        // Handle date conversion from DatePicker
        if (birth_datePicker.getValue() != null) {
            java.sql.Date birthDate = java.sql.Date.valueOf(birth_datePicker.getValue());
            patient.setBirthDate(birthDate);
        }
*/