package org.my_test.leetcode.tree;

import org.testng.annotations.Test;

import static org.my_test.leetcode.util.TreeUtil.build;

public class BinaryTreeLevelOrderTraversalIITest {
    @Test
    public void test() {
        System.out.println(new BinaryTreeLevelOrderTraversalII().levelOrderBottom(build("[3,9,20,null,null,15,7]")));
        System.out.println(new BinaryTreeLevelOrderTraversalII().levelOrderBottom(build("[1]")));
        System.out.println(new BinaryTreeLevelOrderTraversalII().levelOrderBottom(build("[]")));
    }
}
