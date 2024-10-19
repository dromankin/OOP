package ru.nsu.romankin;

public class Vertex<T> {
    private T vertex;

    public Vertex(T vertex) {
        this.vertex = vertex;
    }

    public T getVertex() {
        return vertex;
    }
}
