package org.my_test.play_with_data_structure_book.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * 二叉搜索树
 */
public class BinarySearchTree<K extends Comparable<K>, V> {
    private Node<K, V> root;

    public void add(K key, V value) {
        if (null == root) {
            root = new Node<>(key, value);
            return;
        }

        Node<K, V> curNode = root;
        while (true) {
            if (key.compareTo(curNode.key) == 0) { // key = curNode, 匹配键, 直接返回
                curNode.value = value;
                return;
            } else if (key.compareTo(curNode.key) < 0) { // key < curNode, 小于结点键, 遍历其左子树
                Node<K, V> left = curNode.left;

                if (null == left) { // add的key小于结点, 且结点无左子树了, 说明到底了, 直接插入新结点即可
                    curNode.left = new Node<>(key, value);
                    return;
                }

                curNode = left;
            } else { // key > curNode, 大于结点键, 遍历其右子树
                Node<K, V> right = curNode.right;

                if (null == right) {
                    curNode.right = new Node<>(key, value);
                    return;
                }

                curNode = right;
            }
        }
    }

    public V get(K key) {
        NodeAndParent nodeAndParent = getNode(key);
        return null == nodeAndParent ? null : nodeAndParent.node.value;
    }

    private NodeAndParent getNode(K key) {
        Node<K, V> curNode = root;
        Node<K, V> curNodeParent = null;
        while (null != curNode) {
            if (key.compareTo(curNode.key) == 0) {
                return new NodeAndParent(curNode, curNodeParent);
            } else if (key.compareTo(curNode.key) < 0) {
                curNodeParent = curNode;
                curNode = curNode.left;
            } else if (key.compareTo(curNode.key) > 0) {
                curNodeParent = curNode;
                curNode = curNode.right;
            }
        }
        return null;
    }

    /**
     * <p>删除键</p>
     * <p>步骤:<ul>
     * <li>找到替换结点, 这里取: `删除结点的左子树`的最大结点</li>
     * <li>特殊情况: 有可能`删除结点`无左子树, 此时不需要`替换结点`, 直接将结点删除后再拼接树, 树依然有序</li>
     * <li>用`替换结点`把`删除结点`替换掉: <ul>
     * <li>`替换结点`需要从左子树中弹出, 才能替换其他结点</li>
     * <li>然后替换`删除结点`, 有两种方式: <ul>
     * <li>替换结点, 重建链接</li>
     * <li>替换结点内的值, 这种简单方便</li>
     * </ul></li>
     * </ul></li>
     * </ul></p>
     */
    public void delete(K key) {
        NodeAndParent nodeAndParent = this.getNode(key);
        if (null == nodeAndParent)
            return;
        Node<K, V> node = nodeAndParent.node;
        Node<K, V> parent = nodeAndParent.parent;
        Node<K, V> left = node.left;
        Node<K, V> right = node.right;

        // 防止parent为空
        if (root == node) {
            root = null;
            return;
        }

        // 由于下面需要找到左子树的最大结点, 因此为了防止`替换结点`为空, 这里先判断左子树是否为空
        if (null == left) {
            // 只有右子树, 直接替换即可, 树依然有序
            if (key.compareTo(parent.key) < 0) {
                parent.left = right;
            } else {
                parent.right = right;
            }
            return;
        }

        // 找到`替换结点`, 这里选择`左子树中的最大结点`, 还要找到`替换结点的父结点`
        NodeAndParent leftTreeMaxNodeAndParent = max(left, node);
        Node<K, V> replaceNode = leftTreeMaxNodeAndParent.node;
        Node<K, V> replaceNodeOldParent = leftTreeMaxNodeAndParent.parent;

        // 找到替换结点后, 接下来是, 替换结点本身需要弹出, 再替换需要删除结点
        // 关于替换, 下面是通过替换结点实现的, 需要重建链接, 如果直接替换结点内的值要简单很多

        // 弹出`替换结点`, 弹出后需要把树拼接好
        replaceNodeOldParent.right = replaceNode.left;


        // 拼接与父结点的连接 (替换父结点的子连接)
        if (key.compareTo(parent.key) < 0) {
            parent.left = replaceNode;
        } else {
            parent.right = replaceNode;
        }

        // 拼接与子树的连接 (替换子树)
        replaceNode.left = node.left;
        replaceNode.right = node.right;
    }

    /**
     * 找到子树的最大结点
     */
    private NodeAndParent max(final Node<K, V> subtreeRoot, final Node<K, V> parent) {
        Node<K, V> curNode = subtreeRoot;
        Node<K, V> curNodeParent = parent;
        while (true) {
            if (null == curNode.right)
                return new NodeAndParent(curNode, curNodeParent);
            curNodeParent = curNode;
            curNode = curNode.right;
        }
    }

    @SuppressWarnings("unused")
    private NodeAndParent min(final Node<K, V> node, final Node<K, V> parent) {
        Node<K, V> curNode = node;
        Node<K, V> curNodeParent = parent;
        while (true) {
            if (null == curNode.left)
                return new NodeAndParent(curNode, curNodeParent);
            curNodeParent = curNode;
            curNode = curNode.left;
        }
    }

    /**
     * `结点及其父结点`元祖
     */
    class NodeAndParent {
        Node<K, V> node;
        Node<K, V> parent;

        public NodeAndParent(Node<K, V> node, Node<K, V> parent) {
            this.node = node;
            this.parent = parent;
        }
    }

    /**
     * 中序遍历
     */
    public List<Node<K, V>> inorderTraversal() {
        List<Node<K, V>> nodeList = new LinkedList<>();
        inorderRecursive(root, nodeList);
        return nodeList;
    }

    private void inorderRecursive(final Node<K, V> parent, final List<Node<K, V>> nodeList) {
        if (null == parent) {
            return;
        }
        inorderRecursive(parent.left, nodeList);
        nodeList.add(parent);
        inorderRecursive(parent.right, nodeList);
    }

    public static class Node<K extends Comparable<K>, V> {
        final K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return """
                    {key: %s, value: %s}""".formatted(this.key, this.value);
        }
    }
}
