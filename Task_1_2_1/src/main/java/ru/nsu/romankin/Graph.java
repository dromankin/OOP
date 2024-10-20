package ru.nsu.romankin;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Interface that implements basic graph operations.
 *
 * @param <T> - type of value that vertex stores
 */
public interface Graph<T> {

    void addVertex(Vertex<T> vertex);

    void addEdge(Edge<T> edge);

    void deleteVertex(Vertex<T> vertex);

    void deleteEdge(Edge<T> edge);

    List<Vertex<T>> getNeighbours(Vertex<T> vertex);

    /**
     * That function reads a graph from a file.
     *
     * @param filename - name of file;
     *
     * @param type - type of vertices value;
     *
     * @throws FileNotFoundException - if file was not found.
     */
    default void readFromFile(String filename, String type) throws FileNotFoundException {

        File file = new File(filename);
        try (Scanner scanner = new Scanner(file)) {
            String string;
            int number = 0;
            boolean numberReading;
            boolean verticesReading = true;
            if (type.equals("integer")) {
                numberReading = true;
            }
            if (type.equals("string")) {
                numberReading = false;
            }
            while (scanner.hasNextLine()) {
                string = scanner.nextLine();
                switch (string) {
                    case ("Vertices"):
                        break;
                    case ("Edges"):
                        verticesReading = false;
                        break;
                    default:
                        if (verticesReading) {
                            try {
                                number = Integer.parseInt(string);
                                numberReading = true;
                            } catch (NumberFormatException e) {
                                numberReading = false;
                            }
                            if (numberReading) {
                                addVertex(new Vertex<T>((T) Integer.valueOf(string)));
                            } else {
                                addVertex(new Vertex<T>((T) (string)));
                            }
                        } else {
                            String[] arr = string.split(" ");
                            Vertex<T> vertex1 = getAllVertices().get(Integer.parseInt(arr[0]));
                            Vertex<T> vertex2 = getAllVertices().get(Integer.parseInt(arr[1]));
                            addEdge(new Edge<>(vertex1, vertex2));

                        }
                }
            }
        }
    }

    int getVerticesCount();

    List<Vertex<T>> getAllVertices();

    List<Edge<T>> getAllEdges();
}
