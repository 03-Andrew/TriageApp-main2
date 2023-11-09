package com.dsa.triageapp.NotInUse;

import com.dsa.triageapp.NotInUse.PatientModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class ViewTriageQueue {

    @FXML
    private TableColumn<PatientModel, Integer> ageColumn;

    @FXML
    private TableColumn<PatientModel, String> complaintColumn;

    @FXML
    private TableColumn<PatientModel, String> conditionColumn;

    @FXML
    private TableColumn<PatientModel, String> contactNumColumn;


    @FXML
    private TableColumn<PatientModel, String> genderColumn;

    @FXML
    private TableColumn<PatientModel, String> nameColumn;

    @FXML
    private TableColumn<PatientModel, String> levelColumn;

    @FXML
    private TableView<PatientModel> queueTable;

    @FXML
    private Label nonUrgentCount_lbl;

    @FXML
    private Label emergencyCount_lbl;

    @FXML
    private Label resuscitationCount_lbl;

    @FXML
    private Label semiUrgentCount_lbl;

    @FXML
    private Label urgentCount_lbl;

     @FXML
    private TableColumn<PatientModel, String> patientColumn;

    @FXML
    private TableView<String> patientTable;

    @FXML
    private TextArea patientsTxtArea;

    @FXML
    private ListView<String> myListView;

    private PriorityQueue<PatientModel> patientQueue;
    private String prioQueue;
/*
    public void setPatientQueue(PriorityQueue<Patient1> patientQueue) {
        this.patientQueue = patientQueue;
    }
    private void addToQueue(){
//        PatientModel p1 = new PatientModel(1, "Wilbert", LocalDate.now(), "Male", "0987654321", "complaint3", "condition3");
//        PatientModel p2 = new PatientModel(3, "name3", LocalDate.now(), "Male", "number2", "complaint2", "condition2");
//        PatientModel p3 = new PatientModel(2, "name2", LocalDate.now(), "Female", "number3", "complaint3", "condition3");
//        queue.upHeap(p1);
//        queue.upHeap(p3);
//        queue.upHeap(p2);
        //JOptionPane.showMessageDialog(null, queue.poll().getName());
        //JOptionPane.showMessageDialog(null, queue.peek().getName());
    }
    private void addToList(){
//        PriorityQueue copy = queue.duplicateQueue();
//        while (!copy.isEmpty()){
//            PatientModel p1 = copy.poll();
//            patients.add(p1);
//        }
    }

 */
    private int[] counter;
    public void setCounter(int[] arr){
        this.counter=arr;
        displayValues();
    }

    public void setLbl(String txt){
        patientsTxtArea.setText(txt);
    }

    public void displayValues(){
        resuscitationCount_lbl.setText(counter[0]+"");
        emergencyCount_lbl.setText(counter[1]+"");
        urgentCount_lbl.setText(counter[2]+"");
        semiUrgentCount_lbl.setText(counter[3]+"");
        nonUrgentCount_lbl.setText(counter[4]+"");
    }
    public void setPatientQueue(PriorityQueue<PatientModel> patientQueue){
        this.patientQueue = patientQueue;
    }

    public void observables(){
        PriorityQueue<PatientModel> copy = new PriorityQueue<>();
        copy.addAll(patientQueue);

        while (!copy.isEmpty()){
            PatientModel p = copy.poll();
            //list.add(p);
        }
    }


    public void initialize() {
        try {
            PatientModel[] patientModels = patientQueue.toArray(new PatientModel[patientQueue.size()]);
            // Convert the Queue to an ObservableList
            ObservableList<PatientModel> list = FXCollections.observableArrayList(patientModels);

            // Set the cell value factories for each column
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            contactNumColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
            ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
            genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
            complaintColumn.setCellValueFactory(new PropertyValueFactory<>("complaint"));
            conditionColumn.setCellValueFactory(new PropertyValueFactory<>("condition"));
            levelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));

            // Set the data source for the TableView
            queueTable.setItems(list);
        } catch (Exception e){
        }



    }


    public void back(ActionEvent event) throws IOException {
        ArrayList<String> patients = new ArrayList<>();
        PriorityQueue<PatientModel> copy = new PriorityQueue<>(patientQueue); // Create a copy
        while (!copy.isEmpty()) {
            PatientModel p = copy.poll();
                patients.add(p.getData2());
        }
        myListView.getItems().addAll(patients);
        /*
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Patient1> copy = new PriorityQueue<>(patientQueue); // Create a copy

        while (!copy.isEmpty()) {
            Patient1 p = copy.poll();
            sb.append(p.getName()).append("\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString());



        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dsa/triageapp/triageForm-view.fxml"));
        Parent root = loader.load();

        TriageFormController viewTriageQueue = loader.getController();
        viewTriageQueue.setCounter(counter);
        viewTriageQueue.setPatientQueue(patientQueue);

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene newScene = new Scene(root);
        currentStage.setScene(newScene);
        currentStage.centerOnScreen();
        currentStage.show();

         */
    }

    public void addToListView(){
        ArrayList<String> strArr = new ArrayList<>();

    }
}
