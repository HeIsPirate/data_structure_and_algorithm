package org.my_test.leetcode.tree;

import org.my_test.leetcode.util.TreeNode;
import org.my_test.leetcode.util.TreeUtil;
import org.testng.annotations.Test;

public class ConstructBinaryTreeFromPreorderAndInorderTraversalTest {
    @Test
    public void one() {
        TreeNode root = new ConstructBinaryTreeFromPreorderAndInorderTraversal().buildTree(
                new int[]{3, 9, 20, 15, 7},
                new int[]{9, 3, 15, 20, 7}
        );
        System.out.println(root);
    }
}
