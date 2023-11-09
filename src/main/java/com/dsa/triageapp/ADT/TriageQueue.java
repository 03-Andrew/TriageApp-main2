package com.dsa.triageapp.ADT;


import com.dsa.triageapp.TriageTicket;

public class TriageQueue {
    // Min heap
    private TriageTicket[] array;
    private int count, capacity;
    private final int arraySizeIncrement = 5;

    public TriageQueue() {
        capacity = 5;
        array = new TriageTicket[capacity + 1];
        count = 0;
    }

    public TriageQueue(int capacity) {
        this.capacity = capacity;
        array = new TriageTicket[capacity + 1];
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
        array = new TriageTicket[capacity + 1];
        System.out.println(capacity + 1);
    }

    public int getCapacity() {
        return capacity;
    }

    public void increaseArraySize() {
        TriageTicket[] temp = new TriageTicket[capacity + arraySizeIncrement + 1];
        // Copy the array
        for (int i = 0; i < array.length; i++) {
            temp[i] = array[i];
        }
        // Set the values
        array = temp;
        capacity += arraySizeIncrement;
    }

    public void upHeap(TriageTicket value) {
        if (count + 1 > capacity) {
            increaseArraySize();
        }
        array[++count] = value;
        int index = count;
        while (index > 1 && array[index].getPriorityNumber() < array[index / 2].getPriorityNumber()) {
            // Swap the current element with its parent if it is smaller
            TriageTicket temp = array[index];
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

    public String deleteNode(int value) {
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
                smallerChild = (array[rightChild].getPriorityNumber()
                        < array[leftChild].getPriorityNumber()) ? rightChild : leftChild;
            }


            // If the smaller child is lesser than the current element, swap them
            if (array[smallerChild].getPriorityNumber() < array[index].getPriorityNumber()) {
                TriageTicket temp = array[smallerChild];
                array[smallerChild] = array[index];
                array[index] = temp;

                index = smallerChild; // Move down to the smaller child index
            } else {
                break; // Heap property is restored, stop down-heaping
            }
        } //end of while
    } //end of method

    public void heapify(int count, TriageTicket[] array) {
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
                smallerChild = (array[rightChild].getPriorityNumber()
                        < array[leftChild].getPriorityNumber()) ? rightChild : leftChild;
            }


            // If the smaller child is lesser than the current element, swap them
            if (array[smallerChild].getPriorityNumber() < array[index].getPriorityNumber()) {
                TriageTicket temp = array[smallerChild];
                array[smallerChild] = array[index];
                array[index] = temp;

                index = smallerChild; // Move down to the smaller child index
            } else {
                break; // Heap property is restored, stop down-heaping
            }
        } //end of while
    } //end of method

    public TriageTicket[] heapSort() {
        TriageTicket[] sorted = new TriageTicket[count + 1];
        TriageTicket[] copy = array;

        int index = 0, counter = count;
        for (int i = counter; i >= 1; i--, index++) {
            sorted[index] = copy[1]; // Store the minimum element in the sorted array
            copy[1] = copy[i]; // Move the last element to the root
            counter--;

            heapify(counter, copy); // Restore the heap property
        }
        return sorted;
    }

    public boolean checkDuplicate(int value) {
        for (int i = 1; i <= count; i++) {
            if (array[i].getPriorityNumber() == value) {
                return true;
            }
        } //end of loop
        return false;
    }//end of method

    private int getIndex(int value) {
        for (int i = 1; i <= count; i++) {
            if (array[i].getPriorityNumber() == value) {
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
                hold += array[i].getInfo() + " \n";
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

    public String peek() {
        return (isEmpty()) ? "Queue is Empty" : array[1].getInfo();
    }
    public TriageTicket poll() {
        if (isEmpty()) {
            return null; // or throw an exception based on your preference
        }

        TriageTicket root = array[1];
        array[1] = array[count--];
        heapify();

        return root;
    }
    public TriageQueue duplicateHeap() {
        TriageQueue duplicate = new TriageQueue(capacity); // create a new instance with the same capacity

        // Copy the elements to the new heap
        for (int i = 1; i <= count; i++) {
            duplicate.array[i] = array[i];
        }

        duplicate.count = count; // Set the count of the new heap

        return duplicate;
    }
}