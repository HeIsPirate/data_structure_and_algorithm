package org.my_test.leetcode.tree;

/**
 * <a href="https://leetcode.cn/problems/unique-binary-search-trees/">不同的二叉搜索树</a>
 */
public class UniqueBinarySearchTrees {
    public int numTrees(int n) {
        if (1 == n) {
            return 1;
        }

        /**
         * 1 2
         * 2 1
         * => 2 * 1 = 2
         *
         * 1 2 3
         * 1 3 2
         * 2 1 3
         * 2 3 1
         * 3 1 2
         * 3 2 1
         * => 3 * 2 = 6
         * 有<2,1,3>和<2,3,1>是一样的
         *
         * 1 2 3 4
         * 1 2 4 3
         * 1 3 2 4
         * 1 3 4 2
         * 1 4 2 3
         * 1 4 3 2
         * ~~~
         * => 4 * 3 * 2 * 1 = 24
         * <1,x,y,z>中有<1,3,2,4>和<1,3,4,2>是一样的
         *
         * 2 1 3 5 4 6
         * 2 1 3 5 6 4
         * 2 3 1 5 4 6
         * 2 1 3 5 6 4
         * 这个4个都是一样的...
         * 1 3 2 4 5 6
         * 1 3 4 2 5 6
         */

        //TODO 好像用到了动态规划...以后再解决
        return -1;
    }
}
