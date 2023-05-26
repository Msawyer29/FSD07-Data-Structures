// replaced types String and int with K and V respectively to make the class generic
// K extends Comparable <K> which allows for the compareTo method on keys of type K to be called

import java.util.ArrayList;
import java.util.Iterator;

// Part B - change to generic, K extends Comparable
public class BinaryTree<K extends Comparable<K>, V> implements Iterable<BinaryTree.Pair<K, V>> {

    // Part F - Implement Iterable interface that iterates over Pair<K,V>
    public static class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        @Override
        public String toString() {
            return "(" + key + ", " + value + ")";
        }
    }

    @Override
    public Iterator<Pair<K, V>> iterator() {
        return new BinaryTreeIterator(getPairsInOrder());
    }

    public class BinaryTreeIterator implements Iterator<Pair<K, V>> {
        private Pair<K, V>[] pairsInOrder;
        private int nextIndex = 0;

        public BinaryTreeIterator(Pair<K, V>[] pairsInOrder){
            this.pairsInOrder = pairsInOrder;
        }

        @Override
        public boolean hasNext() {
            return nextIndex < pairsInOrder.length;
        }

        @Override
        public Pair<K, V> next() {
            return pairsInOrder[nextIndex++];
        }
    }

    // no arraylist use teacher example
    public Pair<K, V>[] getPairsInOrder() {
        ArrayList<Pair<K, V>> pairs = new ArrayList<>();
        getPairsInOrder(root, pairs);
        return pairs.toArray(new Pair[0]);
    }

    // Ask teacher if I am allowed to use ArrayList here?
    private void getPairsInOrder(Node node, ArrayList<Pair<K, V>> pairs) {
        if (node != null) {
            getPairsInOrder(node.left, pairs);
            pairs.add(new Pair<>(node.key, node.value));
            getPairsInOrder(node.right, pairs);
        }
    }

    // Part A
    private class Node {
        K key;
        V value;
        Node left, right;
    }

    Node root;
    private int nodesCount;

    void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node node, K key, V value) {
        if (node == null) {
            nodesCount++;
            Node newNode = new Node();
            newNode.key = key;
            newNode.value = value;
            return newNode;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0)
            node.left = put(node.left, key, value);
        else if (cmp > 0)
            node.right = put(node.right, key, value);
        else
            node.value = value;
        return node;
    }

    public V getValByKey(K key) throws RuntimeException {
        Node currNode = root;
        while (true) {
            if (currNode == null) {
                throw new RuntimeException("Key not found");
            }
            if (currNode.key.equals(key)) {
                return currNode.value; // found key
            }
            if (key.compareTo(currNode.key) < 0) { // go left
                currNode = currNode.left;
            } else { // go right
                currNode = currNode.right;
            }
        }
    }



    // get keys in order alphabetically
//    T[] valuesArray = (T[])java.lang.reflect.Array.newInstance(template.getClass().getComponentType(), size);


    void printAllKeyValPairs() {
        printAllKeyValPairs(root);
    }

    private void printAllKeyValPairs(Node node) {
        if (node != null) {
            printAllKeyValPairs(node.left);
            System.out.println(node.key + ": " + node.value);
            printAllKeyValPairs(node.right);
        }
    }
}

