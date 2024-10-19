package ru.nsu.romankin;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Interface that implements basic graph operations.
 * @param <T>
 */
public interface Graph<T> {
    void addVertex(Vertex<T> vertex);
    void addEdge(Edge<T> edge);
    void deleteVertex(Vertex<T> vertex);
    void deleteEdge(Edge<T> edge);
    List<Vertex<T>> getNeighbours(Vertex<T> vertex);
    default void readFromFile(String filename, String type) throws FileNotFoundException {

        File file = new File(filename);
        Scanner scanner = new Scanner(file);
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
        while(scanner.hasNextLine()) {
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
                           addVertex(new Vertex<T>((T)Integer.valueOf(string)));
                       } else {
                           addVertex(new Vertex<T>((T)(string)));
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
    int getVerticesCount();
    List<Vertex<T>> getAllVertices();
    List<Edge<T>> getAllEdges();
}
