package com.dsa.triageapp.Classes;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class Patient1 {
    private String name;
    private String gender;
    private int age;
    private String complaint;
    private String condition;
    private int level;
    private LocalDate birthDate;

    public Patient1(String name, LocalDate date){
        this.name = name;
        birthDate = date;
        age = calculateAge();
    }

      public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getComplaint() {
        return complaint;
    }

    public String getCondition() {
        return condition;
    }

    public int getLevel() {
        return level;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    public LocalDate getBirthDate(){
        return birthDate;
    }
    public void setBirthDate(LocalDate date){
        birthDate = date;
    }

    public String getData(){
        return String.format("%s | %s | %d |\n %s | Priority level: %d\n",name, gender, age,complaint, level);
    }

    public int calculateAge() {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthDate, currentDate);
        return period.getYears();
    }
}
