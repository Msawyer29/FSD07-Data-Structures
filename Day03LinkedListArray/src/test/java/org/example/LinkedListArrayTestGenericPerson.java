package org.example;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class LinkedListArrayTestGenericPerson {
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

        @Override
        public boolean equals(Object obj) {
            Person other = (Person) obj;
            return (name.equals(other.name) && age == other.age);
        }
    }

    @Test
    public void testAddToPersonToArray() {
        System.out.println("generic: add Person toString toArray");
        LinkedListArray<Person> instance = new LinkedListArray<>();
        instance.add(new Person("Jerry",33));
        instance.add(new Person("Terry",22));
        instance.add(new Person("Barry",11));
        assertEquals("[Person(Jerry:33),Person(Terry:22),Person(Barry:11)]", instance.toString());
        Person[] result = instance.toArray(new Person[0]);
        Person[] expectedArray = {new Person("Jerry",33), new Person("Terry",22), new Person("Barry",11) };
        assertArrayEquals(expectedArray, result);
        assertEquals(true, instance.deleteByValue(new Person("Terry",22)));
        assertEquals(false, instance.deleteByValue(new Person("AAAAATerry",27)));
        assertEquals("[Person(Jerry:33),Person(Barry:11)]", instance.toString());
    }
}
