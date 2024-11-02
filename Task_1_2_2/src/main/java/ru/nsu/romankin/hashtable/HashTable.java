package ru.nsu.romankin.hashtable;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Objects;

/**
 * This class implements hashtable with basic functions and iterator.
 *
 * @param <K> - type of key
 *
 * @param <V> - type of value
 */
public class HashTable<K, V> implements Iterable<Node<K, V>> {
    private ArrayList<ArrayList<Node<K, V>>> table;
    private int nodeCount;
    private int capacity;
    static final int DEFAULT_CAPACITY = 2;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private int modCount;

    /**
     * returns size of hashtable(number of "key-value" pairs).
     */
    public int getSize() {
        return nodeCount;
    }


    /**
     * returns iterator.
     */
    public Iterator<Node<K, V>> iterator() {
        return new HashTableIterator<>();
    }

    /**
     * Class constructor.
     */
    public HashTable() {
        table = new ArrayList<>(DEFAULT_CAPACITY);
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            table.add(new ArrayList<>());
        }
        nodeCount = 0;
        capacity = DEFAULT_CAPACITY;
    }


    /**
     * Puts a "key-value" pair in hashtable.
     *
     * @param key - key
     *
     * @param value - value
     */
    public void put(K key, V value) {

        if ((float) nodeCount / capacity >= DEFAULT_LOAD_FACTOR) {
            resize();
        }
        int hash = Objects.hashCode(key) % capacity;
        for (Node<K, V> node : table.get(hash)) {
            if (node.getKey().equals(key)) {
                node.setValue(value);
                return;
            }
        }
        table.get(hash).add(new Node<>(key, value));
        nodeCount++;
        modCount++;
    }

    /**
     * Update a "key-value" pair in hashtable.
     *
     * @param key - key
     *
     * @param value - value
     */
    public void update(K key, V value) {
        int hash = Objects.hashCode(key) % capacity;
        for (Node<K, V> node : table.get(hash)) {
            if (node.getKey().equals(key)) {
                node.setValue(value);
            }
        }
    }

    /**
     * Returns a value by key.
     *
     * @param key - key
     */
    public V getValue(Object key) {
        int hash = Objects.hashCode(key) % table.size();
        for (Node<K, V> node : table.get(hash)) {
            if (node.getKey().equals(key)) {
                return node.getValue();
            }
        }
        return null;
    }

    private void resize() {
        capacity *= 2;
        ArrayList<ArrayList<Node<K, V>>> newTable = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            newTable.add(new ArrayList<>());
        }
        for (ArrayList<Node<K, V>> list : table) {
            for (Node<K, V> node : list) {
                int hash = Objects.hashCode(node.getKey()) % newTable.size();
                newTable.get(hash).add(node);
            }
        }
        table = newTable;
    }

    /**
     * Deletes a "key-value" pair from hashtable.
     *
     * @param key - key
     */
    public void delete(K key) {
        int hash = Objects.hashCode(key) % capacity;
        for (Node<K, V> node : table.get(hash)) {
            if (node.getKey().equals(key)) {
                table.get(hash).remove(node);
                nodeCount--;
                modCount++;
                return;
            }
        }
    }

    /**
     * Checks for existing of value by key.
     *
     * @param key - key
     */
    public boolean containsKey(Object key) {

        int hash = Objects.hashCode(key) % capacity;
        for (Node<K, V> node : table.get(hash)) {
            if (node.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Equals method override.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof HashTable)) {
            return false;
        }
        HashTable<?, ?> compareTable = (HashTable<?, ?>) obj;
        if (compareTable.getSize() != nodeCount) {
            return false;
        }
        for (ArrayList<Node<K, V>> list : table) {
            for (Node<?, ?> node : list) {

                if (!compareTable.containsKey(node.getKey())) {
                    return false;
                } else {
                    if (!compareTable.getValue(node.getKey()).equals(node.getValue())) {
                        return false;
                    }
                }
            }

        }
        return true;
    }

    private class HashTableIterator<K, V> implements Iterator<Node<K, V>> {
        private int expectedModCount;
        private int listIndex;
        private int nodeIndex;

        public HashTableIterator() {
            expectedModCount = modCount;
            listIndex = 0;
            nodeIndex = 0;
        }

        /**
         * Checks if there is a next node in hashtable.
         */
        @Override
        public boolean hasNext() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            for (int i = listIndex; i < table.size(); i++) {
                if (!table.get(i).isEmpty()) {
                    return true;
                }
            }
            return false;
        }

        /**
         * returns a next node in hashtable.
         */
        @Override
        public Node<K, V> next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            for (int i = listIndex; i < table.size(); i++) {
                for (int j = nodeIndex; j < table.get(i).size(); j++) {
                    if (table.get(i).get(j) != null) {
                        if (j == table.get(i).size() - 1) {
                            listIndex = i + 1;
                            nodeIndex = 0;
                            return (Node<K, V>) table.get(i).get(j);
                        } else {
                            nodeIndex = j + 1;
                            return (Node<K, V>) table.get(i).get(j);
                        }
                    }
                }
            }
            return null;
        }
    }

    /**
     * returns hashtable as a String.
     */
    public String toString() {
        StringBuilder string = new StringBuilder("");
        String res = "";
        res += "[";
        string.append("[");
        int count = 0;
        for (ArrayList<Node<K, V>> list : table) {
            for (Node<K, V> node : list) {
                string.append(node.getKey());
                string.append(" = ");
                string.append(node.getValue());
                count++;
                if (count < nodeCount) {
                    string.append("; ");
                }

            }
        }
        string.append("]");
        res = string.toString();
        return res;
    }
}
