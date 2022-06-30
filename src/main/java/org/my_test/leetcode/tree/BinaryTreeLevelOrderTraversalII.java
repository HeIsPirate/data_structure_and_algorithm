package org.my_test.leetcode.tree;

import org.my_test.leetcode.util.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/binary-tree-level-order-traversal-ii/">二叉树的层序遍历 II</a>
 */
public class BinaryTreeLevelOrderTraversalII {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (null == root) {
            return new LinkedList<>();
        }

        LinkedList<List<Integer>> result = new LinkedList<>();
        List<TreeNode> thisLevelNodes = new LinkedList<>();
        thisLevelNodes.add(root);
        List<TreeNode> nextLevelNodes = new LinkedList<>();


        while (!thisLevelNodes.isEmpty()) {
            List<Integer> thisLevelValues = new LinkedList<>();
            for (TreeNode thisLevelNode : thisLevelNodes) {
                thisLevelValues.add(thisLevelNode.val);

                if (null != thisLevelNode.left)
                    nextLevelNodes.add(thisLevelNode.left);
                if (null != thisLevelNode.right)
                    nextLevelNodes.add(thisLevelNode.right);
            }
            result.addFirst(thisLevelValues);

            thisLevelNodes = nextLevelNodes;
            nextLevelNodes = new LinkedList<>();
        }

        return result;
    }
}
