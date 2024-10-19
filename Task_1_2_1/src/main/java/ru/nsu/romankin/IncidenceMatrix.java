package ru.nsu.romankin;

import java.util.ArrayList;
import java.util.List;

public class IncidenceMatrix<T> implements Graph<T> {

    private List<List<Integer>> matrix = new ArrayList<>();
    private List<Vertex<T>> vertices = new ArrayList<>();
    private List<Edge<T>> edges = new ArrayList<>();

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

    @Override
    public void deleteEdge(Edge<T> edge) {
        edges.remove(edge);
        int indexFrom = vertices.indexOf(edge.getFrom());
        int indexTo = vertices.indexOf(edge.getTo());
        int edgeIndex = edges.indexOf(edge);
        matrix.get(indexFrom).set(edgeIndex, 0);
        matrix.get(indexTo).set(edgeIndex, 0);
    }

    @Override
    public void addVertex(Vertex<T> vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
        }
        List<Integer> list = new ArrayList<>();
        matrix.add(list);
    }

    @Override
    public void deleteVertex(Vertex<T> vertex) {
        int index = vertices.indexOf(vertex);
        vertices.remove(vertex);
        matrix.remove(index);
        edges.removeIf(edge -> edge.getFrom() == vertex || edge.getTo() == vertex);
    }



    @Override
    public List<Vertex<T>> getNeighbours(Vertex<T> vertex) {
        List<Vertex<T>> res = new ArrayList<>();
        int vertexIndex = vertices.indexOf(vertex);
        for (int i = 0; i < matrix.get(vertexIndex).size(); i++) {
            if (matrix.get(vertexIndex).get(i) == 1 || (matrix.get(vertexIndex).get(i) == -1)) {
                for (int j = 0; j < vertices.size(); j++) {
                    if ((matrix.get(j).get(i) == 1 || matrix.get(j).get(i) == -1) && j!=vertexIndex) {
                        res.add(vertices.get(j));
                    }
                }
            }
        }
        return res;
    }

    @Override
    public int getVerticesCount() {
        return vertices.size();
    }


    @Override
    public List<Vertex<T>> getAllVertices() {
        return vertices;
    }

    @Override
    public List<Edge<T>> getAllEdges() {
        return edges;
    }
}
