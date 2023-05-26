public class Main {
    public static void main(String[] args) {
        // Create a new binary tree
        BinaryTreeOfInts btoi = new BinaryTreeOfInts();

        // Test put method
        // Add values to the tree
        btoi.put(5); // 5 becomes the root
        btoi.put(3); // 3 goes to the left of 5
        btoi.put(4); // 4 goes to the right of 3
        btoi.put(8); // 8 goes to the right of 5
        btoi.put(2); // 2 goes to the left of 3

        // Check the size of the binary tree, it should be 5
        System.out.println("Size of binary tree: " + btoi.getSize()); // should print 5

        // Test hasValue method
        // Check if 4 exists in the tree
        System.out.println("Does 4 exist in the tree: " + btoi.hasValue(4)); // should print true

        // Test getSumOfAllValues method
        // Calculate the sum of all values in the tree
        System.out.println("Sum of all values in the tree: " + btoi.getSumOfAllValues()); // should print 22 (5+3+4+8+2)

        // Test getValuesInOrder method
        // Get all values in order from largest to smallest
        int[] valuesInOrder = btoi.getValuesInOrder();
        System.out.println("Values in order (largest to smallest): ");
        for (int value : valuesInOrder) {
            System.out.print(value + " "); // should print 8 5 4 3 2
        }
        System.out.println(); // print a new line

        // BinaryTreeOfIntsIterator implements Iterator
        for (int val : btoi) {
            System.out.printf("%d, ", val);
        }
    }
}