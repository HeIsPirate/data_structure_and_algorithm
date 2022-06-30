package org.my_test.leetcode.tree;

import org.my_test.leetcode.util.TreeNode;

import java.util.concurrent.atomic.AtomicReference;

/**
 * <a href="https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/">二叉树展开为链表</a>
 */
public class FlattenBinaryTreeToLinkedList {
    public void flatten(TreeNode root) {
        AtomicReference<RootAndLast> rootAndLastNode = new AtomicReference<>();
        preOrderTraversal(root, rootAndLastNode);
        if (null != root) {
            root.left = null;
            root.right = rootAndLastNode.get().root.right;
        }
    }

    private void preOrderTraversal(TreeNode parent, AtomicReference<RootAndLast> prevNode) {
        if (null == parent) {
            return;
        }

        TreeNode newNode = new TreeNode(parent.val);
        RootAndLast rootAndLastNode = prevNode.get();
        if (null == rootAndLastNode) {
            prevNode.set(new RootAndLast(newNode, newNode));
        } else {
            rootAndLastNode.last.right = newNode;
            rootAndLastNode.last = newNode;
        }


        preOrderTraversal(parent.left, prevNode);
        preOrderTraversal(parent.right, prevNode);
    }

    static class RootAndLast {
        TreeNode root;
        TreeNode last;

        public RootAndLast(TreeNode root, TreeNode last) {
            this.root = root;
            this.last = last;
        }
    }
}
