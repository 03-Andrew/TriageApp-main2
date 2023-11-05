package com.dsa.triageapp.Classes;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class Patient implements Comparable<Patient> {
    private String firstName;
    private String lastName;
    private String middleName;
    private String contactNum;
    private Date birthDate;
    private int age;
    private String gender;
    private String homeAddress;
    private String civilStatus;
    private String occupation;
    private int priority;
    private String complaint;
    private String condition;

    public Patient(){

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getCivilStatus() {
        return civilStatus;
    }

    public void setCivilStatus(String civilStatus) {
        this.civilStatus = civilStatus;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setCondition(String condition){
        this.condition = condition;
    }
    public String getCondition(){
        return condition;
    }

    public void setComplaint(String complaint){
        this.complaint = complaint;
    }
    public String getComplaint(){
        return complaint;
    }
     public int calculateAge() {
        LocalDate birthLocalDate = birthDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthLocalDate, currentDate);
        return period.getYears();
    }

    @Override
    public int compareTo(Patient other) {
        // You can define the priority order as you need here.
        // For example, lower priority values come first.
        return Integer.compare(this.priority, other.priority);
    }

}
