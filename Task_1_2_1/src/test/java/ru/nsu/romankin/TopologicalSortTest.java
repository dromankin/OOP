package ru.nsu.romankin;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

class TopologicalSortTest {

    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.class)
    void topologicalSortTest(Graph<String> graph) {
        Vertex<String> v1 = new Vertex<>("v1");
        Vertex<String> v2 = new Vertex<>("v2");
        Vertex<String> v3 = new Vertex<>("v3");
        Vertex<String> v4 = new Vertex<>("v4");
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        Edge<String> e1 = new Edge<>(v1, v4);
        Edge<String> e2 = new Edge<>(v4, v2);
        Edge<String> e3 = new Edge<>(v4, v3);
        Edge<String> e4 = new Edge<>(v3, v2);
        graph.addEdge(e1);
        graph.addEdge(e2);
        graph.addEdge(e3);
        graph.addEdge(e4);
        TopologicalSort<String> sort = new TopologicalSort<>();
        List<Vertex<String>> list = sort.topologicalSort(graph);
        assertEquals(list.get(0).getVertex(), "v1");
        assertTrue(list.get(1).getVertex().equals("v4") || list.get(1).getVertex().equals("v2"));
        assertTrue(list.get(2).getVertex().equals("v4") || list.get(2).getVertex().equals("v2"));
        assertEquals(list.get(3).getVertex(), "v3");
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
}