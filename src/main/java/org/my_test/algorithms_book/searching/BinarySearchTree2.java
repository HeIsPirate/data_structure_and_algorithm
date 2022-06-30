package org.my_test.algorithms_book.searching;

import java.util.Objects;

public class BinarySearchTree2<K extends Comparable<K>, V> extends AbstractST<K, V> {
    private Node<K, V> root;

    @Override
    @SuppressWarnings("UnnecessaryContinue")
    public void put(K key, V value) {
        if (null == root) {
            root = new Node<>(key, value, null, null, null);
            return;
        }
        Node<K, V> v = root;
        while (true) {
            if (equalTo(key, v.key)) {
                v.key = key;
                return;
            }
            if (lessThan(key, v.key)) {
                if (v.left == null) {
                    v.left = new Node<>(key, value, v, null, null);
                    return;
                } else {
                    v = v.left;
                    continue;
                }
            }
            if (greaterThan(key, v.key)) {
                if (v.right == null) {
                    v.right = new Node<>(key, value, v, null, null);
                    return;
                } else {
                    v = v.right;
                    continue;
                }
            }
        }
    }

    @Override
    public void delete(K key) {
        Node<K, V> node = getNode(key);
        if (null == node) return;

        if (node.parent == null) {
            root = null;
            return;
        }

        if (null != node.right) {
            Node<K, V> ceilingNode = deleteMin(node.right);
            if (node.parent.left == node)
                node.parent.left = ceilingNode;
            else if (node.parent.right == node)
                node.parent.right = ceilingNode;

            node.right.parent = node.parent;
        }
        if (null != node.left) {
            node.parent.left = node.left;
        }
    }

    private Node<K, V> deleteMin(Node<K, V> node) {
        Objects.requireNonNull(node);

        Node<K, V> v = node;
        while (true) {
            if (v.left == null) {
                if (v.parent == null)
                    root = null;
                else {
                    if (v.parent.left == v)
                        v.parent.left = null;
                    else
                        // 如果方法参数的节点, 是某个父节点的右节点, 且该节点没有子节点
                        if (v.parent.right == v)
                            v.parent.right = null;
                }
                return v;
            }
            v = v.left;
        }
    }

    @Override
    public V get(K key) {
        Node<K, V> node = getNode(key);
        if (null != node) return node.value;
        return null;
    }

    @SuppressWarnings("UnnecessaryContinue")
    private Node<K, V> getNode(K key) {
        Node<K, V> v = root;
        while (true) {
            if (equalTo(key, v.key)) {
                return v;
            }
            if (lessThan(key, v.key)) {
                if (null == v.left) {
                    return null;
                } else {
                    v = v.left;
                    continue;
                }
            }
            if (greaterThan(key, v.key)) {
                if (null == v.right) {
                    return null;
                } else {
                    v = v.right;
                    continue;
                }
            }
        }
    }

    public static void main(String[] args) {
        BinarySearchTree2<String, Object> map = new BinarySearchTree2<>();
        map.put("xue", 111222);
        map.put("jia", "bei");

        System.out.println(map.get("xue"));
        System.out.println(map.get("jia"));
    }

    private static class Node<K extends Comparable<K>, V> {
        protected K key;
        protected V value;
        /*父指针*/
        protected Node<K, V> parent;
        /*左子节点指针*/
        protected Node<K, V> left;
        /*右子节点指针*/
        protected Node<K, V> right;

        public Node(K key, V value, Node<K, V> parent, Node<K, V> left, Node<K, V> right) {
            this.key = key;
            this.value = value;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
    }
}
