import java.util.Iterator;
import java.util.NoSuchElementException;

public class RoundFIFOQueue<T extends Comparable<T>> implements Iterable<T> {

    // implement the iterator method
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node current = dequeue;
            private int counter = 0;

            @Override
            public boolean hasNext() {
                return counter < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = current.value;
                current = current.next;
                counter++;
                return value;
            }
        };
    }


    private class Node {
        T value;
        Node next;
        int debugId; // optional, for debugging only
    }

    private Node enqueue, dequeue;
    int size;
    final int maxSize; // maxSize will not change once assigned in the constructor

    public RoundFIFOQueue(int maxSize) {
        this.maxSize = maxSize;
        Node head = new Node();
        head.debugId = 0;
        Node curr = head;
        // Here I am creating a circular linked list
        for (int i = 1; i < maxSize; i++) {
            Node newNode = new Node();
            newNode.debugId = i;
            curr.next = newNode;
            curr = newNode;
        }
        curr.next = head;  // making the list circular

        // In the beginning enqueue and dequeue will point to head
        enqueue = head;
        dequeue = head;
    }

    // printQueue method prints out each Node's content on a separate line
    public void printQueue() {
        // Traversing the circular linked list
        Node curr = enqueue;
        for (int i = 0; i < maxSize; i++) {
            System.out.println("Node #" + curr.debugId + ": " + curr.value);
            curr = curr.next;
        }
        if (curr != enqueue) {
            throw new RuntimeException("Internal error: queue may not be circular");
        }
    }

    // puts value into the queue
    // throws RuntimeException("No space in the queue") if queue is full
    public void enqueue(T value) {
        if (size == maxSize) {
            throw new RuntimeException("No space in the queue");
        }
        enqueue.value = value;
        enqueue = enqueue.next;
        size++;
    }

    // removes and returns value from the queue (in FIFO order)
    // throws RuntimeException("Queue is empty") if queue is empty
    public T dequeue() {
        if (size == 0) {
            throw new RuntimeException("Queue is empty");
        }
        T value = dequeue.value;
        dequeue.value = null; // clear the value
        dequeue = dequeue.next;
        size--;
        return value;
    }

    // size method to return the current size of the queue
    public int size() {
        return size;
    }

    // toArray method creates a new array of the appropriate/correct size
    // returns array of "size" number of elements with most recently enqueued elements first (following "next" references)
    public T[] toArray(T[] template) {
        if (template.length < size)
            throw new IllegalArgumentException("The provided array is smaller than the queue size.");


        // I understand that in a circular queue the oldest value (which should be the first in a FIFO structure)
        // is at the dequeue position and the newest value is just before the enqueue position
        Node current = dequeue; // Start from the node at dequeue position (oldest value)
        for (int i = 0; i < size; i++) {  // fill it from the oldest element to the most recent
            template[i] = current.value;
            current = current.next; // follow the next references
        }

        // If I want the array to be filled from with most recently enqueued elements first
        // I then need to manually reverse the array
        for (int i = 0; i < size / 2; i++) {
            T temp = template[i];
            template[i] = template[size - 1 - i];
            template[size - 1 - i] = temp;
        }

        return template; // return the reversed array (with most recently enqueued elements first)
    }

    // countValues method traverse the circular queue
    // it counts the occurrences of the given value
    public int countValues(T value) {
        int count = 0; // initialize counter
        Node curr = enqueue;
        for (int i = 0; i < size; i++) { // loop through each node in the queue
            if (curr.value.compareTo(value) == 0) {
                count++; // if the value at the current node matches the value provided increment the counter
            }
            curr = curr.next; // then move to the next node
        }
        return count; // return the final number of occurrences of the value provided
    }

}

