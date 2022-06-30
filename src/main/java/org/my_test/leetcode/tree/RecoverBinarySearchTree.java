package org.my_test.leetcode.tree;

import org.my_test.leetcode.util.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/recover-binary-search-tree/">恢复二叉搜索树</a>
 * <br>
 * 最朴素的方法
 */
public class RecoverBinarySearchTree {
    public void recoverTree(TreeNode root) {
        inOrderTraverse(root, new LinkedList<>());
    }

    private void inOrderTraverse(TreeNode parent, List<TreeNode> nodeStack) {
        if (null == parent)
            return;

        inOrderTraverse(parent.left, nodeStack);

        for (TreeNode prev : nodeStack) {
            if (parent.val < prev.val) {
                int tmp = parent.val;
                parent.val = prev.val;
                prev.val = tmp;
            }
        }
        nodeStack.add(parent);

        inOrderTraverse(parent.right, nodeStack);
    }
}
