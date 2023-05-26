import org.w3c.dom.Node; // what is this used for?

import java.util.Iterator;


public class BinaryTreeOfInts implements Iterable<Integer>{

    // Part C - iterator implementation
    @Override
    public Iterator<Integer> iterator() {
        return new BinaryTreeOfIntsIterator(getValuesInOrder());
    }
    public class BinaryTreeOfIntsIterator implements Iterator<Integer> {

        private int[] valuesInOrder;
        int nextIndex = 0;

        public BinaryTreeOfIntsIterator(int [] valuesInOrder){
            this.valuesInOrder = valuesInOrder;
        }

        @Override
        public boolean hasNext() {
            return nextIndex < valuesInOrder.length;
        }

        @Override
        public Integer next() {
            return valuesInOrder[nextIndex++];
        }
    }

    private class NodeOfInt {
        int value; // could also be key, value pair
        NodeOfInt left, right;
    }

    NodeOfInt root;
    private int nodesCount;

    // throws exception if put attempts to insert value that already exists (a duplicate)
    void put(int value) throws IllegalArgumentException {
        if (root == null) { // special case if tree is empty
            NodeOfInt newNode = new NodeOfInt();
            newNode.value = value;
            root = newNode;
            nodesCount++;
            return;
        }
        NodeOfInt currNode = root;
        while (true) { // case for duplicates
            if (currNode.value == value) {
                throw new IllegalArgumentException("Duplicates not allowed");
            }
            if (value < currNode.value) { // if value is less than curr Node GO LEFT - 2nd scenario
                if (currNode.left == null) {
                    // case where we found the spot to put the new node in
                    NodeOfInt newNode = new NodeOfInt();
                    newNode.value = value;
                    currNode.left = newNode;
                    nodesCount++;
                    return;
                } else {
                    // follow to the left on the next iteration
                    currNode = currNode.left;
                    }
                } else { // go right
                    if (currNode.right == null) {
                        // found the spot to put the new node in
                        NodeOfInt newNode = new NodeOfInt();
                        newNode.value = value;
                        currNode.right = newNode;
                        nodesCount++;
                        return;
                    } else {
                        // follow to the left on the next iteration
                        currNode = currNode.right;
                        // continue;
                    }
                }
            }
        }

    public int getSize() {
        return nodesCount;
    }

    // traverse the binary tree
    public boolean hasValue(int val) {
        NodeOfInt currNode = root;
        while (currNode != null) {
            if (currNode.value == val) { // start from the root and at each node check if values equals val
                return true;
            } else if (val < currNode.value) { // move to the left child if val is less than the current node's value
                currNode = currNode.left;
            } else { //  move to the right child if val is greater
                currNode = currNode.right;
            }
        }
        return false;
    }


    // uses recursion to compute the sum of all values in the entire tree
    public int getSumOfAllValues() {
        return getSumOfThisAndSubNodes(root);
    }

    // private helper recursive method to implement the above method
    private int getSumOfThisAndSubNodes(NodeOfInt node) {
        if (node == null) { // base case if node is null
            return 0;
        } else { // return the sum of the node's value and the sums of its left and right subtrees
            return node.value + getSumOfThisAndSubNodes(node.left) + getSumOfThisAndSubNodes(node.right);
        }
    }


    // uses recursion to collect all values from largest to smallest
    int [] getValuesInOrder() { // from largest to smallest
        resultArray = new int[nodesCount];
        resultIndex = 0;
        collectValuesInOrder(root);
        return resultArray;
    }

    // private helper recursive method to implement the above method
    // start from the right child, visit the root and then the left child
    private void collectValuesInOrder(NodeOfInt node) {
        if (node != null) { // base case if node is null
            collectValuesInOrder(node.right);
            resultArray[resultIndex++] = node.value;
            collectValuesInOrder(node.left);
        }
    }

    // data structures used to make collecting values in order easier
    private int[] resultArray;
    private int resultIndex;
}
