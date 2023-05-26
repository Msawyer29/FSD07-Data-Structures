public class Main {
    public static void main(String[] args) {
        // Create a new binary tree
        // BinaryTreeStringInt btsi = new BinaryTreeStringInt();

        // Create a new binary tree where btsi is an instance of BinaryTree<K, V> for the iterator to work
        BinaryTree<String, Integer> btsi = new BinaryTree<>();

        // Test put method
        // Add key-value pairs to the tree
        btsi.put("D", 4);
        btsi.put("B", 2);
        btsi.put("A", 1);
        btsi.put("C", 3);
        btsi.put("E", 5);

        // Test getValByKey method
        // Retrieve values by their keys
        System.out.println("Value for key 'A': " + btsi.getValByKey("A")); // should print 1
        System.out.println("Value for key 'C': " + btsi.getValByKey("C")); // should print 3
        System.out.println("Value for key 'E': " + btsi.getValByKey("E")); // should print 5

        try {
            // Try to retrieve a value for a non-existent key
            System.out.println("Value for key 'F': " + btsi.getValByKey("F"));
        } catch (RuntimeException e) {
            // Should print 'Key not found'
            System.out.println(e.getMessage());
        }

        // Test printAllKeyValPairs method
        // Print all key-value pairs
        System.out.println("\nAll key-value pairs (from smallest key to largest): ");
        btsi.printAllKeyValPairs();

        // Test Iterator
        System.out.println("\nUsing Iterator:");
        for (BinaryTree.Pair<String, Integer> pair : btsi) {
            System.out.println(pair.getKey() + ": " + pair.getValue());
        }
    }
}
