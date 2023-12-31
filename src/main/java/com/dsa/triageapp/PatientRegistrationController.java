package com.dsa.triageapp;

import com.dsa.triageapp.ADT.TriageQueue;
import com.dsa.triageapp.Classes.PatientInfo;
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

public class PatientRegistrationController {


    @FXML
    private TextField address_textField;

    @FXML
    private TextField allergies_textField;

    @FXML
    private TextField contactNo2_textField;

    @FXML
    private TextField contact_textField;

    @FXML
    private TextArea currMed_textArea;

    @FXML
    private DatePicker date_datePicker;

    @FXML
    private TextField emergencyName_textField;

    @FXML
    private TextField firstName_textField;

    @FXML
    private ComboBox<String> gender_cbo;

    @FXML
    private TextField lastName_textField;

    @FXML
    private TextArea medHistory_textarea;

    @FXML
    private TextField middle_textField;

    @FXML
    private TextField nationality_textField;

    @FXML
    private TextField occupation_textField;

    @FXML
    private Button register_btn;

    @FXML
    private TextField relationship_textField;

    @FXML
    private TextField status_textField;
    private int[] counter;
    private TriageQueue triageQueue;
    private boolean[] isStationAvailable;
    public void initialize() {
        // Create an ObservableList of gender options
        ObservableList<String> genderOptions = FXCollections.observableArrayList(
                "Male", "Female"
        );
         // Set the gender options to the ComboBox
        if (gender_cbo != null) {
            gender_cbo.setItems(genderOptions);
        }

    }

    @FXML
    public void addPatient(){
        PatientInfo patientInfo = new PatientInfo();
        String name = String.format("%s, %s %s", lastName_textField.getText(), firstName_textField.getText(), middle_textField.getText());
        patientInfo.setName(name);
        patientInfo.setContactInfo(contact_textField.getText());
        patientInfo.setAddress(address_textField.getText());
        patientInfo.setDate(date_datePicker.getValue());
        patientInfo.setGender(gender_cbo.getValue());
        patientInfo.setOccupation(occupation_textField.getText());
        patientInfo.setCivilStatus(status_textField.getText());
        patientInfo.setEmergencyContactName(emergencyName_textField.getText());
        patientInfo.setNationality(nationality_textField.getText());
        patientInfo.setAge(patientInfo.calculateAge());
        patientInfo.setEmergencyContactNumber(contactNo2_textField.getText());
        patientInfo.setRelationship(relationship_textField.getText());
        patientInfo.setAllergies(allergies_textField.getText());
        patientInfo.setCurrMedication(currMed_textArea.getText());
        patientInfo.setMedicalHistory(medHistory_textarea.getText());

        DataBase db = new DataBase("PatientInfo");
        db.addToFile(patientInfo.storeToDB());
        JOptionPane.showMessageDialog(null, "Added to file");


    }


    double x,y;
    @FXML
    public void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("triageForm-view.fxml"));
        Parent root = loader.load();

        TriageFormController triageFC = loader.getController();
        triageFC.setCounterAndStation(counter, isStationAvailable);
        triageFC.setTriageQueue(triageQueue);

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

    public void setCounterAndQueue(int[] counter, TriageQueue triageQueue){
        this.counter = counter;
        this.triageQueue = triageQueue;
    }

}
