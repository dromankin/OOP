package ru.nsu.romankin.hashtable;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        //comment
        HashTable<String, String> hashTable = new HashTable<>();
        hashTable.put("v1", "value1");
        hashTable.put("v2", "value2");
        hashTable.put("v3", "value3");
        hashTable.put("v4", "value4");
        hashTable.put("v5", "value5");
        HashTable<String, String> anotherHashTable = new HashTable<>();
        anotherHashTable.put("v1", "value1");
        anotherHashTable.put("v2", "value2");
        anotherHashTable.put("v3", "value3");
        anotherHashTable.put("v4", "value4");
        anotherHashTable.put("v5", "value5");
        anotherHashTable.delete("v3");
        Iterator<Node<String, String>> iterator = hashTable.iterator();
        Node<String, String> next;
        while (iterator.hasNext()) {
            next = iterator.next();
            System.out.println(next.getKey() + " " + next.getValue());
        }
        System.out.println(hashTable.toString());
        System.out.println(hashTable.equals(anotherHashTable));
    }
}