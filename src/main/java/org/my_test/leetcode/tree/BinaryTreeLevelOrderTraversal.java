package org.my_test.leetcode.tree;

import org.my_test.leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/binary-tree-level-order-traversal/">二叉树的层序遍历</a>
 */
public class BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {

    }

    @SuppressWarnings({"unused"})
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        List<TreeNode> levelNodes = new LinkedList<>();
        List<TreeNode> nextLevelNodes = new LinkedList<>();

        if (null == root) {
            return new ArrayList<>();
        }

        levelNodes.add(root);

        while (!levelNodes.isEmpty()) {
            List<Integer> levelNodeValues = new LinkedList<>();
            for (TreeNode node : levelNodes) {
                levelNodeValues.add(node.val);
                if (null != node.left) nextLevelNodes.add(node.left);
                if (null != node.right) nextLevelNodes.add(node.right);
            }
            result.add(levelNodeValues);

            levelNodes = nextLevelNodes;
            nextLevelNodes = new LinkedList<>();
        }

        return result;
    }
}