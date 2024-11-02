package ru.nsu.romankin.hashtable;

/**
 * This class describes a node - "key-value" pair.
 *
 * @param <K> - type of key
 *
 * @param <V> - type of value
 */

public class Node<K, V> {
    private final K key;
    private V value;

    /**
     * Class constructor.
     */
    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * returns a key.
     */
    public K getKey() {
        return key;
    }

    /**
     * returns a value.
     */
    public V getValue() {
        return value;
    }

    /**
     * sets new value.
     */
    public void setValue(V value) {
        this.value = value;
    }
}
