package com.dsa.triageapp;

import com.dsa.triageapp.ADT.PriorityQueue;
import com.dsa.triageapp.ADT.TriageQueue;
import com.dsa.triageapp.NotInUse.PatientModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;


public class TriageQueueController {
    @FXML
    private TableColumn<PatientModel, Integer> age;

    @FXML
    private TableColumn<PatientModel, String> complaint;

    @FXML
    private TableColumn<PatientModel, String> condition;

    @FXML
    private TableColumn<PatientModel, String> number;

    @FXML
    private TableColumn<PatientModel, String> name;

    @FXML
    private TableColumn<PatientModel, String> gender;

    @FXML
    private TableColumn<PatientModel, Integer> level;

    @FXML
    private ListView<String> listView1;

    @FXML
    private ListView<String> listView2;

    @FXML
    private ListView<String> listView3;

    @FXML
    private Label nUrgentLbl;


    @FXML
    private Button nextPatientBtn;

    @FXML
    private Button nextPatientBtn2;

    @FXML
    private Button nextPatientBtn3;

    @FXML
    private Label resuscitationLbl;

    @FXML
    private Label sUrgentLbl;

    @FXML
    private Button skipBtn;

    @FXML
    private Button treatBtn;

    @FXML
    private Label treatingLbl1;

    @FXML
    private Label treatingLbl2;

    @FXML
    private Label treatingLbl3;

    @FXML
    private Label urgentLbl;

    @FXML
    private Label emergencyLbl;

    @FXML
    private TableView<TriageTicket> queueTable;
    private double x = 0, y = 0;
    private TriageQueue triageQueue;
    private int[] counter;
    private boolean[] isStationAvailable;
    private ObservableList<TriageTicket> patients = FXCollections.observableArrayList();

    public void setTriageQueue(TriageQueue triageQueue){
        this.triageQueue =  triageQueue;
        fillTable();
        //JOptionPane.showMessageDialog(null,triageQueue.heapCount());
    }


    public void setLabels(){
        resuscitationLbl.setText("Resuscitation: " + counter[0]);
        emergencyLbl.setText("Emergency: " + counter[1]);
        urgentLbl.setText("Urgent: " + counter[2]);
        sUrgentLbl.setText("Semi-urgent: " + counter[3]);
        nUrgentLbl.setText("Non-urgent: " + counter[4]);
    }
    public void initialize(){
        level.setCellValueFactory(new PropertyValueFactory<>("priorityLevel"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        age.setCellValueFactory(new PropertyValueFactory<>("age")); // Use lowercase 'age'
        gender.setCellValueFactory(new PropertyValueFactory<>("gender")); // Use lowercase 'gender'
        number.setCellValueFactory(new PropertyValueFactory<>("number"));
        complaint.setCellValueFactory(new PropertyValueFactory<>("chiefComplaint"));
        condition.setCellValueFactory(new PropertyValueFactory<>("currentCondition"));
        queueTable.setItems(patients);
    }


    @FXML
    public void back(ActionEvent event) throws IOException {
        //updateCounterFile();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("patientTicketing-view.fxml"));
        Parent root = loader.load();
        PatientTicketingController ptc = loader.getController();
        ptc.setTriageQueue(triageQueue);
        ptc.setCounterAndStation(counter,isStationAvailable);
        //ptc.setCounter1(counter);
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
    public void alert(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Patient");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void viewCounter(){
        TriageTicket[] ticks = triageQueue.heapSort();
        for(int i  = 0; i < triageQueue.heapCount(); i++){
            JOptionPane.showMessageDialog(null,ticks[i].getPatientInfo().getName()+"\n"+ticks[i].getChiefComplaint());
        }
        //JOptionPane.showMessageDialog(null, triageQueue.heapSort());
    }

    public void skipPatient(){
        TriageTicket patient  = triageQueue.poll();
        minus(patient.getPriorityLevel());
        setLabels();
        patients.remove(0);
        queueTable.refresh();
    }
    public void treatPatient(){
        if(!isStationsAvailable()){
           alert("No Stations available");
            return;
        } if(triageQueue.isEmpty()){
            alert("No patients");
            return;
        }
        TriageTicket patient  = triageQueue.poll();
        sendToStation(patient);
        minus(patient.getPriorityLevel());
        setLabels();
        patients.remove(0);
        queueTable.refresh();

    }

    public boolean isStationsAvailable(){
        return isStationAvailable[0] || isStationAvailable[1] || isStationAvailable[2];
    }


    public void sendToStation(TriageTicket patient){

        String[] info = {"Patient: " + patient.getName(),
                "Priority level: " + patient.getPriorityLevel(),
                "Age: " + patient.getAge() + "\tGender: " + patient.getGender(),
                "Contact number: " + patient.getNumber(),
                "Chief Complaint: " + patient.getChiefComplaint(),
                "Current Condition: " + patient.getCurrentCondition(),
                "Current Medication: " + patient.getCurrentMedication()};

        if(isStationAvailable[0]){
            treatingLbl1.setText("Station 1");
            listView1.getItems().addAll(info);
            isStationAvailable[0] = false;
        } else if (isStationAvailable[1]){
            treatingLbl2.setText("Station 2");
            listView2.getItems().addAll(info);
            isStationAvailable[1] = false;
        } else if (isStationAvailable[2]){
            treatingLbl3.setText("Station 3");
            listView3.getItems().addAll(info);
            isStationAvailable[2] = false;
        }
    }
    public void nextPatient1(){
        if(isStationAvailable[0]){
            alert("Zero patients under care");
            return;
        }
        isStationAvailable[0] = true;
        listView1.getItems().clear();
        treatingLbl1.setText("Station 1 | Next Patient");
    }
    public void nextPatient2(){
        if(isStationAvailable[1]){
            alert("Zero patients under care");
            return;
        }
        isStationAvailable[1] = true;
        listView2.getItems().clear();
        treatingLbl2.setText("Station 2 | Next Patient");
    }
    public void nextPatient3(){
        if(isStationAvailable[2]){
            alert("Zero patients under care");
            return;
        }
        isStationAvailable[2] = true;
        listView3.getItems().clear();
        treatingLbl3.setText("Station 3 | Next Patient");
    }



    public void fillTable(){
        TriageQueue q = triageQueue.duplicateHeap();
        while (!q.isEmpty()){
            patients.add(q.poll());
        }
    }

    public void updateCounterFile(){
        DataBase db = new DataBase("counter");
        String counters = counter[0]+"#"+counter[1]+"#"+counter[2]+"#"+counter[3]+"#"+counter[4];
        db.storeToFile(counters);
        JOptionPane.showMessageDialog(null,counters);
    }
    public void updateCounterArr(){
        DataBase db = new DataBase("counter");
        String[] strArr = db.getFileText().split("#");
        for(int i = 0; i < strArr.length; i ++){
            counter[i] = Integer.parseInt(strArr[i].trim());
        }
    }
    public void setCounterAndStation(int[] counter, boolean[] isStationAvailable){
        this.counter = counter;
        this.isStationAvailable = isStationAvailable;
        setLabels();
        //JOptionPane.showMessageDialog(null,counter.length);
    }

    public void minus(String selectedPriority){
         switch (selectedPriority) {
            case "Resuscitation"-> counter[0] -= 1;
            case "Emergency" -> counter[1] -= 1;
            case "Urgent" -> counter[2] -= 1;
            case "Semi-urgent" -> counter[3] -= 1;
            case "Non-urgent" -> counter[4] -= 1;
        };
        JOptionPane.showMessageDialog(null, counter.toString());
    }
}
