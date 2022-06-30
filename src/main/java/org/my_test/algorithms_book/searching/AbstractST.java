package org.my_test.algorithms_book.searching;

import java.util.LinkedList;

public abstract class AbstractST<K, V> implements ST<K, V> {
    protected <T extends Comparable<T>> boolean lessThan(T a, T b) {
        return a.compareTo(b) < 0;
    }

    protected <T extends Comparable<T>> boolean greaterThan(T a, T b) {
        return a.compareTo(b) < 0;
    }

    protected <T extends Comparable<T>> boolean equalTo(T a, T b) {
        return a.compareTo(b) == 0;
    }

    protected static abstract class AbstractNode<K, V> {
        protected K key;
        protected V value;
        protected AbstractNode<K, V> left, right;

        public void printTree(AbstractNode<K, V> parent) {
            if (null == parent) {
                System.out.println("[空]");
            }

            LinkedList<AbstractNode<K, V>> list = new LinkedList<>();
            list.addFirst(parent);
            sort(list, parent, 0);
            //TODO 打印树, 最好能体现树的结构
        }

        private void sort(LinkedList<AbstractNode<K, V>> list, AbstractNode<K, V> parent, int parentIndex) {
            if (null != parent.left) {
                list.add(parentIndex - 1, parent.left);
            }
        }

        private static class NodeWrapper<K, V> {
            private int space;
            private AbstractNode<K, V> node;
        }
    }
}
