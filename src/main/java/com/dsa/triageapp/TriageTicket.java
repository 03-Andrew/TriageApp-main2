package com.dsa.triageapp;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TriageTicket {
    Patient patientInfo;
    SimpleStringProperty chiefComplaint, currentCondition, priorityLevel, patientNumber, name, number, gender;
    SimpleIntegerProperty priorityNumber, age;
    public TriageTicket(Patient patientInfo, String chiefComplaint, String currentCondition, String priorityLevel){
        this.patientInfo = patientInfo;
        this.chiefComplaint = new SimpleStringProperty(chiefComplaint);
        this.currentCondition = new SimpleStringProperty(currentCondition);
        this.priorityLevel = new SimpleStringProperty(priorityLevel);
        this.priorityNumber = new SimpleIntegerProperty(getPriority(priorityLevel));
    }
    public TriageTicket(int priorityNumber){
        this.priorityNumber = new SimpleIntegerProperty(priorityNumber);
    }
    public Patient getPatientInfo() {
        return patientInfo;
    }
    public String getName() {return patientInfo.getName();}
    public String getNumber() {return patientInfo.getContactNumber();}
    public String getGender() {return patientInfo.getGender();}
    public int getAge() {return patientInfo.getAge();}

    public void setPatientInfo(Patient patientInfo) {
        this.patientInfo = patientInfo;
    }

    public String getChiefComplaint() {
        return chiefComplaint.get();
    }

    public void setChiefComplaint(String chiefComplaint) {
        this.chiefComplaint.set(chiefComplaint);
    }

    public String getCurrentCondition() {
        return currentCondition.get();
    }

    public void setCurrentCondition(String currentCondition) {
        this.currentCondition.set(currentCondition);
    }

    public String getPriorityLevel() {
        return priorityLevel.get();
    }

    public void setPriorityLevel(String priorityLevel) {
        this.priorityLevel.set(priorityLevel);
    }

    public String getPatientNumber() {
        return patientNumber.get();
    }

    public void setPatientNumber(String patientNumber) {
        this.patientNumber.set(patientNumber);
    }

    public int getPriorityNumber() {
        return priorityNumber.get();
    }

    public void setPriorityNumber(int priorityNumber) {
        this.priorityNumber.set(priorityNumber);
    }

    public String getInfo(){
        return "Patient Priority: " + priorityNumber;
    }

    public int getPriority(String selectedPriority){
        return switch (selectedPriority) {
            case "Resuscitation" -> 1;
            case "Emergency" -> 2;
            case "Urgent" -> 3;
            case "Semi-urgent" -> 4;
            case "Non-urgent" -> 5;
            default -> 0;
        };
    }
}
