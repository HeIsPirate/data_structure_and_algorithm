package org.my_test.leetcode.tree;

import org.testng.annotations.Test;

import static org.my_test.leetcode.util.TreeUtil.build;

public class SameTreeTest {
    @Test
    public void test() {
        System.out.println(new SameTree().isSameTree(build("[1,2,3]"), build("[1,2,3]")));
        System.out.println(new SameTree().isSameTree(build("[1,2]"), build("[1,null,2]")));
        System.out.println(new SameTree().isSameTree(build("[1,2,1]"), build("[1,1,2]")));
    }
}
