package org.my_test.leetcode.tree;

import org.my_test.leetcode.util.TreeNode;

/**
 * <a href="https://leetcode.cn/problems/invert-binary-tree/">翻转二叉树</a>
 * <p>这里通过递归实现的</p>
 */
@SuppressWarnings("unused")
public class InvertBinaryTree {

    @SuppressWarnings("InnerClassMayBeStatic")
    class Solution {
        public TreeNode invertTree(TreeNode root) {
            if (null == root) {
                return new TreeNode();
            }

            TreeNode invertTreeRoot = new TreeNode(root.val);
            recursive(root, invertTreeRoot);
            return invertTreeRoot;
        }

        private void recursive(TreeNode oldTreeParent, TreeNode newTreeParent) {
            TreeNode oldLeft = oldTreeParent.left;
            TreeNode oldRight = oldTreeParent.right;

            if (null != oldRight) {
                TreeNode newLeft = new TreeNode(oldRight.val);
                newTreeParent.left = newLeft;
                recursive(oldRight, newLeft);
            }
            if (null != oldLeft) {
                TreeNode newRight = new TreeNode(oldLeft.val);
                newTreeParent.right = newRight;
                recursive(oldLeft, newRight);
            }
        }
    }
}