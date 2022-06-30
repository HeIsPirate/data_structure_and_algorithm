package org.my_test.algorithms_book.graph;

/**
 * 有权重边
 */
public class EdgeWeighted extends Edge implements Comparable<EdgeWeighted> {
    private double weight;

    private EdgeWeighted(int aVertexIndex, int bVertexIndex) {
        super(aVertexIndex, bVertexIndex);
    }

    public EdgeWeighted(int aVertexIndex, int bVertexIndex, double weight) {
        this(aVertexIndex, bVertexIndex);
        this.weight = weight;
    }

    public double getWeight() {
        return this.weight;
    }

    @Override
    public int compareTo(EdgeWeighted o) {
        return Double.compare(this.weight, o.weight);
    }

    @Override
    public String toString() {
        return "(%s,%s/%s)".formatted(this.aVertexIndex, this.bVertexIndex, this.weight);
    }
}
