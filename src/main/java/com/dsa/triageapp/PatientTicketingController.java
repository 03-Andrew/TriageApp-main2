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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;
import java.util.Arrays;

public class PatientTicketingController {
     @FXML
    private Button addBtn;

    @FXML
    private TextField allergiesTxtField;

    @FXML
    private TextField complaintTxtField;

    @FXML
    private TextField conditionTxtField;

    @FXML
    private TextField currentMedTxtField;

    @FXML
    private TextField dateTxtfield;

    @FXML
    private TextField genderTxtField;

    @FXML
    private TextField nameTxtBox;

    @FXML
    private Button newBtn;

    @FXML
    private TextField noTxtField;

    @FXML
    private ComboBox<String> prioLevelCbo;

    @FXML
    private Button searchBtn;

    private int[] counter;
    private Patient patient;
    private TriageQueue queue;

    public void initialize(){
        //updateCounterArr();
        ObservableList<String> priorityOptions = FXCollections.observableArrayList(
                "Resuscitation", "Emergency", "Urgent", "Semi-urgent", "Non-urgent"
        );
        prioLevelCbo.setItems(priorityOptions);

    }
    public void initData(Patient patient) {
        this.patient = patient;

        if (patient != null) {
            nameTxtBox.setText(patient.getName());
            dateTxtfield.setText(patient.getBirthDate()+"");
            genderTxtField.setText(patient.getGender());
            noTxtField.setText(patient.getContactNumber());
        }
    }

    double x,y;
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

    public void back(ActionEvent event) throws IOException {
        //updateCounterFile();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("triageForm-view.fxml"));
        Parent root = loader.load();

        TriageFormController triageFC = loader.getController();
        triageFC.setTriageQueue(queue);
        triageFC.setCounter(counter);

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
    public void goToSearch(ActionEvent event) throws IOException {
        //updateCounterFile();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewPatients-view.fxml"));
        Parent root = loader.load();

        ViewPatientsController viewPC = loader.getController();
        viewPC.setTriageQueue(queue);
        viewPC.setCounter(counter);


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
    public void goToQueue(ActionEvent event) throws IOException {
        //updateCounterFile();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TriageQueue-view.fxml"));
        Parent root = loader.load();

        // Get the controller of the target view
        TriageQueueController triageQC = loader.getController();
        // Set the updated values in the target controller
        triageQC.setTriageQueue(queue);
        triageQC.setCounter(counter);

        Stage newStage = new Stage();
        Scene newScene = new Scene(root);
        newStage.setScene(newScene);
        newStage.centerOnScreen();
        newStage.setResizable(false);
        newStage.initStyle(StageStyle.TRANSPARENT);
        newStage.setTitle("Triage Queue");
        newStage.show();

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    public void addToQueue(){
        TriageTicket ticket = new TriageTicket(patient, complaintTxtField.getText(),conditionTxtField.getText(),prioLevelCbo.getValue());
        queue.upHeap(ticket);
        count(ticket.getPriorityLevel());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Patient Added");
        alert.setHeaderText(null);
        alert.setContentText(queue.displayHeap()+"\n"+ Arrays.toString(counter)+"\n"+queue.peek());
        alert.showAndWait();
        clearFields();



    }

    public void updateCounterFile(){
        DataBase db = new DataBase("counter");
        String counters = counter[0]+"#"+counter[1]+"#"+counter[2]+"#"+counter[3]+"#"+counter[4];
        db.storeToFile(counters);
    }
    public void updateCounterArr(){
        DataBase db = new DataBase("counter");
        String[] strArr = db.getFileText().split("#");
        for(int i = 0; i < strArr.length; i ++){
            counter[i] = Integer.parseInt(strArr[i].trim());
        }
    }
    public void setTriageQueue(TriageQueue triageQueue){
        this.queue =  triageQueue;
        if(triageQueue != null) {
            //JOptionPane.showMessageDialog(null, triageQueue.heapCount());
        }
    }
    public void setCounter(int[] counter){
        this.counter = counter;
        System.out.println(counter.length);
    }
    public void clearFields(){
        allergiesTxtField.clear();
        complaintTxtField.clear();
        conditionTxtField.clear();
        currentMedTxtField.clear();
        nameTxtBox.clear();
        dateTxtfield.clear();
        genderTxtField.clear();
        noTxtField.clear();

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
    public void minus(int selectedPriority){
        switch (selectedPriority) {
            case 1 -> counter[0] -= 1;
            case 2 -> counter[1] -= 1;
            case 3 -> counter[2] -= 1;
            case 4 -> counter[3] -= 1;
            case 5 -> counter[4] -= 1;
        };
        JOptionPane.showMessageDialog(null, counter.toString());
    }


}
