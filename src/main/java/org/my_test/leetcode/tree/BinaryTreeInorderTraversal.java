package org.my_test.leetcode.tree;

import org.my_test.leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/binary-tree-inorder-traversal/">二叉树的中序遍历</a>
 */
@SuppressWarnings("unused")
public class BinaryTreeInorderTraversal {

    public List<Integer> inorderTraversal(final TreeNode root) {
        ArrayList<Integer> valList = new ArrayList<>();
        recursive(root, valList);
        return valList;
    }

    private void recursive(final TreeNode parent, List<Integer> valList) {
        if (null == parent) {
            return;
        }
        recursive(parent.left, valList);
        valList.add(parent.val);
        recursive(parent.right, valList);
    }

}
