package ru.nsu.romankin;

import java.util.ArrayList;
import java.util.List;

/**
 * This class describes incidence matrix implementation of graph.
 *
 * @param <T> - type of value that vertex stores.
 */
public class IncidenceMatrix<T> implements Graph<T> {

    private List<List<Integer>> matrix = new ArrayList<>();
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
            edges.add(edge);
        }
        for (List<Integer> vertex : matrix) {
            vertex.add(0);
        }
        int indexFrom = vertices.indexOf(edge.getFrom());
        int indexTo = vertices.indexOf(edge.getTo());
        int edgeIndex = edges.indexOf(edge);
        matrix.get(indexFrom).set(edgeIndex, 1);
        matrix.get(indexTo).set(edgeIndex, -1);
    }

    /**
     * Function that deletes edge from the graph.
     *
     * @param edge - edge
     */
    @Override
    public void deleteEdge(Edge<T> edge) {
        edges.remove(edge);
        int indexFrom = vertices.indexOf(edge.getFrom());
        int indexTo = vertices.indexOf(edge.getTo());
        int edgeIndex = edges.indexOf(edge);
        matrix.get(indexFrom).set(edgeIndex, 0);
        matrix.get(indexTo).set(edgeIndex, 0);
    }

    /**
     * Function that adds vertex to the graph.
     *
     * @param vertex - vertex
     */
    @Override
    public void addVertex(Vertex<T> vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
        }
        List<Integer> list = new ArrayList<>();
        matrix.add(list);
    }

    /**
     * Function that deletes vertex from the graph.
     *
     * @param vertex - vertex
     */
    @Override
    public void deleteVertex(Vertex<T> vertex) {
        int index = vertices.indexOf(vertex);
        vertices.remove(vertex);
        matrix.remove(index);
        edges.removeIf(edge -> edge.getFrom() == vertex || edge.getTo() == vertex);
    }


    /**
     * Function that gets list of neighbours of vertex.
     *
     * @param vertex - vertex
     */
    @Override
    public List<Vertex<T>> getNeighbours(Vertex<T> vertex) {
        int value;
        List<Vertex<T>> res = new ArrayList<>();
        int vertexIndex = vertices.indexOf(vertex);
        for (int i = 0; i < matrix.get(vertexIndex).size(); i++) {
            if (matrix.get(vertexIndex).get(i) == 1 || (matrix.get(vertexIndex).get(i) == -1)) {
                for (int j = 0; j < vertices.size(); j++) {
                    value = matrix.get(j).get(i);
                    if ((value == 1 || value == -1) && j != vertexIndex) {
                        res.add(vertices.get(j));
                    }
                }
            }
        }
        return res;
    }

    /**
     * Function that gets count of vertices.
     */
    @Override
    public int getVerticesCount() {
        return vertices.size();
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
