package com.dsa.triageapp;
import com.dsa.triageapp.ADT.TriageQueue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ViewPatientsController {
    private Patient selectedPatient;
    private TriageQueue triageQueue;
    private int[] counter;
    private boolean[] isStationAvailable;
      @FXML
    private TableColumn<Patient, String> BirthDate;

    @FXML
    private TableColumn<Patient, Integer> age;

    @FXML
    private Button back;


    @FXML
    private TextField searchTxtField;

    @FXML
    private TableColumn<Patient, String> contactNo;

    @FXML
    private TableColumn<Patient, String> gender;

    @FXML
    private TableColumn<Patient, String> name;

     @FXML
    private Button Select;

    @FXML
    private TableView<Patient> table;

    private final ObservableList<Patient> patientList = FXCollections.observableArrayList();

    public void initialize(){
        DataBase db = new DataBase("PatientInfo1");
        ArrayList<String[]> patients = db.getFile();
         for (String[] s : patients) {
            String dateStr = s[2];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(dateStr, formatter);
            Patient p = new Patient(s[0], s[1], localDate, s[3]);
            patientList.add(p);
        }

        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        age.setCellValueFactory(new PropertyValueFactory<>("Age"));
        gender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        contactNo.setCellValueFactory(new PropertyValueFactory<>("ContactNumber"));

        table.setItems(patientList);
        searchTxtField.textProperty().addListener((observable, oldValue, newValue) -> {
            table.setItems(filterPatients(newValue));
        });

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedPatient = newSelection;
            }
        });
    }
    public Patient getSelectedPatient() {
        return selectedPatient;
    }

    private ObservableList<Patient> filterPatients(String searchText) {
        if (searchText == null || searchText.isEmpty()) {
            return patientList;
        }

        ObservableList<Patient> filteredList = FXCollections.observableArrayList();

        for (Patient patient : patientList) {
            if (patient.getName().toLowerCase().contains(searchText.toLowerCase())) {
                filteredList.add(patient);
            }
        }

        return filteredList;
    }
    double x,y;
    public void loadView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("patientTicketing-view.fxml"));
        Parent root = loader.load();

        PatientTicketingController patientTC = loader.getController();
        patientTC.setCounterAndStation(counter, isStationAvailable);
        patientTC.setTriageQueue(triageQueue);

        Stage newStage = new Stage(); // Create a new stage for the new scene

        Scene newScene = new Scene(root);
        newStage.setScene(newScene);
        newStage.centerOnScreen();
        newStage.setResizable(false);
        newStage.initStyle(StageStyle.TRANSPARENT);
        newStage.setTitle("Triage Queue");
        newStage.show();

        Patient selectedPatient = getSelectedPatient();
        // Pass the selectedPatient object to the new view
        PatientTicketingController controller = loader.getController();
        controller.setTriageQueue(triageQueue);
        controller.initData(selectedPatient);

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
    public void selectPatient(ActionEvent event) throws IOException {
         Patient selectedPatient = getSelectedPatient();
        if (selectedPatient != null) {
            String patientName = selectedPatient.getName();

            // Create an alert to display the selected patient's name
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Selected Patient");
            alert.setHeaderText(null);
            alert.setContentText("You selected: " + patientName);

            alert.showAndWait(); // Display the alert as a dialog
            loadView(event);

        } else {
            // Handle the case where no patient is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Patient Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a patient first.");

            alert.showAndWait(); // Display the warning alert
        }
    }
    public void setTriageQueue(TriageQueue triageQueue){
        this.triageQueue = triageQueue;
        //JOptionPane.showMessageDialog(null,triageQueue.heapCount());
    }
    public void setCounterAndStation(int[] counter, boolean[] isStationAvailable){
        this.isStationAvailable = isStationAvailable;
        this.counter = counter;
        //JOptionPane.showMessageDialog(null, counter.length);
    }

}
