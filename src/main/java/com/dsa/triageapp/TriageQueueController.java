package com.dsa.triageapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class TriageQueueController {
    @FXML
    private Label emergencyLbl;

    @FXML
    private ListView<?> listView1;

    @FXML
    private ListView<?> listView2;

    @FXML
    private ListView<?> listView3;

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
}
