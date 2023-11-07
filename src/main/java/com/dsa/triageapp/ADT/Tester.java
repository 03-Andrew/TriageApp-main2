package com.dsa.triageapp.ADT;

import com.dsa.triageapp.Classes.Patient1;

import javax.swing.*;
import java.nio.channels.Pipe;
import java.time.LocalDate;
import java.util.Arrays;

public class Tester {
    public static void main(String[] args) {
        PriorityQueue priorityQueue = new PriorityQueue();
        Patient1 p = new Patient1("bob", LocalDate.now());
        p.setLevel(3);
        Patient1 p2 = new Patient1("Bbbb", LocalDate.now());
        p.setLevel(1);
        priorityQueue.upHeap(p);
        priorityQueue.upHeap(p2);
        JOptionPane.showMessageDialog(null, Arrays.stream(priorityQueue.heapSort()).toArray());
        System.out.println("Prio: " + priorityQueue.heapCount());
        Patient1[] patient1 = priorityQueue.heapSort();
        for(int i = 0; i < priorityQueue.heapCount(); i++){
            JOptionPane.showMessageDialog(null, patient1[i].getLevel());
        }



    }
}
