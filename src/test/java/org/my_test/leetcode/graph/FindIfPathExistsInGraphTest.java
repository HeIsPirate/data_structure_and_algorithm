package org.my_test.leetcode.graph;

import org.my_test.algorithms_book.graph.AdjacencyMatrix;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class FindIfPathExistsInGraphTest {
    @Test
    public void test() {
        FindIfPathExistsInGraph graph = new FindIfPathExistsInGraph();
        assert graph.validPath(3, buildEdges("[[[0, 1], [1, 2], [2, 0]]]"), 0, 2);
    }

    @Test
    public void test2() {
        FindIfPathExistsInGraph graph = new FindIfPathExistsInGraph();
        assert !graph.validPath(6, buildEdges("[[0,1],[0,2],[3,5],[5,4],[4,3]]"), 0, 5);
    }

    @Test
    public void test3() {
        FindIfPathExistsInGraph graph = new FindIfPathExistsInGraph();
        assert graph.validPath(5, buildEdges("[[0,4]]"), 0, 4);
    }

    @Test
    public void test4() {
        FindIfPathExistsInGraph graph = new FindIfPathExistsInGraph();
        assert graph.validPath(1, buildEdges("[]"), 0, 0);
    }

    @Test
    public void test5() {
        FindIfPathExistsInGraph graph = new FindIfPathExistsInGraph();
        assert graph.validPath(10, buildEdges("[[4,3],[1,4],[4,8],[1,7],[6,4],[4,2],[7,4],[4,0],[0,9],[5,4]]"), 5, 9);
    }

    @Test
    public void testLargeGraph() {
        String[] graphString = readGraph();
        FindIfPathExistsInGraph graph = new FindIfPathExistsInGraph();
        assert graph.validPath(Integer.parseInt(graphString[0]), buildEdges(graphString[1]), Integer.parseInt(graphString[2]), Integer.parseInt(graphString[3]));
    }

    @SuppressWarnings("ConstantConditions")
    private String[] readGraph() {
        String[] result = new String[4];
        try (InputStream stream = AdjacencyMatrix.class.getResourceAsStream("/FindIfPathExistsInGraphTest.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {

            int index = 0;
            String readLine;
            while ((readLine = reader.readLine()) != null) {
                result[index++] = readLine;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private int[][] buildEdges(String str) {
        String[] split = str.split(",");
        int[][] edges = new int[split.length / 2][];

        for (int i = 0; i < split.length; i++) {
            if (i % 2 == 1) {
                String vertexV = split[i - 1].replaceAll("[\s\\[\\]]", "");
                String vertexW = split[i].replaceAll("[\s\\[\\]]", "");
                edges[i / 2] = new int[]{Integer.parseInt(vertexV), Integer.parseInt(vertexW)};
            }
        }

        return edges;
    }

    @Test
    public void buildEdges() {
        int[][] edges = buildEdges("[[[0, 1], [1, 2], [2, 0]]]");
        System.out.println(Arrays.deepToString(edges));
    }

    @Test
    public void replaceTest() {
        System.out.println("[[[0, 1], [1, 2], [2, 0]]]".replaceAll("[\\[\\]]", ""));
    }
}
