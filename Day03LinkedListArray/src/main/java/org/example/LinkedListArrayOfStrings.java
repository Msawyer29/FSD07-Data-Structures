package org.example;

public class LinkedListArrayOfStrings {
    private class Container {
        Container next;
        String value;
    }

    private Container start, end;
    private int size;


    // Add method
    public void add(String value) {
        // create a new container object, set its value field to the passed value
        Container newContainer = new Container();
        newContainer.value = value;

        // check if the start of the LinkedList is null
        // if it is, then it is empty, so set start and end to the new container object
        if (start == null) {
            start = newContainer;
            end = newContainer;
        } else { // if start is not null, there are elements in the LinkedList, so add a new container at the end of the LinkedList
            end.next = newContainer; // add the new Container at the end of the LinkedList by setting the next field of the current end to the new Container
            end = newContainer; // update end to point to the new Container.
        }
        size++; // increment the size
    }


    // Get method
    public String get(int index) {
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


    // insert ValueAtIndex method
    public void insertValueAtIndex(String value, int index) {
        // check if passed 'index' is valid
        //  If it's less than 0 or greater than the size of the LinkedList, throw an IndexOutOfBoundsException
        if (index < 0 || index > size) { // index ">" size NOT ">=" to allow index to go beyond current list
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        // create a new Container and set its value to the passed value
        Container newContainer = new Container();
        newContainer.value = value;

        // If the index is 0, insert at the start of the LinkedList.
        // set the next of the new Container to the current start and then update start to point to the new Container
        if (index == 0) {
            newContainer.next = start;
            start = newContainer;
        } else {
            // index is not 0, start from the beginning of the LinkedList and keep following the next pointers until the Container before the desired index
            Container current = start;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            // set the next of the newContainer to the next of the currentContainer
            // and set the next of the currentContainer to the newContainer
            newContainer.next = current.next;
            current.next = newContainer;
        }

        // If the next of the new Container is null, it means index was inserted at the end of the LinkedList.
        // update end to point to the newContainer
        if (newContainer.next == null) {
            end = newContainer;
        }

        // increment the size of the LinkedList by 1 since a new element was added
        size++;
    }


    // replaceValueAtIndex method
    public void replaceValueAtIndex(String value, int index) {
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
        if (index == 0) { // special case: removing the first line
            start = start.next;
            if (start == null) {
                end = null;
            }
        } else {
            // remove the Container at the desired index by updating the next pointer of the Container before it to skip the Container at the index
            Container current = start;
            for (int i = 0; i < index - 1; i++) {
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


    // deleteByValue method
    public boolean deleteByValue(String value) {
        // check if the LinkedList is empty, if it is, return false
        if (size == 0) {
            return false;
        }

        // check if the value of the start Container is equal to the given value
        // if it is, remove the start Container
        // if it's not, start from the beginning of the LinkedList and keep following the next pointers until a Container with a value equal to the given value is found
        // or reach the end of the LinkedList
        if (start.value.equals(value)) {
            start = start.next;
            if (start == null) {
                end = null;
            }
            size--;
            return true;
        }

        // if a Container w a value = to the given value is found,
        // remove it by updating the next pointer of the Container before it to skip the Container with the given value,
        // this effectively removes the Container from our LinkedList
        Container current = start;
        while (current.next != null && !current.next.value.equals(value)) {
            current = current.next;
        }

        //  if the next of the Container stopped at is null, it means the end of the LinkedList was reached without finding the value
        //  return false
        if (current.next != null) {
            current.next = current.next.next;
            if (current.next == null) {
                end = current;
            }
            size--;
            return true;
        }
        return false;
    }


    // getSize method
    public int getSize() {
        return size;
    }


    @Override
    public String toString() { // comma-separated values list similar to: [5, 8, 11]
        StringBuilder sb = new StringBuilder("[");
        Container current = start;
        // while (current != null) {
        for (int i = 0; i < size; i++) {
            sb.append(i == 0 ? "" : ",");
            sb.append(current.value);
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public String[] toArray() { // could be used for Unit testing
        String[] result = new String[size];
        Container current = start;
        for (int i = 0; i < size; i++){
            result[i] = current.value;
            current = current.next;
        }
        return result;
    }

}

