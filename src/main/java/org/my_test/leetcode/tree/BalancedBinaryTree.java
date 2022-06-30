package org.my_test.leetcode.tree;

import org.my_test.leetcode.util.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/balanced-binary-tree/">平衡二叉树</a>
 */
@SuppressWarnings({"unused", "CommentedOutCode", "DanglingJavadoc"})
public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        return postOrderTraversal(root, new HashMap<>());
    }

    /**
     * 后序遍历
     */
    private boolean postOrderTraversal(TreeNode parent, Map<TreeNode, Integer> treeHeightMap) {
        if (null == parent) {
            return true;
        }
        boolean leftBalanced = postOrderTraversal(parent.left, treeHeightMap);
        boolean rightBalanced = leftBalanced && postOrderTraversal(parent.right, treeHeightMap);

        // 如果左右子树不平衡, 直接返回!
        if (!leftBalanced || !rightBalanced) {
            return false;
        }

        int leftHeight = null == parent.left ? 0 : null == treeHeightMap.get(parent.left) ? 0 : treeHeightMap.get(parent.left);
        int rightHeight = null == parent.right ? 0 : null == treeHeightMap.get(parent.right) ? 0 : treeHeightMap.get(parent.right);
        int heightDiffer = rightHeight - leftHeight;
        if (heightDiffer > 1 || heightDiffer < -1) {
            return false;
        }

        treeHeightMap.put(parent, Math.max(leftHeight, rightHeight) + 1);
        return true;
    }

    /**
     * 通过异常直接返回, 简单直接...
     */
    /*private void postOrderTraversal(TreeNode parent, Map<TreeNode, Integer> treeHeightMap) {
        if (null == parent) {
            return;
        }
        postOrderTraversal(parent.left, treeHeightMap);
        postOrderTraversal(parent.right, treeHeightMap);

        int leftHeight = null == parent.left ? 0 : treeHeightMap.get(parent.left);
        int rightHeight = null == parent.right ? 0 : treeHeightMap.get(parent.right);

        int heightDiffer = rightHeight - leftHeight;
        if (heightDiffer > 1 || heightDiffer < -1) {
            throw new RuntimeException();
        }

        treeHeightMap.put(parent, Math.max(leftHeight, rightHeight) + 1);
    }*/
}
