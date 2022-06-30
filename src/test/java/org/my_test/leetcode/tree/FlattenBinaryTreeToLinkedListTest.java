package org.my_test.leetcode.tree;

import org.my_test.leetcode.util.TreeNode;
import org.testng.annotations.Test;

import static org.my_test.leetcode.util.TreeUtil.build;

public class FlattenBinaryTreeToLinkedListTest {
    @Test
    public void test() {
        // String testString = "1,2,5,3,4,null,6";
        String testString = "1,2,null,3,4,5";
        TreeNode root = build(testString);
        new FlattenBinaryTreeToLinkedListNotReplaceNode().flatten(root);

        System.out.println(FindDuplicateSubtrees.levelOrder(root));
    }
}
