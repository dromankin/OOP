package ru.nsu.romankin.hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import org.junit.jupiter.api.Test;

class HashTableTest {

    @Test
    void getSizeTest() {
        HashTable<String, String> hashTable = new HashTable<>();
        hashTable.put("v1", "value1");
        hashTable.put("v2", "value2");
        hashTable.put("v3", "value3");
        hashTable.put("v4", "value4");
        hashTable.put("v5", "value5");
        hashTable.delete("v5");
        hashTable.delete("v5");
        assertEquals(hashTable.getSize(), 4);
    }

    @Test
    void putTest() {
        HashTable<String, String> hashTable = new HashTable<>();
        hashTable.put("v1", "value1");
        hashTable.put("v2", "value2");
        hashTable.put("v3", "value3");
        hashTable.put("v4", "value4");
        hashTable.put("v5", "value5");
        assertEquals("value4", hashTable.getValue("v4"));
        assertTrue(hashTable.containsKey("v1")
                && hashTable.containsKey("v2")
                && hashTable.containsKey("v3")
                && hashTable.containsKey("v4")
                && hashTable.containsKey("v5")
        );
    }

    @Test
    void updateTest() {
        HashTable<String, String> hashTable = new HashTable<>();
        hashTable.put("v1", "value1");
        hashTable.put("v2", "value2");
        hashTable.put("v3", "value3");
        hashTable.put("v4", "value4");
        hashTable.put("v5", "value5");
        hashTable.update("v2", "anotherValue");
        assertEquals("anotherValue", hashTable.getValue("v2"));
        hashTable.update("v1", "value1");
        assertEquals("value1", hashTable.getValue("v1"));
    }

    @Test
    void getValueTest() {
        HashTable<String, String> hashTable = new HashTable<>();
        hashTable.put("v1", "value1");
        hashTable.put("v2", "value2");
        hashTable.put("v3", "value3");
        hashTable.put("v4", "value4");
        hashTable.put("v5", "value5");
        assertTrue(hashTable.getValue("v1").equals("value1")
                && hashTable.getValue("v2").equals("value2")
                && hashTable.getValue("v3").equals("value3")
                && hashTable.getValue("v4").equals("value4")
                && hashTable.getValue("v5").equals("value5")
        );
    }

    @Test
    void deleteTest() {
        HashTable<String, String> hashTable = new HashTable<>();
        hashTable.put("v1", "value1");
        hashTable.put("v2", "value2");
        hashTable.put("v3", "value3");
        hashTable.put("v4", "value4");
        hashTable.put("v5", "value5");
        hashTable.delete("v1");
        hashTable.delete("v1");
        hashTable.delete("v1");
        hashTable.delete("v6");
        assertEquals(4, hashTable.getSize());
        assertFalse(hashTable.containsKey("v1"));
    }

    @Test
    void containsKeyTest() {
        HashTable<String, String> hashTable = new HashTable<>();
        hashTable.put("v1", "value1");
        hashTable.put("v2", "value2");
        hashTable.put("v3", "value3");
        hashTable.put("v4", "value4");
        hashTable.put("v5", "value5");

        assertTrue(hashTable.containsKey("v1")
                && hashTable.containsKey("v2")
                && hashTable.containsKey("v3")
                && hashTable.containsKey("v4")
                && hashTable.containsKey("v5")
        );
        hashTable.delete("v5");
        assertFalse(hashTable.containsKey("v5"));
    }

    @Test
    void testEquals() {
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
        assertTrue(hashTable.equals(anotherHashTable));
        anotherHashTable.put("v6", "value6");
        assertFalse(hashTable.equals(anotherHashTable));
    }

    @Test
    void testToString() {
        HashTable<String, String> hashTable = new HashTable<>();
        hashTable.put("v1", "value1");
        hashTable.put("v2", "value2");
        hashTable.put("v3", "value3");
        hashTable.put("v4", "value4");
        hashTable.put("v5", "value5");
        String res = hashTable.toString();
        assertEquals("[v1 = value1; v2 = value2; v3 = value3; v4 = value4; v5 = value5]", res);
    }

    @Test
    void iteratorTest() {
        HashTable<String, String> hashTable = new HashTable<>();
        hashTable.put("v1", "value1");
        hashTable.put("v2", "value2");
        hashTable.put("v3", "value3");
        hashTable.put("v4", "value4");
        hashTable.put("v5", "value5");
        Iterator<Node<String, String>> iterator = hashTable.iterator();
        Node<String, String> next;
        ArrayList<Node<String, String>> nodeList = new ArrayList<>();
        int hasNextCount = 0;
        while (iterator.hasNext()) {
            hasNextCount++;
            next = iterator.next();
            nodeList.add(next);
        }
        assertEquals(hasNextCount, 5);
        for (Node<String, String> node : nodeList) {
            assertTrue(hashTable.containsKey(node.getKey())
                && node.getValue().equals(hashTable.getValue(node.getKey())));
        }
    }
}