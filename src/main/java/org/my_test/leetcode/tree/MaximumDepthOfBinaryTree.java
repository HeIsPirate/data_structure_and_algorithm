package org.my_test.leetcode.tree;

import org.my_test.leetcode.util.TreeNode;

import java.util.LinkedList;

/**
 * <a href="https://leetcode.cn/problems/maximum-depth-of-binary-tree/">二叉树的最大深度</a>
 */
public class MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        int depth = 0;
        // 层序遍历
        {
            LinkedList<TreeNode> levelNodeList = new LinkedList<>();
            LinkedList<TreeNode> nextLevelNodeList = new LinkedList<>();
            if (null != root) {
                levelNodeList.add(root);
                depth++;
            }

            while (!levelNodeList.isEmpty()) {
                TreeNode node = levelNodeList.pollFirst();
                TreeNode left = node.left;
                TreeNode right = node.right;

                if (null != left) nextLevelNodeList.add(left);
                if (null != right) nextLevelNodeList.add(right);

                if (levelNodeList.isEmpty() && !nextLevelNodeList.isEmpty()) {
                    levelNodeList = nextLevelNodeList;
                    nextLevelNodeList = new LinkedList<>();
                    depth++;
                }
            }
        }
        return depth;
    }
}
