package org.example;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;

public class PriorityStackTest {

    // to make sure generic behaviour is well implemented we use this custom class
    class Person {

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        private String name;
        private int age;

        @Override
        public String toString() {
            return name + "^" + age;
        }

        @Override
        public boolean equals(Object obj) {
            Person other = (Person) obj;
            return name.equals(other.name) && (age == other.age);
        }

    }

    // For full marks: write at least 3 additional tests, as described below.

    // Midterm TODO: To obtain full marks for the task you must write at least
    // one test for every case where code will throw NoSuchElementException (3 cases, min 3 tests)
    // methods: pop() popPriority() removeValue()

    // Mac additional test 1
    // trying to pop on an empty stack should result in the NoSuchElementException being thrown
    @Test(expected = NoSuchElementException.class)
    public void popOnEmptyStackThrowException() {
        PriorityStack<String> instance = new PriorityStack<>();
        instance.pop();
    }

    // Mac additional test 2
    // trying to pop priority on an empty stack should result in the NoSuchElementException being thrown
    @Test(expected = NoSuchElementException.class)
    public void popPriorityOnEmptyStackThrowException() {
        PriorityStack<String> instance = new PriorityStack<>();
        instance.popPriority();
    }

    // Mac additional test 3
    // when trying to remove a value that doesn't exist a NoSuchElementException should be thrown
    @Test(expected = NoSuchElementException.class)
    public void removeNonExistentValueThrowException() {
        PriorityStack<String> instance = new PriorityStack<>();
        instance.push("Jerry");
        instance.push("Terry");
        instance.push("Larry");
        instance.push("Barry");
        instance.removeValue("Mary");
    }


    // NOTE: These tests are accurate to the best of teacher's knowledge.
    // However, if something doesn't seem right with a test you must consult the teacher
    // *during* the midterm, not after.

    @Test
    public void PushTest1() {
        PriorityStack<String> instance = new PriorityStack<>();
        instance.push("Jerry");
        instance.push("Terry");
        instance.push("Larry", true);
        instance.push("Barry");
        assertEquals(4, instance.getSize());
        assertEquals("[Barry:N,Larry:P,Terry:N,Jerry:N]", instance.toString());
    }


    @Test
    public void PushPopTest1() {
        PriorityStack<String> instance = new PriorityStack<>();
        instance.push("Jerry");
        instance.push("Terry");
        instance.push("Larry", true);
        instance.push("Barry");
        assertEquals(4, instance.getSize());
        String res1 = instance.pop();
        assertEquals("Barry", res1);
        String res2 = instance.pop();
        assertEquals("Larry", res2);
        assertEquals("[Terry:N,Jerry:N]", instance.toString());
    }

    @Test
    public void PushPopPushTest1() {
        PriorityStack<String> instance = new PriorityStack<>();
        instance.push("Jerry");
        instance.push("Terry");
        instance.push("Larry", true);
        instance.push("Barry");
        assertEquals(4, instance.getSize());
        String res1 = instance.pop();
        assertEquals("Barry", res1);
        String res2 = instance.pop();
        assertEquals("Larry", res2);
        assertEquals(2, instance.getSize());
        assertEquals("[Terry:N,Jerry:N]", instance.toString());
        instance.push("Eva", true);
        instance.push("Martha");
        instance.push("Ruth");
        assertEquals(5, instance.getSize());
        assertEquals("[Ruth:N,Martha:N,Eva:P,Terry:N,Jerry:N]", instance.toString());
    }

    @Test
    public void PopPriorityTest1() {
        PriorityStack<String> instance = new PriorityStack<>();
        instance.push("Jerry");
        instance.push("Terry");
        instance.push("Larry", true);
        instance.push("Barry");
        assertEquals(4, instance.getSize());
        String res1 = instance.popPriority();
        assertEquals("Larry", res1);
        assertEquals(3, instance.getSize());
        String res2 = instance.popPriority();
        assertEquals("Barry", res2);
        assertEquals(2, instance.getSize());
        assertEquals("[Terry:N,Jerry:N]", instance.toString());
    }

    @Test
    public void PopPriorityTest2FromTop() {
        PriorityStack<String> instance = new PriorityStack<>();
        instance.push("Jerry");
        instance.push("Terry");
        instance.push("Barry");
        instance.push("Larry", true);
        assertEquals(4, instance.getSize());
        String res1 = instance.popPriority();
        assertEquals("[Barry:N,Terry:N,Jerry:N]", instance.toString());
    }


    @Test
    public void HasValueTest1() {
        PriorityStack<String> instance = new PriorityStack<>();
        instance.push("Jerry");
        instance.push("Terry");
        instance.push("Larry", true);
        instance.push("Barry");
        assertEquals(4, instance.getSize());
        assertEquals("[Barry:N,Larry:P,Terry:N,Jerry:N]", instance.toString());
        int res1 = instance.hasValue("Eva");
        assertEquals(-1, res1);
        int res2 = instance.hasValue("Terry");
        assertEquals(2, res2);
    }

    @Test
    public void HasValueRemoveValueTest1() {
        PriorityStack<String> instance = new PriorityStack<>();
        instance.push("Jerry");
        instance.push("Terry");
        instance.push("Larry", true);
        instance.push("Barry");
        assertEquals(4, instance.getSize());
        assertEquals("[Barry:N,Larry:P,Terry:N,Jerry:N]", instance.toString());
        int res1 = instance.hasValue("Eva");
        assertEquals(-1, res1);
        int res2 = instance.hasValue("Terry");
        assertEquals(2, res2);
        String res3 = instance.removeValue("Jerry");
        assertEquals("Jerry", res3);
        String res4 = instance.removeValue("Barry");
        assertEquals("Barry", res4);
        String res5 = instance.pop();
        assertEquals("Larry", res5);
        String res6 = instance.pop();
        assertEquals("Terry", res6);
        assertEquals("[]", instance.toString());
        assertEquals(0, instance.getSize());
    }

    @Test
    public void ReorderByPriorityTest1() {
        PriorityStack<String> instance = new PriorityStack<>();
        instance.push("Jerry", false);
        instance.push("Terry");
        instance.push("Larry", true);
        instance.push("Barry");
        instance.push("Eva", true);
        instance.push("Martha");
        instance.push("Ruth");
        assertEquals(7, instance.getSize());
        instance.reorderByPriority();
        assertEquals("[Eva:P,Larry:P,Ruth:N,Martha:N,Barry:N,Terry:N,Jerry:N]", instance.toString());
        String res1 = instance.pop();
        assertEquals("Eva", res1);
        String res2 = instance.pop();
        assertEquals("Larry", res2);
        String res3 = instance.pop();
        assertEquals("Ruth", res3);
        assertEquals(4, instance.getSize());
        assertEquals("[Martha:N,Barry:N,Terry:N,Jerry:N]", instance.toString());
    }


    @Test
    public void CustomEqualsTest1() {
        PriorityStack<Person> instance = new PriorityStack<>();
        instance.push(new Person("Jerry", 33));
        Person p1 = new Person("Maria", 22);
        instance.push(p1, true);
        instance.push(new Person("Tom", 44));
        instance.push(new Person("Eva", 55));
        assertEquals("[Eva^55:N,Tom^44:N,Maria^22:P,Jerry^33:N]", instance.toString());
        int res1 = instance.hasValue(new Person("Tom", 44));
        assertEquals(1, res1);
        Person res2 = instance.removeValue(new Person("Maria", 22));
        assertEquals(p1, res2); // this checks if p1.equals(res2) return true
        assertEquals(true, p1 == res2); // must return exactly the object that was originally pushed
    }


    // REMINDER: If you implemented toArray() but not toArrayReversed() then
    // you must write your own unit test for that method or modify this one below

    // I implemented toArrayReversed() and modified the test below slightly
        @Test
        public void ReverseTest1() {
            PriorityStack<String> instance = new PriorityStack<>();
            instance.push("Jerry");
            instance.push("Terry");
            instance.push("Larry");
            instance.push("Barry");
            String[] array = new String[4]; // mac added this line to remove an error on the line below
            Object [] result = instance.toArrayReversed(array); // mac modified "()" to "(array)"
            Object [] expected = {"Jerry", "Terry", "Larry", "Barry" };
            Assert.assertArrayEquals(expected, result);
        }

}
