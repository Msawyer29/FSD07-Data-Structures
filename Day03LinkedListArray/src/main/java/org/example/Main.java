package org.example;

public class Main {
    public static void main(String[] args) {
        LinkedListArrayOfStrings list = new LinkedListArrayOfStrings();

        // Test add method
        list.add("Hello");
        list.add("World");
        list.add("Java");
        list.add("Pluto");


//        // Test get method
//        System.out.println(list.get(0));  // Should print: Hello
//        System.out.println(list.get(1));  // Should print: World

        // Test insertValueAtIndex method
        list.insertValueAtIndex("Saturn", 3);
//        System.out.println(list.get(1));  // Should print: Java

        System.out.println(list); // test StringBuilder in @Override String toString method

//        // Test replaceValueAtIndex method
//        list.replaceValueAtIndex("Mars", 1);
//        System.out.println(list.get(1));  // Should print: Mars
//
//        // Test deleteByIndex method
//        list.deleteByIndex(2);
//        System.out.println(list.get(1));  // Should print: Mars
//
//        // Test deleteByValue method
//        System.out.println(list.get(1));  // Should print: Mars, confirming that "Mars" is there before deletion
//        list.deleteByValue("Mars");
//        System.out.println(list.get(1));  // Should print: "Pluto", if "Mars" was deleted successfully
//
//        // Test getSize method
//        System.out.println(list.getSize());  // Should print: 2
    }
}