package ru.nsu.romankin;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
//        Graph<String> graph = new AdjacencyList<>();
//        Vertex<String> v1 = new Vertex<>("v1");
//        Vertex<String> v2 = new Vertex<>("v2");
//        Vertex<String> v3 = new Vertex<>("v3");
//        Vertex<String> v4 = new Vertex<>("v4");
//        graph.addVertex(v1);
//        graph.addVertex(v2);
//        graph.addVertex(v3);
//        graph.addVertex(v4);
//        Edge<String> e1 = new Edge<>(v1,v4);
//        Edge<String> e2 = new Edge<>(v4,v2);
//        Edge<String> e3 = new Edge<>(v4,v3);
//        Edge<String> e4 = new Edge<>(v3,v2);
//        graph.addEdge(e1);
//        graph.addEdge(e2);
//        graph.addEdge(e3);
//        graph.addEdge(e4);
//        //graph.deleteVertex(v1);
//        TopologicalSort<String> sort = new TopologicalSort<>();
//        List<Vertex<String>> list = sort.topologicalSort(graph);
//        List<Vertex<String>> list2 = graph.getNeighbours(v1);
//        for (Vertex<String> i : list) {
//            System.out.printf("%s, %d\n", i.getVertex(), list.indexOf(i)+1);
//        }
        Graph<String> graph2 = new AdjacencyList<>();
        graph2.readFromFile("graph.txt", "string");
        for (Edge<String> edge : graph2.getAllEdges()) {
            System.out.printf("%s %s\n", edge.getFrom().getVertex(), edge.getTo().getVertex());
        }
    }
}