package org.my_test.algorithms_book.sort;

import java.util.Arrays;
import java.util.stream.Collectors;

public class QuickSortByNode<T extends Comparable<T>> extends AbstractSort<T> {

    private static class Node<T> {
        protected T item;
        protected Node<T> prev;
        protected Node<T> next;

        public Node(T item, Node<T> prev, Node<T> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return String.format("{item: %s, prev: %s, next: %s}", item, null == prev ? "null" : prev.item, next == null ? "null" : next.item);
        }
    }

    private Node<T> partition(Node<T> lo, Node<T> hi) {
        Node<T> v = lo;

        Node<T> left = lo.next;
        Node<T> right = hi;
        while (true) {
            while (less(left.item, v.item)) {
                left = left.next;
                if (left == hi)
                    break;
            }
            while (less(v.item, right.item)) {
                right = right.prev;
                if (right == lo)
                    break;
            }

            if (left == right || right.next == left) {
                exch(v, right);
                return right;
            }

            exch(left, right);
            left = left.next;
            right = right.prev;
        }
    }

    private void exch(Node<T> a, Node<T> b) {
        T temp = a.item;
        a.item = b.item;
        b.item = temp;
    }

    private void sortByNode(Node<T> lo, Node<T> hi) {
        if (null == lo || null == hi || lo == hi) {
            return;
        }
        Node<T> partition = this.partition(lo, hi);
        sortByNode(lo, partition.prev);
        sortByNode(partition.next, hi);
    }

    private Node<T>[] buildNode(T[] arr) {
        Node<T> first, last;
        first = last = new Node<>(arr[0], null, null);

        for (int i = 1; i < arr.length; i++) {
            Node<T> node = new Node<>(arr[i], last, null);

            last.next = node;
            last = node;
        }

        //noinspection unchecked
        return new Node[]{first, last};
    }

    @Override
    public void sort(T[] arr) {
        Node<T>[] nodes = buildNode(arr);

        sortByNode(nodes[0], nodes[1]);
    }

    public static void main(String[] args) {
        char[] before1 = "QUICKSORTEXAMPLE".toCharArray();
        Character[] before = new Character[before1.length];
        for (int i = 0; i < before1.length; i++) {
            before[i] = before1[i];
        }

        Node<Character>[] nodes = new QuickSortByNode<Character>().buildNode(before);
        Node<Character> node = nodes[0];
        while (node != null) {
            System.out.println(node);
            node = node.next;
        }

        new QuickSortByNode<Character>().sort(before);

        System.out.println(Arrays.stream(before).map(String::valueOf).collect(Collectors.joining(",")));
    }
}
