package org.example;

import java.util.NoSuchElementException;

public class PriorityStack<T> {

    private class Container<T> {
        T value;
        boolean hasPriority;
        Container<T> nextBelow;

        public Container(T value, boolean hasPriority, Container<T> nextBelow) {
            this.value = value;
            this.hasPriority = hasPriority;
            this.nextBelow = nextBelow;
        }
    }

    private Container<T> top; // top of the stack element
    private int size;

    // push methods add element to the top of the stack
    public void push(T value) {
        // if a priority is not provided, default to false
        push(value, false);
    }

    public void push(T value, boolean hasPriority) {
        top = new Container<>(value, hasPriority, top);
        size++;
    }

    // pop method
    // removes the top element from the stack and returns its value
    public T pop() {
        if (size == 0) { // if the stack is empty, throw exception
            throw new NoSuchElementException();
        }
        T value = top.value;
        top = top.nextBelow;
        size--; // decrement size after removing top element
        return value;
    }

    // popPriority method
    // go through the stack from the top looking for a priority item
    public T popPriority() {
        if (size == 0) { // if the stack is empty throw an exception
            throw new NoSuchElementException();
        }
        // if priority item is found, remove it and return its value
        // if priority item is not found, remove and return the top item
        Container<T> current = top;
        Container<T> prev = null;
        while (current != null && !current.hasPriority) {
            prev = current;
            current = current.nextBelow;
        }
        if (current == null) {
            return pop();
        } else if (prev == null) {
            top = current.nextBelow;
        } else {
            prev.nextBelow = current.nextBelow;
        }
        size--;
        return current.value;
    }

    // hasValue method
    // go through the stack from the top to the bottom looking for the given value
    public int hasValue(T value) {
        int distance = 0;
        for (Container<T> current = top; current != null; current = current.nextBelow) {
            if (value.equals(current.value)) {
                // return the distance from the top, from 0 and then increment by 1
                return distance; // returning value 0 means the value is on the top of the stack
            }
            distance++;
        }
        return -1; // if the value is not found in the stack, return "-1"
    }

    // removeValue method
    public T removeValue(T value) {
        if (size == 0) { // check if stack is empty, if it is throw an exception
            throw new NoSuchElementException();
        }
        Container<T> current = top; // start from top of stack
        Container<T> prev = null;
        // traverse the stack looking for the value and stop if it's found or stop when reaching the bottom
        while (current != null && !value.equals(current.value)) {
            prev = current;
            current = current.nextBelow;
        }
        if (current == null) { // if the value is not found throw an exception
            throw new NoSuchElementException();
        }
        // if the value is found at top of stack remove it by setting the top to the next item
        // if it is not found at top of stack link the previous item to the next item (this removes the current item)
        if (prev == null) {
            top = current.nextBelow;
        } else {
            prev.nextBelow = current.nextBelow;
        }
        size--; // decrement the stack size
        return current.value; // return the value of the item removed
    }

    public int getSize() {
        return size; // return the number of items in the stack
    }

    public void reorderByPriority() { // create two separate stacks as linked lists
        Container<T> priorityTop = null, priorityBottom = null; // priority stack
        Container<T> nonPriorityTop = null, nonPriorityBottom = null; // non-priority stack
        // traverse original stacks and copy relevant items to the designated new stack based on priority status
        // maintain the original order within the new stacks
        for (Container<T> current = top; current != null; current = current.nextBelow) {
            Container<T> newContainer = new Container<>(current.value, current.hasPriority, null);
            if (current.hasPriority) {
                if (priorityTop == null) {
                    priorityTop = newContainer;
                } else {
                    priorityBottom.nextBelow = newContainer;
                }
                priorityBottom = newContainer;
            } else {
                if (nonPriorityTop == null) {
                    nonPriorityTop = newContainer;
                } else {
                    nonPriorityBottom.nextBelow = newContainer;
                }
                nonPriorityBottom = newContainer;
            }
        }
        // combine the new stacks together with priority items on the top
        if (priorityBottom == null) {
            top = nonPriorityTop;
        } else {
            priorityBottom.nextBelow = nonPriorityTop;
            top = priorityTop;
        }
    }

    // toString method
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(); // use StringBuilder to create strings
        sb.append('[');
        for (Container<T> current = top; current != null; current = current.nextBelow) { // traverse stack, add each items value and priority status to the string
            if (sb.length() > 1) {
                sb.append(','); // separate each item using commas
            }
            sb.append(current.value.toString()); // append the value and priority status to the string
            sb.append(':');
            sb.append(current.hasPriority ? 'P' : 'N');
        }
        sb.append(']');
        return sb.toString(); // convert to string
    }

    // toArrayReversed method
    // add fields to implement toArrayReversed
    private T[] reversed;
    private int reversedCount;

    // create a new array T[] that is the same size of the stack size
    public T[] toArrayReversed(T[] array) {
        // implement reversed array and reversedCounter
        reversed = array;
        reversedCount = 0;

        // starting from the top of the stack call the reverse helper method
        toArrayReversedHelperMethod(top);

        return reversed;
    }

    // the twist? add a toArrayReversedHelperMethod
    private void toArrayReversedHelperMethod(Container<T> current) {
        // return if the stack is empty or the bottom of the stack has been reached
        if (current == null) {
            return;
        }

        // go to the next element in the stack to build it recursively
        toArrayReversedHelperMethod(current.nextBelow);

        // element 0 must be at the bottom of the stack
        // when traversing back up the stack add the current element to the reversed array
        reversed[reversedCount++] = current.value;
    }

}