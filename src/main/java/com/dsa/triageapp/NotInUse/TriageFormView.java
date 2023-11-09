package com.dsa.triageapp.NotInUse;

import com.dsa.triageapp.ADT.PriorityQueue;
import com.dsa.triageapp.ADT.Queue;
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
import java.util.ArrayList;
//import java.util.PriorityQueue;

public class TriageFormView {
    public javafx.scene.layout.BorderPane BorderPane;
    PriorityQueue patientQueue = new PriorityQueue();
    private int[] counter;
    Queue<PatientModel> st1 = new Queue<>();
    Queue<PatientModel> st2 = new Queue<>();
    Queue<PatientModel> st3 = new Queue<>();
    //Patient1[] arr;

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
    private TableColumn<PatientModel, Integer> ageColumn;

    @FXML
    private TableColumn<PatientModel, String> complaintColumn;

    @FXML
    private TableColumn<PatientModel, String> conditionColumn;

    @FXML
    private TableColumn<PatientModel, String> contactNumColumn;

    @FXML
    private Label emergencyCount_lbl;

    @FXML
    private TableColumn<PatientModel, String> genderColumn;

    @FXML
    private TableColumn<PatientModel, String> nameColumn;

    @FXML
    private Label nonUrgentCount_lbl;

    @FXML
    private TableColumn<PatientModel, String> levelColumn;

    @FXML
    private TableView<PatientModel> queueTable;

    @FXML
    private Label resuscitationCount_lbl;

    @FXML
    private Label semiUrgentCount_lbl;

    @FXML
    private Label urgentCount_lbl;

    @FXML
    private ListView<String> myListView;


    //Nurse Station
    @FXML
    private ListView<String> s1Lview;

    @FXML
    private ListView<String> s2Lview;

    @FXML
    private ListView<String> s3Lview;

    @FXML
    private Label station1Lbl;

    @FXML
    private Label station2Lbl;

    @FXML
    private Label station3Lbl;

    @FXML
    protected void addPatient(ActionEvent event){
        /*
        String name = String.format("%s, %s %s", lastName_txtField.getText(), firstName_txtField.getText(), middle_txtField.getText());
        LocalDate date = birthDate_datePicker.getValue();
        Patient1 patient = new Patient1(name, date);
        patient.setGender(gender_cbo.getValue());
        patient.setLevel(setLevel(level_cbo.getValue()));
        patient.setComplaint(complaint_txtField.getText());
        patient.setCondition(condition_txtField.getText());
        patient.setNumber(contact_txtField.getText());
        count(level_cbo.getValue());
        patientQueue.upHeap(patient);
        JOptionPane.showMessageDialog(null, name+" added"+patientQueue.peek());
        clearFields();
        setCounters();
        addToListView2(patient.getData2());

         */
    }
    @FXML
    public void viewQueue(ActionEvent event) throws IOException {
        /*
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
        */
        JOptionPane.showMessageDialog(null, label());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dsa/triageapp/ViewTriageQueue.fxml"));
        Parent root = loader.load();

        ViewTriageQueue viewTriageQueue = loader.getController();
        viewTriageQueue.setCounter(counter);
        //viewTriageQueue.setPatientQueue(patientQueue);

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene newScene = new Scene(root);
        currentStage.setScene(newScene);
        currentStage.centerOnScreen();
        currentStage.show();
    }
    @FXML
    private void switchToSecondScene(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dsa/triageapp/ViewTriageQueue.fxml"));
            Parent root = loader.load();

            // Get the controller for the second scene
            ViewTriageQueue secondSceneController = loader.getController();

            // Pass the triageQueue to the second scene controller
            //secondSceneController.setPatientQueue(patientQueue);
            secondSceneController.setLbl(label());

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene newScene = new Scene(root);
            currentStage.setScene(newScene);
            currentStage.centerOnScreen();
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setHeapArr(){
        patientQueue.heapSort();

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
        //setCounters();
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
        resuscitationCount_lbl.setText("Resuscitation: 0");
        emergencyCount_lbl.setText("Emergency: 0");
        urgentCount_lbl.setText("Urgent: 0");
        semiUrgentCount_lbl.setText("Semi-urgent: 0");
        nonUrgentCount_lbl.setText("Non-urgent: 0");

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

    public void setCounters(){
        resuscitationCount_lbl.setText("Resuscitation: " + counter[0]);
        emergencyCount_lbl.setText("Emergency: " + counter[1]);
        urgentCount_lbl.setText("Urgent: " + counter[2]);
        semiUrgentCount_lbl.setText("Semi-urgent: " + counter[3]);
        nonUrgentCount_lbl.setText("Non-urgent: " + counter[4]);
    }


    public void setCounter(int[] counter){
        this.counter = counter;
    }
    public void setPatientQueue(PriorityQueue patientQueue){
        this.patientQueue = patientQueue;
    }
    public String label(){
        PriorityQueue priorityQueue = new PriorityQueue();
        StringBuilder sb = new StringBuilder();
        while (!priorityQueue.isEmpty()){
            PatientModel p = priorityQueue.poll();
            sb.append(p.getData2()).append("\n");
        }
        return sb.toString();
    }


    public void addToListView() {
        myListView.getItems().clear();
        //setHeapArr();
        //Patient1[] arr = patientQueue.heapSort();
        ArrayList<String> strArr = new ArrayList<>();
        PriorityQueue copy = patientQueue.duplicateQueue();
        PatientModel[] p2 = copy.heapSort();
        //JOptionPane.showMessageDialog(null, arr.length);
        for(int i = 0; i < copy.heapCount(); i++){
            PatientModel p = p2[i];
            strArr.add(p.getData2());
        }

        // Now, add all the items to the ListView after the loop
        myListView.getItems().addAll(strArr);
    }
    public void addToListView2(String str){
        myListView.getItems().add(str);
    }
    @FXML
    public void treatPatient(){

        JOptionPane.showMessageDialog(null, "HUHU");
        if(patientQueue.isEmpty()){
            return;
        }
        if (!myListView.getItems().isEmpty()) {
            //myListView.getItems().remove(0); // Remove the first item (top value)
            String firstLine = myListView.getItems().remove(0);
            minus(Integer.parseInt(firstLine.substring(16,17)));
        }

        Queue<PatientModel> queue = new Queue<>();
        setCounters();
        PatientModel p = patientQueue.poll();
        queue.enqueue(p);
        PatientModel patientModel = queue.dequeue();
        addToStation(patientModel);
        JOptionPane.showMessageDialog(null, patientModel.getData2());

        //JOptionPane.showMessageDialog(null,queue.get);

        //JOptionPane.showMessageDialog(null, queue.frontValue().getName());
    }

    public void addToStation(PatientModel patientModel) {
        int size1 = st1.getCurrentSize();
        int size2 = st2.getCurrentSize();
        int size3 = st3.getCurrentSize();

        if (size1 <= size2 && size1 <= size3) {
            st1.enqueue(patientModel);
            s1Lview.getItems().add(patientModel.getData2());
            station1Lbl.setText("Station 1 | Treating: " + st1.peek().getName());
        } else if (size2 <= size1 && size2 <= size3) {
            st2.enqueue(patientModel);
            s2Lview.getItems().add(patientModel.getData2());
            station2Lbl.setText("Station 2 | Treating: " + st2.peek().getName());
        } else {
            st3.enqueue(patientModel);
            s3Lview.getItems().add(patientModel.getData2());
            station3Lbl.setText("Station 1 | Treating: " + st3.peek().getName());
        }
    }


}

