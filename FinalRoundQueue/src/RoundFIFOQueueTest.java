import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RoundFIFOQueueTest {

    // testEnqueueDequeue tests that elements are dequeued in the order they are enqueued
    @Test
    public void testEnqueueDequeue() {
        RoundFIFOQueue<Integer> queue = new RoundFIFOQueue<>(5);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        assertEquals((Integer) 1, queue.dequeue());
        assertEquals((Integer) 2, queue.dequeue());
        assertEquals((Integer) 3, queue.dequeue());
    }

    // testEnqueueException tests that an exception is thrown when more elements are enqueued than the maximum size allowed
    @Test(expected = RuntimeException.class)
    public void testEnqueueException() {
        RoundFIFOQueue<Integer> queue = new RoundFIFOQueue<>(2);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3); // this should throw the RuntimeException
    }

    // testDequeueException tests that an exception is thrown when an element is dequeued from an empty queue
    @Test(expected = RuntimeException.class)
    public void testDequeueException() {
        RoundFIFOQueue<Integer> queue = new RoundFIFOQueue<>(2);
        queue.dequeue(); // this should throw the RuntimeException
    }

    // testToArray tests that the toArray method returns an array of elements with most recently enqueued elements first
    @Test
    public void testToArray() {
        RoundFIFOQueue<Integer> queue = new RoundFIFOQueue<>(5);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        Integer[] expected = new Integer[]{5, 4, 3, 2, 1};
        assertArrayEquals(expected, queue.toArray(new Integer[queue.size()]));
    }

    // testToArrayException tests that the IllegalArgumentException("The provided array is smaller than the queue size.")
    // will be thrown when the toArray method is called to make an array smaller than the queue max size
    @Test
    public void testToArrayException() {
        RoundFIFOQueue<Integer> queue = new RoundFIFOQueue<>(5); // max size of queue is 5
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        Integer[] template = new Integer[4]; // 4 is smaller than the queue size (5)

        Exception exception = assertThrows(IllegalArgumentException.class, () -> queue.toArray(template));

        String expectedMessage = "The provided array is smaller than the queue size.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // testCountValues tests that the countValues method correctly counts the occurrences of a given value
    @Test
    public void testCountValues() {
        RoundFIFOQueue<Integer> queue = new RoundFIFOQueue<>(5);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(2);
        queue.enqueue(2);
        assertEquals(3, queue.countValues(2));
        assertEquals(1, queue.countValues(1));
        assertEquals(0, queue.countValues(4));
    }

    // testIterator tests that the iterator correctly traverses the queue in order from the oldest to the most recent element
    @Test
    public void testIterator() {
        RoundFIFOQueue<Integer> queue = new RoundFIFOQueue<>(3);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        Iterator<Integer> it = queue.iterator();
        assertTrue(it.hasNext());
        assertEquals((Integer)1, it.next());
        assertTrue(it.hasNext());
        assertEquals((Integer)2, it.next());
        assertTrue(it.hasNext());
        assertEquals((Integer)3, it.next());
        assertFalse(it.hasNext());
    }

    // testIteratorException tests that the iterator throws an exception when next() is called and there are no more elements/empty queue
    @Test(expected = NoSuchElementException.class)
    public void testIteratorException() {
        RoundFIFOQueue<Integer> queue = new RoundFIFOQueue<>(3);
        Iterator<Integer> it = queue.iterator();
        it.next(); // this should throw the NoSuchElementException
    }
}

