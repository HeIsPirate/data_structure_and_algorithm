package org.my_test.leetcode.tree;

import org.my_test.leetcode.util.TreeNode;
import org.my_test.leetcode.util.TreeUtil;
import org.testng.annotations.Test;

public class MaximumDepthOfBinaryTreeByDfsTest {
    @Test
    public void one() {
        TreeNode root = TreeUtil.build("[3,9,20,null,null,15,7]");
        int depth = new MaximumDepthOfBinaryTreeByDfs().maxDepth(root);
        assert 3 == depth;
    }

    @Test
    public void two() {
        TreeNode root = TreeUtil.build("[1,null,2]");
        int depth = new MaximumDepthOfBinaryTreeByDfs().maxDepth(root);
        assert 2 == depth;
    }

    @Test
    public void three() {
        TreeNode root = TreeUtil.build("[1,2,3,4,null,null,5]");
        int depth = new MaximumDepthOfBinaryTreeByDfs().maxDepth(root);
        assert 3 == depth;
    }
}
