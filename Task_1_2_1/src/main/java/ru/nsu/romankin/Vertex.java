package ru.nsu.romankin;

public class Vertex<T> {
    private T vertex;

    Vertex(T vertex) {
        this.vertex = vertex;
    }

    public T getVertex() {
        return vertex;
    }
}
