package com.dsa.triageapp.Classes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;

public class PatientInfo {
    private String name;
    private String contactInfo;
    private LocalDate date;
    private int age;
    private String gender;
    private String address;
    private String occupation;
    private String civilStatus;
    private String nationality;
    private String emergencyContactName;
    private String emergencyContactNumber;
    private String relationship;
    private String allergies;
    private String currMedication;
    private String medicalHistory;


    public PatientInfo(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getAge() {
        return calculateAge();
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getCivilStatus() {
        return civilStatus;
    }

    public void setCivilStatus(String civilStatus) {
        this.civilStatus = civilStatus;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactNumber() {
        return emergencyContactNumber;
    }

    public void setEmergencyContactNumber(String emergencyContactNumber) {
        this.emergencyContactNumber = emergencyContactNumber;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getCurrMedication() {
        return currMedication;
    }

    public void setCurrMedication(String currMedication) {
        this.currMedication = currMedication;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public int calculateAge() {
        LocalDate birthLocalDate = date.atTime(LocalTime.now(ZoneId.systemDefault())).toLocalDate();
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthLocalDate, currentDate);
        return period.getYears();
    }

    public String storeToDB(){
        return name + "||" + contactInfo + "||" + date + "||" + age + "||" + gender + "||" + address + "||" + occupation + "||" + civilStatus + "||" + nationality + "||" + emergencyContactName + "||" + emergencyContactNumber + "||" + relationship + "||" + allergies + "||" + currMedication + "||" + medicalHistory + "\n";

    }


}
