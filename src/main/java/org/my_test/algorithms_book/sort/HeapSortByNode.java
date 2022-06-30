package org.my_test.algorithms_book.sort;

/**
 * <p>堆排序用的是`二叉堆`, 原因就在于二叉堆是一颗完全二叉树, 可以使用数组表示</p>
 * <p>使用数组表示的原因是: 构建完全二叉树</p>
 * <p>所以虽然是树, 但不适合使用二叉树链表实现</p>
 */
@SuppressWarnings("ALL")
@Deprecated
public class HeapSortByNode<T extends Comparable<T>> extends AbstractSort<T> {
    private Node<T> root;
    private Node<T> last;

    private static class Node<T extends Comparable<T>> {
        protected T item;
        protected Node<T> parent; // 父结点
        protected Node<T> left; // 左子结点
        protected Node<T> right; // 右子结点
        protected Node<T> next; // 弟弟节点

        public Node(T item) {
            this.item = item;
        }
    }

    /**
     * <p>尝试交换两个结点, 但是指针变动太麻烦了</p>
     * <p>因此直接交换结点的值</p>
     */
    private void swim(Node<T> node) {
        Node<T> parent = node.parent;
        if (parent == null) {
            return;
        }
        if (less(node.item, parent.item)) {
            T temp = node.item;
            node.item = parent.item;
            parent.item = temp;
            swim(parent);
        }
    }

    private void sink(Node<T> node) {

    }

    /**
     * 构建二叉堆
     */
    private void put(T t) {
        if (null == root) {
            Node<T> node = new Node<>(t);
            root = last = node;
            return;
        }

        // 如何正确地插入结点?
        // 即使有last结点都不行
        // 一定发生随机访问? 是的
        // 链表不能通过下标, 定位地访问元素
        Node<T> newNode = new Node<>(t);
        Node<T> oldLastParent = last.parent;
        if (null == oldLastParent) {
            root.left = last = newNode;
            return;
        }
        if (null == oldLastParent.right) {
            oldLastParent.right = oldLastParent.left.next = newNode;
            return;
        }
        Node<T> newParent = oldLastParent.next;
        if (null == newParent) {
            // 必须遍历才能换层...
        }
    }

    @Override
    public void sort(T[] arr) {

    }
}
