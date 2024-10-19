package ru.nsu.romankin;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyMatrix<T> implements Graph<T> {

    private List<List<Boolean>> matrix = new ArrayList<>();
    private List<Vertex<T>> vertices = new ArrayList<>();
    private List<Edge<T>> edges = new ArrayList<>();
    @Override
    public void addEdge(Edge<T> edge) {
        int indexFrom = vertices.indexOf(edge.getFrom());
        int indexTo = vertices.indexOf(edge.getTo());
        matrix.get(indexFrom).set(indexTo, true);
    }

    @Override
    public void deleteEdge(Edge<T> edge) {
        int indexFrom = vertices.indexOf(edge.getFrom());
        int indexTo = vertices.indexOf(edge.getTo());
        matrix.get(indexFrom).set(indexTo, false);
    }

    @Override
    public void addVertex(Vertex<T> vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
            int index = vertices.indexOf(vertex);
            for (List<Boolean> temp : matrix) {
                temp.add(false);
            }
            List<Boolean> temp = new ArrayList<>();
            for (int i = 0; i < vertices.size(); i++) {
                temp.add(false);
            }
            matrix.add(temp);
            matrix.get(index).set(index, false);
        }
    }

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
            if (list.get(index) && matrix.indexOf(list) != index ) {
                res.add(vertices.get(matrix.indexOf(list)));
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
