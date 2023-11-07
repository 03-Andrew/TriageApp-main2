package com.dsa.triageapp.ADT;

import com.dsa.triageapp.Classes.Patient1;

public class PriorityQueue {
    // Min heap
    private Patient1[] array;
    private int count, capacity;
    private final int arraySizeIncrement = 5;

    public PriorityQueue() {
        capacity = 5;
        array = new Patient1[capacity + 1];
        count = 0;
    }

    public PriorityQueue(int capacity) {
        this.capacity = capacity;
        array = new Patient1[capacity + 1];
        count = 0;
    }

    public int heapCount() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count >= (capacity);
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
        array = new Patient1[capacity + 1];
        System.out.println(capacity + 1);
    }

    public int getCapacity() {
        return capacity;
    }

    public void increaseArraySize() {
        Patient1[] temp = new Patient1[capacity + arraySizeIncrement + 1];
        // Copy the array
        for (int i = 0; i < array.length; i++) {
            temp[i] = array[i];
        }
        // Set the values
        array = temp;
        capacity += arraySizeIncrement;
    }

    public void upHeap(Patient1 value) {
        if (count + 1 > capacity) {
            increaseArraySize();
        }
        array[++count] = value;
        int index = count;
        while (index > 1 && array[index].getLevel() < array[index / 2].getLevel()) {
            // Swap the current element with its parent if it is smaller
            Patient1 temp = array[index];
            array[index] = array[index / 2];
            array[index / 2] = temp;

            index = index / 2; // Move up to the parent index
        }
    }

    public String delete() {
        // Adrian Carlo A. Menguita
        if (count != 0) {
            String hold = array[1] + " has been removed from the heap";
            array[1] = array[count--];
            heapify();
            return hold;
        } else return "Heap is empty!";
    }
    public Patient1 poll() {
        if (count == 0) {
            // Handle the case when the heap is empty
            return null; // Or throw an exception or return a default value
        }

        Patient1 p = array[0];
        array[0] = array[count - 1]; // Move the last element to the root
        count--; // Decrease the count

        heapify(); // Restore the heap property

        return p;
    }


    public String deleteNode(Patient1 value) {
        // Adrian Carlo A. Menguita
        int nodeIndex = getIndex(value);
        if (nodeIndex == -1) return "Value is not present within the heap";
        String hold = array[nodeIndex] + " has been removed from the heap";
        array[nodeIndex] = array[count--];
        heapify();
        return hold;
    }

    public void heapify() {
        if (count <= 1) {
            return; // No need to down-heap if there's only one element or no elements
        }
        int index = 1;
        while (index <= count / 2) {
            int smallerChild;

            int leftChild = index * 2;
            if (leftChild > count) break; // The current element is a leaf
            int rightChild = index * 2 + 1;
            if (rightChild > count) {
                // Only the left child exist;
                smallerChild = leftChild;
            } else {
                // Find the smaller child
                smallerChild = (array[rightChild].getLevel() < array[leftChild].getLevel()) ? rightChild : leftChild;
            }


            // If the smaller child is lesser than the current element, swap them
            if (array[smallerChild].getLevel() < array[index].getLevel()) {
                Patient1 temp = array[smallerChild];
                array[smallerChild] = array[index];
                array[index] = temp;

                index = smallerChild; // Move down to the smaller child index
            } else {
                break; // Heap property is restored, stop down-heaping
            }
        } //end of while
    } //end of method

    public void heapify(int count, Patient1[] array) {
        if (count <= 1) {
            return; // No need to down-heap if there's only one element or no elements
        }
        int index = 1;
        while (index <= count / 2) {
            int smallerChild;

            int leftChild = index * 2;
            if (leftChild > count) break; // The current element is a leaf
            int rightChild = index * 2 + 1;
            if (rightChild > count) {
                // Only the left child exist;
                smallerChild = leftChild;
            } else {
                // Find the smaller child
                smallerChild = (array[rightChild].getLevel() < array[leftChild].getLevel()) ? rightChild : leftChild;
            }


            // If the smaller child is lesser than the current element, swap them
            if (array[smallerChild].getLevel() < array[index].getLevel()) {
                Patient1 temp = array[smallerChild];
                array[smallerChild] = array[index];
                array[index] = temp;

                index = smallerChild; // Move down to the smaller child index
            } else {
                break; // Heap property is restored, stop down-heaping
            }
        } //end of while
    } //end of method

    public Patient1[] heapSort() {
        Patient1[] sorted = new Patient1[count + 1];
        Patient1[] copy = new Patient1[array.length];
        System.arraycopy(array, 0, copy, 0, array.length);

        int index = 0, counter = count;
        for (int i = counter; i >= 1; i--, index++) {
            sorted[index] = copy[1]; // Store the minimum element in the sorted array
            copy[1] = copy[i]; // Move the last element to the root
            counter--;

            heapify(counter, copy); // Restore the heap property
        }
        return sorted;
    }

     public PriorityQueue duplicateQueue() {
        PriorityQueue newQueue = new PriorityQueue(this.capacity);

        // Copy the elements from the current queue to the new queue
        for (int i = 1; i <= this.count; i++) {
            newQueue.upHeap(this.array[i]);
        }

        return newQueue;
    }

    public boolean checkDuplicate(Patient1 value) {
        for (int i = 1; i <= count; i++) {
            if (array[i] == value) {
                return true;
            }
        } //end of loop
        return false;
    }//end of method

    private int getIndex(Patient1 value) {
        for (int i = 1; i <= count; i++) {
            if (array[i] == value) {
                return i;
            }
        }//end of loop
        return -1; // Value not found in the heap
    }//end of method

    public String displayHeap() {
        String hold = "";

        if (isEmpty()) {
            hold = "Heap is empty!";
        } else {
            for (int i = 1; i <= count; i++) {
                hold += array[i].getData2();
            }//end of loop
        }
        return hold;
    }//end of method

    /**
     * Resets the heap by clearing the elements.
     */
    public void reset() {
        count = 0;
    }//end of method

    public Patient1 peek() {
        return (isEmpty()) ? null : array[0];
    }
    public Patient1[] toArray() {
        Patient1[] result = new Patient1[count];

        // Copy the elements from the queue to the result array
        for (int i = 1; i <= count; i++) {
            result[i - 1] = array[i];
        }

        return result;
    }

}
