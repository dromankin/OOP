package ru.nsu.romankin;

import java.util.ArrayList;
import java.util.List;

/**
 * This class describes adjacency matrix implementation of graph.
 *
 * @param <T> - type of value that vertex stores.
 */
public class AdjacencyMatrix<T> implements Graph<T> {

    private List<List<Boolean>> matrix = new ArrayList<>();
    private List<Vertex<T>> vertices = new ArrayList<>();
    private List<Edge<T>> edges = new ArrayList<>();

    /**
     * Function that adds edge to graph.
     *
     * @param edge - edge.
     */
    @Override
    public void addEdge(Edge<T> edge) {
        int indexFrom = vertices.indexOf(edge.getFrom());
        int indexTo = vertices.indexOf(edge.getTo());
        matrix.get(indexFrom).set(indexTo, true);
    }

    /**
     * Function that deletes edge from the graph.
     *
     * @param edge - edge
     */
    @Override
    public void deleteEdge(Edge<T> edge) {
        int indexFrom = vertices.indexOf(edge.getFrom());
        int indexTo = vertices.indexOf(edge.getTo());
        matrix.get(indexFrom).set(indexTo, false);
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
            for (List<Boolean> temp : matrix) {
                temp.add(false);
            }
            List<Boolean> temp = new ArrayList<>();
            for (int i = 0; i < vertices.size(); i++) {
                temp.add(false);
            }
            int index = vertices.indexOf(vertex);
            matrix.add(temp);
            matrix.get(index).set(index, false);
        }
    }

    /**
     * Function that deletes vertex from the graph.
     *
     * @param vertex - vertex
     */
    @Override
    public void deleteVertex(Vertex<T> vertex) {
        if (vertices.contains(vertex)) {
            int index = vertices.indexOf(vertex);
            vertices.remove(vertex);
            matrix.remove(index);
            for (List<Boolean> list : matrix) {
                list.remove(index);
            }
        }
    }

    /**
     * Function that gets list of neighbours of vertex.
     *
     * @param vertex - vertex
     */
    @Override
    public List<Vertex<T>> getNeighbours(Vertex<T> vertex) {
        int index = vertices.indexOf(vertex);
        List<Vertex<T>> res = new ArrayList<>();
        for (int i = 0; i < matrix.get(index).size(); i++) {
            if (matrix.get(index).get(i)) {
                res.add(vertices.get(i));
            }
        }
        for (List<Boolean> list : matrix) {
            if (list.get(index) && matrix.indexOf(list) != index) {
                res.add(vertices.get(matrix.indexOf(list)));
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
