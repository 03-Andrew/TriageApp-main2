package com.dsa.triageapp;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.time.Period;

public class Patient {
    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getGender() {
        return gender.get();
    }

    public SimpleStringProperty genderProperty() {
        return gender;
    }

    public int getAge() {
        return age.get();
    }

    public SimpleIntegerProperty ageProperty() {
        return age;
    }

    public String getContactNumber() {
        return contactNumber.get();
    }

    public SimpleStringProperty contactNumberProperty() {
        return contactNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public String getOccupation() {
        return occupation.get();
    }

    public SimpleStringProperty occupationProperty() {
        return occupation;
    }

    public String getCivilStatus() {
        return civilStatus.get();
    }

    public SimpleStringProperty civilStatusProperty() {
        return civilStatus;
    }

    public String getNationality() {
        return nationality.get();
    }

    public SimpleStringProperty nationalityProperty() {
        return nationality;
    }

    public String getEmergencyContactName() {
        return emergencyContactName.get();
    }

    public SimpleStringProperty emergencyContactNameProperty() {
        return emergencyContactName;
    }

    public String getEmergencyContactNumber() {
        return emergencyContactNumber.get();
    }

    public SimpleStringProperty emergencyContactNumberProperty() {
        return emergencyContactNumber;
    }

    public String getRelationship() {
        return relationship.get();
    }

    public SimpleStringProperty relationshipProperty() {
        return relationship;
    }

    public String getAllergies() {
        return allergies.get();
    }

    public SimpleStringProperty allergiesProperty() {
        return allergies;
    }

    public String getCurrMedication() {
        return currMedication.get();
    }

    public SimpleStringProperty currMedicationProperty() {
        return currMedication;
    }

    public String getMedicalHistory() {
        return medicalHistory.get();
    }

    public SimpleStringProperty medicalHistoryProperty() {
        return medicalHistory;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber.set(contactNumber);
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public void setOccupation(String occupation) {
        this.occupation.set(occupation);
    }

    public void setCivilStatus(String civilStatus) {
        this.civilStatus.set(civilStatus);
    }

    public void setNationality(String nationality) {
        this.nationality.set(nationality);
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName.set(emergencyContactName);
    }

    public void setEmergencyContactNumber(String emergencyContactNumber) {
        this.emergencyContactNumber.set(emergencyContactNumber);
    }

    public void setRelationship(String relationship) {
        this.relationship.set(relationship);
    }

    public void setAllergies(String allergies) {
        this.allergies.set(allergies);
    }

    public void setCurrMedication(String currMedication) {
        this.currMedication.set(currMedication);
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory.set(medicalHistory);
    }

    private SimpleStringProperty name;
    private SimpleStringProperty gender;
    private SimpleIntegerProperty age;
    private SimpleStringProperty contactNumber;
    private LocalDate birthDate;
    private SimpleStringProperty address;
    private SimpleStringProperty occupation;
    private SimpleStringProperty civilStatus;
    private SimpleStringProperty nationality;
    private SimpleStringProperty emergencyContactName;
    private SimpleStringProperty emergencyContactNumber;
    private SimpleStringProperty relationship;
    private SimpleStringProperty allergies;
    private SimpleStringProperty currMedication;
    private SimpleStringProperty medicalHistory;

    public Patient(String name, String gender, String contactNumber, LocalDate birthDate, String address,
                   String occupation, String civilStatus, String nationality, String emergencyContactName,
                   String emergencyContactNumber, String relationship, String allergies, String currMedication,
                   String medicalHistory) {
        this.name = new SimpleStringProperty(name);
        this.gender = new SimpleStringProperty(gender);
        this.age = new SimpleIntegerProperty(calculateAgeProperty());
        this.contactNumber = new SimpleStringProperty(contactNumber);
        this.birthDate = birthDate;
        this.address = new SimpleStringProperty(address);
        this.occupation = new SimpleStringProperty(occupation);
        this.civilStatus = new SimpleStringProperty(civilStatus);
        this.nationality = new SimpleStringProperty(nationality);
        this.emergencyContactName = new SimpleStringProperty(emergencyContactName);
        this.emergencyContactNumber = new SimpleStringProperty(emergencyContactNumber);
        this.relationship = new SimpleStringProperty(relationship);
        this.allergies = new SimpleStringProperty(allergies);
        this.currMedication = new SimpleStringProperty(currMedication);
        this.medicalHistory = new SimpleStringProperty(medicalHistory);
    }


    public Patient(String name, String gender, LocalDate date, String contactNumber){
        this.name = new SimpleStringProperty(name);
        this.gender = new SimpleStringProperty(gender);
        this.birthDate = date;
        this.age = new SimpleIntegerProperty(calculateAgeProperty());
        this.contactNumber = new SimpleStringProperty(contactNumber);
    }

    public String dbString1(){
        return name.get()+"#"+gender.get()+"#"+birthDate+"#"+contactNumber.get()+"\n";
    }

    public int calculateAgeProperty() {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthDate, currentDate);
        return period.getYears();
    }
}
