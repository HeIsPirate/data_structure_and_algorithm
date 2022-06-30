package org.my_test.algorithms_book.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class GraphTestUtil {
    /**
     * 读取 mediumG.txt 图
     */
    @SuppressWarnings("ConstantConditions")
    public static void readMediumGraph(IntConsumer vertexNumConsumer, IntConsumer edgeNumConsumer, BiConsumer<Integer, Integer> edgeConsumer) {
        try (InputStream stream = AdjacencyMatrix.class.getResourceAsStream("/data/graph/mediumG.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(stream));) {

            int vertexNum = Integer.parseInt(reader.readLine());
            int edgeNum = Integer.parseInt(reader.readLine());

            if (null != vertexNumConsumer)
                vertexNumConsumer.accept(vertexNum);
            if (null != edgeNumConsumer)
                edgeNumConsumer.accept(edgeNum);
            if (null != edgeConsumer) {
                String readLine;
                while ((readLine = reader.readLine()) != null) {
                    String[] split = readLine.split(" ");
                    if (2 != split.length)
                        continue;
                    edgeConsumer.accept(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 构建 mediumEWG.txt 有权重图
     */
    public static EdgeWeightedGraph<Integer> BUILD_BY_MEDIUM_EWG() {
        AtomicReference<EdgeWeightedGraph<Integer>> graph = new AtomicReference<>();

        GraphTestUtil.readMediumEWG(
                vertexNum -> {
                    graph.set(new EdgeWeightedGraph<>(vertexNum));
                    graph.get().initVertexArray(IntStream.range(0, vertexNum).boxed().toArray(Integer[]::new));
                },
                edgeNumber -> graph.get().setEdgeNumber(edgeNumber),
                edge -> graph.get().addEdge(edge));

        return graph.get();
    }

    /**
     * 读取 mediumEWG.txt 有权重图
     */
    @SuppressWarnings({"ConstantConditions"})
    private static void readMediumEWG(IntConsumer vertexNumConsumer, IntConsumer edgeNumConsumer, Consumer<EdgeWeighted> edgeConsumer) {
        try (InputStream stream = AdjacencyMatrix.class.getResourceAsStream("/data/graph/mediumEWG.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(stream));) {

            int vertexNum = Integer.parseInt(reader.readLine());
            int edgeNum = Integer.parseInt(reader.readLine());

            if (null != vertexNumConsumer)
                vertexNumConsumer.accept(vertexNum);
            if (null != edgeNumConsumer)
                edgeNumConsumer.accept(edgeNum);
            if (null != edgeConsumer) {
                String readLine;
                while ((readLine = reader.readLine()) != null) {
                    String[] split = readLine.split(" ");
                    if (3 != split.length)
                        throw new RuntimeException();
                    edgeConsumer.accept(new EdgeWeighted(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Double.parseDouble(split[2])));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
