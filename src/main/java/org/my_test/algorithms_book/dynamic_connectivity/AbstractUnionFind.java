package org.my_test.algorithms_book.dynamic_connectivity;

public abstract class AbstractUnionFind {

    public AbstractUnionFind(@SuppressWarnings("unused") Object[] array) {
        // 初始化代码
    }

    public abstract String find(Object p);

    @SuppressWarnings("unused")
    public abstract void union(Object p, Object q);

    public boolean connected(Object p, Object q) {
        return find(p).equals(find(q));
    }
}
