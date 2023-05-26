public class BinaryTreeStringInt {
    private class Node {
        String key;
        int value;
        Node left, right;
    }

    Node root;
    private int nodesCount;

    // if put attempts to insert a key that already exists then value is updated (no exception is thrown)
    // values may be duplicates but keys may not
    void put(String key, int value) {
        root = put(root, key, value);
    }

    private Node put(Node node, String key, int value) {
        if (node == null) {
            nodesCount++;
            Node newNode = new Node();
            newNode.key = key;
            newNode.value = value;
            return newNode;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(node.left, key, value);
        else if (cmp > 0) node.right = put(node.right, key, value);
        else node.value = value; // update value if key already exists
        return node;
    }

    // throws exception if key is not found, otherwise returns the value
    public int getValByKey(String key) throws RuntimeException {
        Node node = root;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) node = node.left;
            else if (cmp > 0) node = node.right;
            else return node.value; // key found
        }
        throw new RuntimeException("Key not found"); // key not found
    }

    // print out all key-value pairs (one per line) from the smallest key to the largest
    void printAllKeyValPairs() {
        printAllKeyValPairs(root);
    }

    private void printAllKeyValPairs(Node node) {
        if (node != null) {
            printAllKeyValPairs(node.left); // print keys in left subtree
            System.out.println(node.key + ": " + node.value); // print key value of current node
            printAllKeyValPairs(node.right); // print keys in right subtree
        }
    }
}