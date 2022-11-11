package org.my_test.leetcode.tree;

import org.my_test.leetcode.util.TreeNode;

import java.util.*;
import java.util.stream.IntStream;

/**
 * <a href="https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/">从前序与中序遍历序列构造二叉树</a>
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    /**
     * <ul>
     *     <li>preorder 和 inorder 均 无重复 元素</li>
     * </ul>
     *
     * <ul>
     *     <li>preorder顺序: parent下标 < left下标 < right下标</li>
     *     <li></li>
     * </ul>
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Deque<Integer> preorderAllStack = new LinkedList<>(IntStream.of(preorder).boxed().toList());

        TreeNode root = new TreeNode(preorderAllStack.pollFirst());

        recursivelyBuildTree(root, preorderAllStack, IntStream.of(inorder).boxed().toList());

        return root;
    }

    public void recursivelyBuildTree(TreeNode parent, Deque<Integer> preorderAllStack, List<Integer> inorder) {
        if (null == parent || null == inorder || inorder.size() == 0) {
            return;
        }

        List<Integer> inorderLeft = new ArrayList<>();
        List<Integer> inorderRight = new ArrayList<>();

        boolean traversedParent = false;
        for (int i : inorder) {
            if (parent.val == i) {
                traversedParent = true;
                continue;
            }
            if (!traversedParent) inorderLeft.add(i);
            else inorderRight.add(i);
        }

        //TODO 未完成
        Integer first = preorderAllStack.peekFirst();
        if (!inorder.contains(first)) {
            return;
        }
        preorderAllStack.removeFirst();
        if (inorderLeft.size() > 0) parent.left = new TreeNode(first);
        else parent.right = new TreeNode(first);

        recursivelyBuildTree(parent.left, preorderAllStack, inorderLeft);
        recursivelyBuildTree(parent.right, preorderAllStack, inorderRight);
    }
}