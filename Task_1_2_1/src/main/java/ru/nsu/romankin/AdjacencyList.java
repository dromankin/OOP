package ru.nsu.romankin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdjacencyList<T> implements Graph<T> {

    private HashMap<Vertex<T>, ArrayList<Vertex<T>>> list = new HashMap<>();
    private List<Vertex<T>> vertices = new ArrayList<>();

    @Override
    public void addEdge(Edge<T> edge) {
        if (list.containsKey(edge.getTo()) || list.containsKey(edge.getFrom())) {
            if (!list.get(edge.getFrom()).contains(edge.getTo()) &&
            !list.get(edge.getTo()).contains(edge.getFrom())) {
                list.get(edge.getFrom()).add(edge.getTo());
                list.get(edge.getTo()).add(edge.getFrom());
            }
        }
    }


    @Override
    public void deleteEdge(Edge<T> edge) {
        list.get(edge.getFrom()).remove(edge.getTo());
    }

    @Override
    public void addVertex(Vertex<T> vertex) {
        list.putIfAbsent(vertex, new ArrayList<>());
        vertices.add(vertex);
    }

    @Override
    public void deleteVertex(Vertex<T> vertex) {
        list.remove(vertex);
        for (List<Vertex<T>> neighbours : list.values()) {
            neighbours.remove(vertex);
        }
        vertices.remove(vertex);
    }

    @Override
    public ArrayList<Vertex<T>> getNeighbours(Vertex<T> vertex) {
        return list.getOrDefault(vertex, new ArrayList<>());
    }

    @Override
    public void readFromFile(String filename) {

    }

    @Override
    public int getVerticesCount() {
        return list.size();
    }

    @Override
    public Vertex<T> getVertexById(int id) {
        return vertices.get(id);
    }

    @Override
    public int getVertexId(Vertex<T> vertex) {
        return vertices.indexOf(vertex);
    }
}