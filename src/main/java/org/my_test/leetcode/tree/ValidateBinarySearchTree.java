package org.my_test.leetcode.tree;

import org.my_test.leetcode.util.TreeNode;

import java.util.concurrent.atomic.AtomicReference;

/**
 * <a href="https://leetcode.cn/problems/validate-binary-search-tree/">验证二叉搜索树</a>
 */
public class ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        try {
            inOrderTraversal(root, new AtomicReference<>());
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 中序遍历, 与前一个值比较
     */
    private void inOrderTraversal(TreeNode parent, AtomicReference<Integer> prevValue) {
        if (null == parent) {
            return;
        }

        inOrderTraversal(parent.left, prevValue);

        Integer prevValueGet = prevValue.getAndSet(parent.val);
        if (null != prevValueGet && parent.val <= prevValueGet) {
            throw new RuntimeException();
        }

        inOrderTraversal(parent.right, prevValue);
    }
}