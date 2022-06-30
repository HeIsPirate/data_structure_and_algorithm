package org.my_test.leetcode.tree;

import org.my_test.leetcode.util.TreeNode;

/**
 * <a href="https://leetcode.cn/problems/recover-binary-search-tree/">恢复二叉搜索树</a>
 */
@SuppressWarnings("unused")
public class RecoverBinarySearchTreeOptimization {
    public void recoverTree(TreeNode root) {
        inOrderTraverse(root);
        //TODO 恢复二叉搜索树, 似乎要用到`线索二叉树`, https://zhuanlan.zhihu.com/p/101321696
    }

    private void inOrderTraverse(TreeNode parent) {
        if (null == parent)
            return;

        inOrderTraverse(parent.left);

        TreeNode predecessor = getPredecessor(parent);


        inOrderTraverse(parent.right);
    }

    private TreeNode getPredecessor(TreeNode thisNode) {
        return null;
    }

    private void swap(TreeNode a, TreeNode b) {

    }
}
