package org.my_test.algorithms_book.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>邻接表</p>
 */
public class AdjacencyList<T> extends AbstractGraph<T> {
    /**
     * `相邻顶点列表`数组
     */
    private final List<Integer>[] adjacencyVertexArray;

    @SuppressWarnings("unchecked")
    public AdjacencyList(int vertexNumber) {
        super(vertexNumber);
        this.adjacencyVertexArray = new List[vertexNumber];
        for (int i = 0; i < vertexNumber; i++) {
            adjacencyVertexArray[i] = new LinkedList<>();
        }
    }

    @Override
    public List<Integer> adj(int xVertexIndex) {
        return adjacencyVertexArray[xVertexIndex];
    }

    @Override
    public void addEdge(int aVertexIndex, int bVertexIndex) {
        adjacencyVertexArray[aVertexIndex].add(bVertexIndex);
        adjacencyVertexArray[bVertexIndex].add(aVertexIndex);
    }

    @Override
    public String toString() {
        String template = "{%s: [%s]}";
        List<String> vertexList = new LinkedList<>();

        for (int i = 0; i < this.vertexArray.length; i++) {
            T value = this.vertexArray[i];
            List<Integer> adjacencyVertex = this.adj(i);
            if (null == adjacencyVertex || adjacencyVertex.size() == 0) continue;
            String adjacencyVertexString = adjacencyVertex.stream()
                    .map(j -> this.vertexArray[j])
                    .filter(Objects::nonNull)
                    .map(Object::toString).collect(Collectors.joining(","));
            vertexList.add(template.formatted(value, adjacencyVertexString));
        }

        return "{" + vertexList + "}";
    }
}
