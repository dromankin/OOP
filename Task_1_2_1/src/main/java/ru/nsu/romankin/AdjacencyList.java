package ru.nsu.romankin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * This class describes adjacency list implementation of graph.
 *
 * @param <T> - type of value that vertex stores.
 */
public class AdjacencyList<T> implements Graph<T> {

    private HashMap<Vertex<T>, ArrayList<Vertex<T>>> list = new HashMap<>();
    private List<Vertex<T>> vertices = new ArrayList<>();
    private List<Edge<T>> edges = new ArrayList<>();

    /**
     * Function that adds edge to graph.
     *
     * @param edge - edge.
     */
    @Override
    public void addEdge(Edge<T> edge) {
        if (!edges.contains(edge)) {
            if (list.containsKey(edge.getTo()) && list.containsKey(edge.getFrom())) {
                if (!list.get(edge.getFrom()).contains(edge.getTo())
                        && !list.get(edge.getTo()).contains(edge.getFrom())) {
                    list.get(edge.getFrom()).add(edge.getTo());
                    list.get(edge.getTo()).add(edge.getFrom());

                }

            }
            edges.add(edge);
        }
    }

    /**
     * Function that deletes edge from the graph.
     *
     * @param edge - edge
     */
    @Override
    public void deleteEdge(Edge<T> edge) {
        if (edges.contains(edge)) {
            list.get(edge.getFrom()).remove(edge.getTo());
            edges.remove(edge);
        }
    }

    /**
     * Function that adds vertex to the graph.
     *
     * @param vertex - vertex
     */
    @Override
    public void addVertex(Vertex<T> vertex) {
        list.putIfAbsent(vertex, new ArrayList<>());
        vertices.add(vertex);
    }

    /**
     * Function that deletes vertex from the graph.
     *
     * @param vertex - vertex
     */
    @Override
    public void deleteVertex(Vertex<T> vertex) {
        list.remove(vertex);
        for (List<Vertex<T>> neighbours : list.values()) {
            neighbours.remove(vertex);
        }
        vertices.remove(vertex);
        edges.removeIf(edge -> edge.getTo() == vertex || edge.getFrom() == vertex);
    }

    /**
     * Function that gets list of neighbours of vertex.
     *
     * @param vertex - vertex
     */
    @Override
    public ArrayList<Vertex<T>> getNeighbours(Vertex<T> vertex) {
        return list.getOrDefault(vertex, new ArrayList<>());
    }

    /**
     * Function that gets count of vertices.
     */
    @Override
    public int getVerticesCount() {
        return list.size();
    }

    /**
     * Function that gets list of all vertices.
     */
    @Override
    public List<Vertex<T>> getAllVertices() {
        return vertices;
    }

    /**
     * Function that gets list of all edges.
     */
    @Override
    public List<Edge<T>> getAllEdges() {
        return edges;
    }
}
