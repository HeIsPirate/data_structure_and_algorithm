package org.my_test.leetcode.tree;

import org.my_test.leetcode.util.TreeNode;

/**
 * <a href="https://leetcode.cn/problems/maximum-depth-of-binary-tree/">二叉树的最大深度</a>
 * <p>通过"Dfs深度优先遍历"实现</p>
 */
public class MaximumDepthOfBinaryTreeByDfs {
    public int maxDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }

        int depth = 1;

        int leftDepth = this.maxDepth(root.left);
        int rightDepth = this.maxDepth(root.right);
        // 取"较大的那个子树高度"相加
        depth += Math.max(leftDepth, rightDepth);
        return depth;
    }
}
