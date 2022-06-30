package org.my_test.leetcode.tree;

import org.testng.annotations.Test;

import static org.my_test.leetcode.util.TreeUtil.build;

public class ValidateBinarySearchTreeTest {
    @Test
    public void test() {
        System.out.println(new ValidateBinarySearchTree().isValidBST(build("5,4,6,null,null,3,7")));
        System.out.println(new ValidateBinarySearchTree().isValidBST(build("1,1")));
    }
}
