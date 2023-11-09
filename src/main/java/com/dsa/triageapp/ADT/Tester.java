package com.dsa.triageapp.ADT;

import com.dsa.triageapp.NotInUse.PatientModel;

import java.time.LocalDate;

public class Tester {
    public static void main(String[] args) {
        PriorityQueue q = new PriorityQueue();
        PatientModel p = new PatientModel(3, "qwerttyuiy", LocalDate.now(), "Male","0987654321","Headache","inpain");
        PatientModel p2 = new PatientModel(4, "qwerty", LocalDate.now(), "Male","0987654321","Headache","inpain");
        q.upHeap(p);
        q.upHeap(p2);
        System.out.println(q.poll());
        System.out.println(q.poll().getName());
    }
}
