package org.my_test.leetcode.tree;

import org.my_test.leetcode.util.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/">二叉树的锯齿形层序遍历</a>
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        LinkedList<TreeNode> levelNodeList = new LinkedList<>();
        LinkedList<TreeNode> nextLevelNodeList = new LinkedList<>();
        if (null != root) {
            levelNodeList.add(root);
            result.addLast(new LinkedList<>());
        }
        int level = 0;

        while (!levelNodeList.isEmpty()) {
            TreeNode node = levelNodeList.pollFirst();

            // 添加到result中
            result.peekLast().add(node.val);

            // 形成交替
            if (level % 2 != 0) {
                if (null != node.right) nextLevelNodeList.addFirst(node.right);
                if (null != node.left) nextLevelNodeList.addFirst(node.left);
            } else {
                if (null != node.left) nextLevelNodeList.addFirst(node.left);
                if (null != node.right) nextLevelNodeList.addFirst(node.right);
            }

            // 下一层
            if (levelNodeList.isEmpty() && !nextLevelNodeList.isEmpty()) {
                level++;
                levelNodeList = nextLevelNodeList;
                nextLevelNodeList = new LinkedList<>();
                result.addLast(new LinkedList<>());
            }
        }

        return result;
    }
}
