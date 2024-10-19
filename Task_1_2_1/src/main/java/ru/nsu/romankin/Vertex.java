package ru.nsu.romankin;

/**
 * This class describes a vertex of graph.
 *
 * @param <T> - type of value that vertex stores.
 */
public class Vertex<T> {
    private T vertex;

    /**
     * Class constructor.
     *
     * @param vertex - value of type T.
     */
    public Vertex(T vertex) {
        this.vertex = vertex;
    }

    /**
     * Returns value of vertex.
     */
    public T getVertex() {
        return vertex;
    }
}
