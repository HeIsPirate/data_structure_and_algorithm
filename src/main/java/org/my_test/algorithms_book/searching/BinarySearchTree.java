package org.my_test.algorithms_book.searching;

import java.util.Objects;

public class BinarySearchTree<K extends Comparable<K>, V> extends AbstractIndexedST<K, V> {
    private Node<K, V> root;

    @Override
    @SuppressWarnings("UnnecessaryContinue")
    public void put(K key, V value) {
        if (null == root) {
            root = new Node<>(key, value, null, null);
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
                    v.left = new Node<>(key, value, null, null);
                    return;
                } else {
                    v = v.left;
                    continue;
                }
            }
            if (greaterThan(key, v.key)) {
                if (v.right == null) {
                    v.right = new Node<>(key, value, null, null);
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
        NodeTuple<K, V> nodeTuple = this.getNode(key);
        if (null == nodeTuple) return;
        Node<K, V> node = nodeTuple.one;
        Node<K, V> parent = nodeTuple.two;

        if (parent == null && node != root) {
            throw new RuntimeException("parent == null && node != root");
        }

        if (null != node.right) {
            Node<K, V> ceilingNode = deleteMin(node.right);
            if (null == parent) {
                ceilingNode.left = root.left;
                root = ceilingNode;
            } else {
                this.replaceNode(parent, node, ceilingNode);
            }
        } else {
            if (null == parent) {
                root = root.left;
            } else {
                this.replaceNode(parent, node, node.left);
            }
        }
    }

    /**
     * 替换节点
     *
     * @param parentNode   被替换节点的父节点
     * @param replacedNode 被替换节点
     * @param newNode      新节点 (节点大小, 在替换后, 依然符合"二叉查找树的有序性")
     */
    private void replaceNode(Node<K, V> parentNode, Node<K, V> replacedNode, Node<K, V> newNode) {
        // 更新: 父节点的子节点指针
        if (parentNode.right == replacedNode) {
            assert newNode == null || greaterThan(newNode.key, parentNode.key);
            parentNode.right = newNode;
        } else if (parentNode.left == replacedNode) {
            assert newNode == null || lessThan(newNode.key, parentNode.key);
            parentNode.left = newNode;
        }

        // 更新: 新节点的左指针
        if (null != newNode && newNode != replacedNode.left) {
            assert greaterThan(newNode.key, replacedNode.left.key);
            newNode.left = replacedNode.left;
            replacedNode.left = null;
        }

        // 更新: 新节点的右指针
        if (null != newNode && newNode != replacedNode.right) {
            assert lessThan(newNode.key, replacedNode.right.key);
            newNode.right = replacedNode.right;
            replacedNode.right = null;
        }
    }

    @Override
    public void deleteMin() {
        Node<K, V> min = deleteMin(root);
        if (min == root) {
            root = root.right;
        }
    }

    /**
     * 删除某节点下的最小节点
     * <p>(仅删除最小节点, 不删除最小节点的子节点树)</p>
     *
     * @param certainNode 节点
     * @return 最小节点
     */
    private Node<K, V> deleteMin(Node<K, V> certainNode) {
        Objects.requireNonNull(certainNode);

        Node<K, V> v = certainNode;
        Node<K, V> parent = null;
        while (true) {
            if (v.left != null) {
                parent = v;
                v = v.left;
                continue;
            }
            // v.left == null:
            {
                if (null != parent) {
                    // 父节点的左节点(就是v, 最小节点), 链接上最小节点的右节点(树)
                    parent.left = v.right;
                }
                return v;
            }
        }
    }

    @Override
    public V get(K key) {
        NodeTuple<K, V> nodeTuple = getNode(key);
        if (null != nodeTuple) return nodeTuple.one.value;
        return null;
    }

    /**
     * @param key ..
     * @return {@link NodeTuple}: {one: 匹配到的节点; two: 对应的父节点}
     */
    @SuppressWarnings("UnnecessaryContinue")
    private NodeTuple<K, V> getNode(K key) {
        Node<K, V> v = root;
        Node<K, V> parent = null;
        while (true) {
            if (equalTo(key, v.key)) {
                return new NodeTuple<>(v, parent);
            }
            if (lessThan(key, v.key)) {
                if (null == v.left) {
                    return null;
                } else {
                    parent = v;
                    v = v.left;
                    continue;
                }
            }
            if (greaterThan(key, v.key)) {
                if (null == v.right) {
                    return null;
                } else {
                    parent = v;
                    v = v.right;
                    continue;
                }
            }
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<String, Object> map = new BinarySearchTree<>();
        map.put("xue", 111222);
        map.put("jia", "bei");

        System.out.println(map.get("xue"));
        System.out.println(map.get("jia"));
    }

    private static class Node<K extends Comparable<K>, V> {
        protected K key;
        protected V value;
        /*左子节点指针*/
        protected Node<K, V> left;
        /*右子节点指针*/
        protected Node<K, V> right;

        public Node(K key, V value, Node<K, V> left, Node<K, V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    private static class NodeTuple<K extends Comparable<K>, V> {
        protected Node<K, V> one;
        protected Node<K, V> two;

        public NodeTuple(Node<K, V> one, Node<K, V> two) {
            this.one = one;
            this.two = two;
        }
    }
}
