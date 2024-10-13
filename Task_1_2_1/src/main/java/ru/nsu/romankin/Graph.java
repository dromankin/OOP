package ru.nsu.romankin;

import java.util.ArrayList;
import java.util.List;

public interface Graph<T> {
    void addVertex(Vertex<T> vertex);
    void addEdge(Edge<T> edge);
    void deleteVertex(Vertex<T> vertex);
    void deleteEdge(Edge<T> edge);
    List<Vertex<T>> getNeighbours(Vertex<T> vertex);
    void readFromFile(String filename);
    int getVerticesCount();
    Vertex<T> getVertexById(int id);
    int getVertexId(Vertex<T> vertex);
}
