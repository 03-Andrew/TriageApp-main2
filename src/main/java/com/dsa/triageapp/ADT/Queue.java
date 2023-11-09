package com.dsa.triageapp.ADT;

public class Queue<T> { // Renamed class and added generics
    private int front, rear, capacity;
    private T[] patient;

    public Queue() {
        capacity = 5; // Use a constant or configuration property
        patient = (T[]) new Object[capacity];
        front = rear = -1;
    }

    public Queue(int capacity) {
        this.capacity = capacity;
        patient = (T[]) new Object[capacity];
        front = rear = -1;
    }

    public boolean isEmpty() {
        return rear == -1;
    }

    public boolean isFull() {
        return rear == capacity - 1;
    }

    public boolean enqueue(T data) {
        if (isFull()) {
            return false;
        }
        patient[++rear] = data;
        if (front == -1) {
            front = 0;
        }
        return true;
    }

    public T dequeue() {
        if (isEmpty()) {
            return null;
        }

        T val = patient[front];
        for (int i = front; i < rear; i++) {
            patient[i] = patient[i + 1];
        }
        rear--;

        if (isEmpty()) {
            front = -1;
        }

        return val;
    }

    public String display2() {
        return displayHelper("head<-", " ", "<-rear");
    }

    public String display3() {
        return displayHelper("rear->", " ", "->head");
    }

    private String displayHelper(String prefix, String separator, String suffix) {
        if (isEmpty()) {
            return "Queue is empty";
        }
        StringBuilder hold = new StringBuilder(prefix);
        for (int i = front; i <= rear; i++) {
            hold.append(patient[i]);
            if (i < rear) {
                hold.append(separator);
            }
        }
        hold.append(suffix);
        return hold.toString();
    }

    public T peek() {
        return isEmpty() ? null : patient[front];
    }

    public T last() {
        return isEmpty() ? null : patient[rear];
    }

    public T frontValue() {
        return isEmpty() ? null : patient[front];
    }

    public T rearView() {
        return isEmpty() ? null : patient[rear];
    }

    public int getCurrentSize() {
        return isEmpty() ? 0 : rear - front + 1;
    }

    public int getCapacity() {
        return capacity;
    }
}
