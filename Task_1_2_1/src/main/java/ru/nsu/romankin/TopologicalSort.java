package ru.nsu.romankin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TopologicalSort<T>  {
    private List<Vertex<T>> ans = new ArrayList<>();
    private boolean[] used;
    public List<Vertex<T>> topologicalSort(Graph<T> graph) {
        int verticesCount = graph.getVerticesCount();
        used = new boolean[verticesCount];
        for (int i = 0; i < verticesCount; i++)
            used[i] = false;
        ans.clear();
        for (int i=0; i < verticesCount; ++i)
            if (!used[i])
                dfs(i, graph);
        Collections.reverse(ans);
        return ans;
    }

    private void dfs(int v, Graph<T> graph) {
        used[v] = true;
        int size = graph.getNeighbours(graph.getVertexById(v)).size();
        for (int i = 0; i < size; i++) {
            int to = graph.getVertexId(graph.getNeighbours(graph.getVertexById(v)).get(i));
            if (!used[to])
                dfs (to, graph);
        }
        ans.add(graph.getVertexById(v));
    }
}
