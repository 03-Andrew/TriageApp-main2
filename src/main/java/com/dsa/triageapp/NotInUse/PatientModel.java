package com.dsa.triageapp.NotInUse;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.time.Period;

public class PatientModel {
    private SimpleStringProperty name;
    private SimpleStringProperty gender;
    private SimpleIntegerProperty age;
    private SimpleStringProperty number;
    private SimpleStringProperty complaint;
    private SimpleStringProperty condition;
    private SimpleIntegerProperty level;
    private LocalDate birthDate;

    public PatientModel(SimpleStringProperty name, LocalDate date) {
        this.name = name;
        birthDate = date;
        age = calculateAgeProperty();
    }

    public PatientModel(Integer level, String name, LocalDate date, String gender, String contactNum, String complaint, String condition) {
        this.level = new SimpleIntegerProperty(level);
        this.name = new SimpleStringProperty(name);
        this.birthDate = date;
        this.age = calculateAgeProperty();
        this.gender = new SimpleStringProperty(gender);
        this.number = new SimpleStringProperty(contactNum);
        this.complaint = new SimpleStringProperty(complaint);
        this.condition = new SimpleStringProperty(condition);
    }

    public String getNumber() {
        return number.get();
    }

    public void setNumber(String number) {
        this.number = new SimpleStringProperty(number);
    }

    public String getName() {
        return name.get();
    }

    public String getGender() {
        return gender.get();
    }

    public int getAge() {
        return age.get();
    }

    public String getComplaint() {
        return complaint.get();
    }

    public String getCondition() {
        return condition.get();
    }

    public int getLevel() {
        return level.get();
    }


    // Setters
    public void setName(String name) {
        this.name.set(name);
    }

    public void setGender(String  gender) {
        this.gender.set(gender);
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public void setComplaint(String complaint) {
        this.complaint.set(complaint);
    }

    public void setCondition(String condition) {
        this.condition.set(condition);
    }

    public void setLevel(int level) {
        this.level.set(level);
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate date) {
        birthDate = date;
        age = calculateAgeProperty();
    }

    public String getData() {
        return String.format("Patient: %s, %s, %d |\n %s | Priority level: %d\n", name.get(), gender.get(), age.get(), complaint.get(), level.get());
    }

    public String getData2() {
        return String.format("Priority level: %d Patient: %s, %s, %d, %s\t Complaint: %s\t Condition: %s", level.get(), name.get(), gender.get(), age.get(), number.get(), complaint.get(), condition.get());
    }
    /*
    public String dataToFile() {
        return String.format("%d||%s||%s||%d||%s||%", level.get(), name.get(), gender.get(), age.get(), number.get());
    }

     */
    public SimpleIntegerProperty calculateAgeProperty() {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthDate, currentDate);
        int years = period.getYears();
        return new SimpleIntegerProperty(years);
    }
}


