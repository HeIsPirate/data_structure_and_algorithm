package org.my_test.leetcode.tree;

import org.my_test.leetcode.util.TreeNode;
import org.my_test.leetcode.util.TreeUtil;
import org.testng.annotations.Test;

public class SymmetricTreeTest {
    @Test
    public void one() {
        TreeNode root = TreeUtil.build("[1,2,2,3,4,4,3]");
        boolean isSymmetric = new SymmetricTree().isSymmetric(root);
        assert isSymmetric;
    }

    @Test
    public void two() {
        TreeNode root = TreeUtil.build("[1,2,2,null,3,null,3]");
        boolean isSymmetric = new SymmetricTree().isSymmetric(root);
        assert !isSymmetric;
    }
}
