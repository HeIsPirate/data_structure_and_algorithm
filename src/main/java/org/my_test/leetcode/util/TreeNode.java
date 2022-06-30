package org.my_test.leetcode.util;

/**
 * Leetcode二叉树结点
 */
public class TreeNode extends BinaryNode<TreeNode, Integer> {
    public TreeNode() {
    }

    public TreeNode(int val) {
        this.setVal(val);
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.setVal(val);
        this.setLeft(left);
        this.setRight(right);
    }

    @Override
    public String toString() {
        return "{val: %s}".formatted(this.val);
    }

    @Override
    public TreeNode newNode() {
        return new TreeNode();
    }
}