package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListArrayTest {

    class Person {

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        String name;
        int age;

        @Override
        public String toString() {
            return String.format("Person(%s:%d)", name, age);
        }
/*
        @Override
        public boolean equals(Object obj) {
            Person other = (Person) obj;
            return (name.equals(other.name) && age == other.age);
        } */
    }

    @Test
    public void testAddToStringToArray() {
        System.out.println("add toString toArray");
        LinkedListArray<String> instance = new LinkedListArray<String>();
        instance.add("Jerry");
        instance.add("Terry");
        instance.add("Barry");
        instance.add("Larry");
        instance.add("Marry");
        assertEquals(5, instance.getSize());
        assertEquals("[Jerry,Terry,Barry,Larry,Marry]", instance.toString());
        String[] result = instance.toArray(new String[0]);
        String[] expectedArray = {"Jerry", "Terry", "Barry", "Larry", "Marry"};
        assertArrayEquals(expectedArray, result);
    }

    @Test
    public void testAddGet() {
        System.out.println("add get");
        LinkedListArray<String> instance = new LinkedListArray<String>();
        instance.add("Jerry");
        instance.add("Terry");
        instance.add("Barry");
        instance.add("Larry");
        instance.add("Marry");
        assertEquals("Jerry", instance.get(0));
        assertEquals("Larry", instance.get(3));
        assertEquals("Marry", instance.get(4));
    }

    @Test
    public void testAddDelete() {
        System.out.println("add delete");
        LinkedListArray<String> instance = new LinkedListArray<String>();
        instance.add("Jerry"); // 0
        instance.add("Terry");
        instance.add("Barry");
        instance.add("Marry"); // 3
        instance.add("Larry");
        instance.add("Garry"); // 5
        instance.deleteByIndex(5);
        assertEquals("[Jerry,Terry,Barry,Marry,Larry]", instance.toString());
        instance.deleteByIndex(3);
        assertEquals("[Jerry,Terry,Barry,Larry]", instance.toString());
        instance.deleteByIndex(0);
        assertEquals("[Terry,Barry,Larry]", instance.toString());
    }

    @Test
    public void testAddDeleteByValue() {
        System.out.println("add delete");
        LinkedListArray<String> instance = new LinkedListArray<String>();
        instance.add("Jerry"); // 0
        instance.add("Terry");
        instance.add("Barry");
        instance.add("Marry"); // 3
        instance.add("Barry");
        instance.add("Terry"); // 5
        instance.deleteByValue("Barry");
        assertEquals("[Jerry,Terry,Marry,Barry,Terry]", instance.toString());
    }

    @Test
    public void testAddDeleteAdd() {
        System.out.println("add delete");
        LinkedListArray<String> instance = new LinkedListArray<String>();
        instance.add("Jerry");
        instance.add("Terry");
        instance.add("Barry"); // 2
        instance.deleteByIndex(2);
        assertEquals("[Jerry,Terry]", instance.toString());
        instance.add("Marry");
        assertEquals("[Jerry,Terry,Marry]", instance.toString());
    }

    @Test
    public void testAddInsert() {
        System.out.println("add insert");
        LinkedListArray<String> instance = new LinkedListArray<String>();
        instance.add("Jerry");
        instance.add("Terry");
        instance.insertValueAtIndex("Barry",0); // insert at the start
        assertEquals("[Barry,Jerry,Terry]", instance.toString());
        instance.insertValueAtIndex("Marry",3); // really an add - insert at the very end
        assertEquals("[Barry,Jerry,Terry,Marry]", instance.toString());
        instance.insertValueAtIndex("Zarry",2); // insert in the middle
        assertEquals("[Barry,Jerry,Zarry,Terry,Marry]", instance.toString());
    }

    @Test
    public void testIndexExceptions() {
        System.out.println("index exceptions");
        LinkedListArray<String> instance = new LinkedListArray<String>();
        instance.add("Jerry"); // 0
        instance.add("Terry");
        instance.add("Barry");
        instance.add("Marry"); // 3
        instance.add("Larry");
        instance.add("Garry"); // 5

        // get
        assertThrows(IndexOutOfBoundsException.class,
                () -> instance.get(-1));
        assertThrows(IndexOutOfBoundsException.class,
                () -> instance.get(6));
        // delete
        assertThrows(IndexOutOfBoundsException.class,
                () -> instance.deleteByIndex(-1));
        assertThrows(IndexOutOfBoundsException.class,
                () -> instance.deleteByIndex(6));
        // insert
        assertThrows(IndexOutOfBoundsException.class,
                () -> instance.insertValueAtIndex("Eva", -1));
        assertThrows(IndexOutOfBoundsException.class,
                () -> instance.insertValueAtIndex("Eva", 7));
    }

}
