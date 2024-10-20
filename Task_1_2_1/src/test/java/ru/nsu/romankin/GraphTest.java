package ru.nsu.romankin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

class GraphTest {

    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.class)
    void addVertexTest(Graph<String> graph) {
        Vertex<String> v1 = new Vertex<>("v1");
        graph.addVertex(v1);
        List<Vertex<String>> list = graph.getAllVertices();
        assertEquals("v1", list.get(list.size() - 1).getVertex());
    }

    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.class)
    void deleteVertexTest(Graph<String> graph) {
        Vertex<String> v1 = new Vertex<>("v1");
        Vertex<String> v2 = new Vertex<>("v2");
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.deleteVertex(v2);
        List<Vertex<String>> list = graph.getAllVertices();
        assertNotEquals("v2", list.get(list.size() - 1).getVertex());
    }

    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.class)
    void addEdgeTest(Graph<String> graph) {
        Vertex<String> v1 = new Vertex<>("v1");
        Vertex<String> v2 = new Vertex<>("v2");
        graph.addVertex(v1);
        graph.addVertex(v2);
        Edge<String> e1 = new Edge<>(v1, v2);
        Edge<String> e2 = new Edge<>(v2, v1);
        graph.addEdge(e1);
        graph.addEdge(e1);
        graph.addEdge(e2);
        graph.addEdge(e2);
        graph.addEdge(e2);
        List<Vertex<String>> verticesList = graph.getAllVertices();
        List<Edge<String>> edgesList = graph.getAllEdges();
        assertEquals("v2", edgesList.get(1).getFrom().getVertex());
    }

    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.class)
    void deleteEdgeTest(Graph<String> graph) {
        Vertex<String> v1 = new Vertex<>("v1");
        Vertex<String> v2 = new Vertex<>("v2");
        graph.addVertex(v1);
        graph.addVertex(v2);
        Edge<String> e1 = new Edge<>(v1, v2);
        Edge<String> e2 = new Edge<>(v2, v1);
        graph.addEdge(e1);
        graph.addEdge(e2);
        graph.deleteEdge(e2);
        graph.deleteEdge(e2);
        graph.deleteEdge(e2);
        List<Vertex<String>> verticesList = graph.getAllVertices();
        List<Edge<String>> edgesList = graph.getAllEdges();
        assertEquals("v1", edgesList.get(edgesList.size() - 1).getFrom().getVertex());
    }

    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.class)
    void getNeighboursTest(Graph<String> graph) {
        Vertex<String> v1 = new Vertex<>("v1");
        Vertex<String> v2 = new Vertex<>("v2");
        Vertex<String> v3 = new Vertex<>("v3");
        Vertex<String> v4 = new Vertex<>("v4");
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        Edge<String> e1 = new Edge<>(v1, v2);
        Edge<String> e2 = new Edge<>(v1, v3);
        Edge<String> e3 = new Edge<>(v2, v3);
        graph.addEdge(e1);
        graph.addEdge(e2);
        graph.addEdge(e3);
        List<Vertex<String>> verticesList = graph.getAllVertices();
        List<Edge<String>> edgesList = graph.getAllEdges();
        List<Vertex<String>> v1Neighbours = graph.getNeighbours(v1);
        List<Vertex<String>> v4Neighbours = graph.getNeighbours(v4);
        assertTrue(v1Neighbours.contains(v2) && v1Neighbours.contains(v3));
        assertTrue(v4Neighbours.isEmpty());
    }

    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.class)
    void getVerticesCountTest(Graph<String> graph) {
        Vertex<String> v1 = new Vertex<>("v1");
        Vertex<String> v2 = new Vertex<>("v2");
        Vertex<String> v3 = new Vertex<>("v3");
        Vertex<String> v4 = new Vertex<>("v4");
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        Edge<String> e1 = new Edge<>(v1, v2);
        Edge<String> e2 = new Edge<>(v1, v3);
        Edge<String> e3 = new Edge<>(v2, v3);
        graph.addEdge(e1);
        graph.addEdge(e2);
        graph.addEdge(e3);
        List<Vertex<String>> verticesList = graph.getAllVertices();
        List<Edge<String>> edgesList = graph.getAllEdges();
        assertEquals(graph.getVerticesCount(), 4);
        graph.deleteVertex(v4);
        graph.deleteVertex(v4);
        graph.deleteVertex(v3);
        assertEquals(graph.getVerticesCount(), 2);

    }

    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.class)
    void getAllVerticesAndEdgesTest(Graph<String> graph) {
        Vertex<String> v1 = new Vertex<>("v1");
        Vertex<String> v2 = new Vertex<>("v2");
        Vertex<String> v3 = new Vertex<>("v3");
        Vertex<String> v4 = new Vertex<>("v4");
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        Edge<String> e1 = new Edge<>(v1, v2);
        Edge<String> e2 = new Edge<>(v1, v3);
        Edge<String> e3 = new Edge<>(v2, v3);
        graph.addEdge(e1);
        graph.addEdge(e2);
        graph.addEdge(e3);
        List<Vertex<String>> verticesList = graph.getAllVertices();
        List<Edge<String>> edgesList = graph.getAllEdges();
        assertEquals(verticesList.get(3), v4);
        assertEquals(edgesList.get(edgesList.size() - 1), e3);
        graph.deleteVertex(v4);
        graph.deleteVertex(v4);
        graph.deleteVertex(v3);
        assertEquals(verticesList.get(verticesList.size() - 1), v2);
        assertEquals(edgesList.get(edgesList.size() - 1), e1);

    }

    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.class)
    void readFromFileTest(Graph<String> graph) throws FileNotFoundException {
        graph.readFromFile("graph.txt", "string");
        List<Vertex<String>> verticesList = graph.getAllVertices();
        List<Edge<String>> edgesList = graph.getAllEdges();
        assertFalse(verticesList.isEmpty() && edgesList.isEmpty());
        assertTrue(!graph.getNeighbours(verticesList.get(0)).isEmpty()
                        && graph.getNeighbours(verticesList.get(0)).contains(verticesList.get(1))
                        && graph.getNeighbours(verticesList.get(0)).contains(verticesList.get(2)));

    }

    @ParameterizedTest
    @ArgumentsSource(NewTestArgumentsProvider.class)
    void readIntegerFromFileTest(Graph<Integer> graph) throws FileNotFoundException {
        graph.readFromFile("graphTest.txt", "integer");
        List<Vertex<Integer>> verticesList = graph.getAllVertices();
        List<Edge<Integer>> edgesList = graph.getAllEdges();
        assertFalse(verticesList.isEmpty() && edgesList.isEmpty());
        assertTrue(!graph.getNeighbours(verticesList.get(0)).isEmpty()
                && graph.getNeighbours(verticesList.get(0)).contains(verticesList.get(1))
                && graph.getNeighbours(verticesList.get(0)).contains(verticesList.get(2)));

    }

    static class TestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(new AdjacencyList<String>()),
                    Arguments.of(new AdjacencyMatrix<String>()),
                    Arguments.of(new IncidenceMatrix<String>())
            );
        }
    }

    static class NewTestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(new AdjacencyList<Integer>()),
                    Arguments.of(new AdjacencyMatrix<Integer>()),
                    Arguments.of(new IncidenceMatrix<Integer>())
            );
        }
    }
}