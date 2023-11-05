package com.dsa.triageapp.Classes;

public class PatientQueue implements Comparable<PatientQueue> {
    private Patient patient;
    private int level;

    public PatientQueue(Patient patient, int level) {
        this.patient = patient;
        this.level = level;
    }

    // Getters
    public Patient getPatient() {
        return patient;
    }

    public int getLevel() {
        return level;
    }

    // Setters
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public int compareTo(PatientQueue other) {
        // Compare based on the 'level' attribute, which represents priority
        return Integer.compare(this.level, other.level);
    }
}
