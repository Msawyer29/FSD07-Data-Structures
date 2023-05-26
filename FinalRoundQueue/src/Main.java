public class Main {
    public static void main(String[] args) {

        // Here I create a queue of integers where the queue has a maximum size of 5
        RoundFIFOQueue<Integer> queue = new RoundFIFOQueue<>(5);

        // Enqueue the values: 1, 2, 3, 4, 5
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        // Here I try to enqueue a 6th value
        // This should throw an exception and print, "No space in the queue", because the queue is full
        System.out.println("\nPrint a message from the exception thrown caused by the queue being full:");
        try {
            queue.enqueue(6);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        // Print the queue
        // Should print:
        // Node #0: 1
        // Node #1: 2
        // Node #2: 3
        // Node #3: 4
        // Node #4: 5
        System.out.println("\nPrint queue to show the values 1, 2, 3, 4, 5 were added to the queue:");
        queue.printQueue();

        // Dequeue and then print the dequeued value
        // Should print: "Dequeued: 1"
        System.out.println("\nDequeued: " + queue.dequeue());

        // Print the queue again to show that the first value (1) has been dequeued
        // Should print:
        // Node #0: null
        // Node #1: 2
        // Node #2: 3
        // Node #3: 4
        // Node #4: 5
        System.out.println("\nPrint queue to show the first value was removed:");
        queue.printQueue();

        // Enqueue a new value to show that it gets put in the correct spot
        // Should print:
        // Node #1: 2
        // Node #2: 3
        // Node #3: 4
        // Node #4: 5
        // Node #0: 6
        System.out.println("\nPrint queue to show the new value (6) was added to the end of the queue:");
        queue.enqueue(6);
        queue.printQueue();

        // Count the occurrences of a value
        // Should print: "Occurrences of 6: 1"
        System.out.println("\nOccurrences of 6: " + queue.countValues(6));

        // Convert the queue to an array
        Integer[] array = new Integer[queue.size()]; // Create an array with the same size as the queue
        array = queue.toArray(array); // Fill the array with the queue's elements
        // Print the array
        System.out.println("\nPrint the contents of the queue as an array with most recently enqueued elements first:");
        System.out.print("[");
        for(int i=0; i<array.length; i++) {
            if(i > 0) {
                System.out.print(", ");
            }
            System.out.print(array[i]);
        }
        System.out.println("]");


        // Iterate over the queue with a for loop
        // Should print:
        // 2
        // 3
        // 4
        // 5
        // 6
        System.out.println("\nPrint the contents of the queue by iterating over the queue with a for loop:");
        for (int value : queue) {
            System.out.println(value);
        }
    }
}
