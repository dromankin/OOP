package ru.nsu.romankin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TopologicalSort<T>  {
    //private List<Vertex<T>> ans = new ArrayList<>();
    //private boolean[] used;
    public List<Vertex<T>> topologicalSort(Graph<T> graph) {
        List<Vertex<T>> ans = new ArrayList<>();
        boolean[] used;
        int verticesCount = graph.getVerticesCount();
        used = new boolean[verticesCount];
        for (int i = 0; i < verticesCount; i++)
            used[i] = false;
        ans.clear();
        for (int i=0; i < verticesCount; ++i)
            if (!used[i])
                dfs(i, graph, used, ans);
        Collections.reverse(ans);
        return ans;
    }

    private void dfs(int v, Graph<T> graph, boolean[] used, List<Vertex<T>> ans) {
        used[v] = true;
        int size = graph.getNeighbours(graph.getAllVertices().get(v)).size();
        Vertex<T> vertex;
        for (int i = 0; i < size; i++) {
            vertex = graph.getNeighbours(graph.getAllVertices().get(v)).get(i);
            int to = graph.getAllVertices().indexOf(vertex);
            if (!used[to])
                dfs (to, graph, used, ans);
        }
        ans.add(graph.getAllVertices().get(v));
    }
}
