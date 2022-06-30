package org.my_test.leetcode.tree;

import org.my_test.leetcode.util.TreeNode;
import org.my_test.leetcode.util.TreeUtil;
import org.testng.annotations.Test;

public class RecoverBinarySearchTreeTest {
    @Test
    public void test() {
        execute("[1,3,null,null,2]");
        // 预期结果: [3,1,null,null,2]
    }

    @Test
    public void test2() {
        execute("[3,1,4,null,null,2]");
        // 预期结果: [3,1,null,null,2]
    }

    @Test
    public void test3() {
        execute("[1,3,null,null,2]");
        // 预期结果: [2,1,4,null,null,3]
    }

    private void execute(String string) {
        TreeNode root = TreeUtil.build(string);
        new RecoverBinarySearchTree().recoverTree(root);
        System.out.println(FindDuplicateSubtrees.levelOrder(root));
    }
}