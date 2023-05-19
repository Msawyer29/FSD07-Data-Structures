package org.example;

public class LinkedListArray<T> { // T = Type, single letter is common practice
    private class Container {
        Container next;
        T value;
    }
    private Container start, end;
    private int size;

    // add method
    public void add(T value) {
        // create a new container object, set its value field to the passed value
        Container newContainer = new Container();
        newContainer.value = value;

        if (start == null) { // list empty - special case
            start = newContainer;
        } else { // list not empty - general case
            end.next = newContainer; // add the new Container at the end of the LinkedList by setting the next field of the current end to the new Container
        }
        end = newContainer;
        size++; // increment the size
    }


    // get method
    public T get(int index) {
        // check if the passed index is valid, if < or = to 0, throw out of bounds exception
        if (index < 0 || index >= size) { // watch out for "off by one error" must be ">=" NOT ">"
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        // if valid, begin from the start of LinkedList and keep following the next pointers until moved "index" steps
        Container current = start;
        for (int i = 0; i < index; i++) { // while loop is not appropriate
            current = current.next;
        }
        // return the value of the container at the desired index
        return current.value;
    }


    // insertValueAtIndex method
    public void insertValueAtIndex(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (size == 0 || index == size) {
            add(value);
            return;
        }
        Container newCont = new Container();
        newCont.value = value;
        if (index == 0) { // insert at the start of a non-empty list
            newCont.next = start;
            start = newCont;
            size++;
            return;
        }
        Container before = start;
        for (int i = 0; i < index - 1; i++) { // find previous item, just before index
            before = before.next;
        }
        newCont.next = before.next;
        before.next = newCont;
        size++;
    }

    // replaceValueAtIndex method
    public void replaceValueAtIndex(T value, int index) {
        // check if index is valid, if not throw exception
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        // if valid, begin at start of LinkedList and follow next pointers until moved 'index' steps
        Container current = start;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        // replace the value of the Container at the desired index with the given value
        current.value = value;
    }

    // deleteByIndex method
    public void deleteByIndex(int index) {
        // check if index is valid, if not throw exception
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        // if valid, begin at start of LinkedList and follow next pointers until moved 'index' steps
        if (index == 0) { // special case: removing the first item
            start = start.next;
            if (start == null) {
                end = null;
            }
        } else {
            // remove the Container at the desired index by updating the next pointer of the Container before it to skip the Container at the index
            Container current = start;
            for (int i = 0; i < index - 1; i++) { // find previous item, just before index
                current = current.next;
            }
            current.next = current.next.next; // next.next - means the one after being deleted (follow next twice)
            if (current.next == null) {
                end = current;
            }
        }
        // decrement the size of the LinkedList by 1 since an element was deleted
        size--;
    }

    public boolean deleteByValue(T value) { // delete first value found
        int count = 0;
        Container current = start;
        while (current != null) {
            if (current.value.equals(value)) {
                deleteByIndex(count);
                return true;
            }
            current = current.next;
            count++;
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() { // comma-separated values list similar to: [5,8,11]
        StringBuilder sb = new StringBuilder("[");
        Container current = start;
        // while (current != null) {
        for (int i = 0; i < size; i++) {
            sb.append(i == 0 ? "" : ",");
            sb.append(current.value); // .toString() call happens here
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public T[] toArray(T[] template) { // could be used for Unit testing
        // T[] result = new T[size]; // this won't compile
        // T[] result = (T[]) new Object[size]; // will compiler but then crash at runtime
        // T[] result = (T[]) Array.newInstance(template.getClass(), size); // fails on storing value into the array
        T[] result = (T[])java.lang.reflect.Array.newInstance(template.getClass().getComponentType(), size);
        Container current = start;
        for (int i = 0; i < size; i++) {
            result[i] = current.value;
            current = current.next;
        }
        return result;
    }





}
