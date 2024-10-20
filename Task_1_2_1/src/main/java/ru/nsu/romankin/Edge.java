package ru.nsu.romankin;


/**
 * This class describes edge of graph.
 *
 * @param <T> - type of value that stores in vertices connected by edge.
 */
public class Edge<T> {

    private Vertex<T> from;
    private Vertex<T> to;


    /**
     * Class constructor.
     *
     * @param from - vertex where edge starts.
     *
     * @param to - vertex where edge ends.
     */

    public Edge(Vertex<T> from, Vertex<T> to) {
        this.from = from;
        this.to = to;
    }

    /**
     * Gets vertex where edge starts.
     */
    public Vertex<T> getFrom() {
        return from;
    }

    /**
     * Gets vertex where edge ends.
     */
    public Vertex<T> getTo() {
        return to;
    }

}
