package org.my_test.leetcode.tree;

import org.my_test.leetcode.util.TreeNode;

/**
 * <a href="https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/">二叉树展开为链表</a>
 * <p>不通过替换结点的方式</p>
 * <p>是原地算法吗? 用了辅助对象, 有些人认为没有new结点既是原地: <a href="https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/solution/er-cha-shu-zhan-kai-wei-lian-biao-by-leetcode-solu/525428">评论</a></p>
 * <p>空间复杂度不是O(1)</p>
 * <p>这种做法, 和使用LinkedList性质是一样吧?</p>
 * <br>
 * <p>链表的顺序就是前序遍历的结果</p>
 * <p>但是不能直接前序遍历生成链表! 比如根结点root, 前序遍历时root右结点为5, 生成的链表root右结点为2!</p>
 */
@SuppressWarnings({"DanglingJavadoc", "unused"})
public class FlattenBinaryTreeToLinkedListNotReplaceNode {
    public void flatten(TreeNode root) {
        postOrderTraversal(root);
    }

    /**
     * 后序遍历
     *
     * @return `这棵子树生成的链表`的头/尾结点
     */
    private FirstAndLast postOrderTraversal(TreeNode parent) {
        if (null == parent) {
            return null;
        }

        TreeNode left = parent.left;
        TreeNode right = parent.right;

        parent.left = null;
        parent.right = null;

        FirstAndLast leftFirstAndLast = postOrderTraversal(left);
        FirstAndLast rightFirstAndLast = postOrderTraversal(right);

        /**
         * 下面将树拼接起来
         * 基本思想是: parent -> 左子树first -> 左子树last -> 右子树first -> 右子树last
         */

        // 简化处理只有右子树的情况
        if (null == leftFirstAndLast && null != rightFirstAndLast) {
            leftFirstAndLast = rightFirstAndLast;
            rightFirstAndLast = null;
        }

        // 拼接
        // parent -> 左子树first -> 左子树last -> 右子树first -> 右子树last
        if (null != leftFirstAndLast) {
            parent.right = leftFirstAndLast.first;
            // 左右子树的两个链表拼接
            if (null != rightFirstAndLast) {
                TreeNode leftSubTreeLast = null != leftFirstAndLast.last ? leftFirstAndLast.last : leftFirstAndLast.first;
                leftSubTreeLast.right = rightFirstAndLast.first;
            }
        }

        // 计算该子树的尾结点, 有这四种:  右子树first, 右子树last, 左子树first, 左子树last
        // 尾结点可以null
        TreeNode last = null;
        if (null != rightFirstAndLast)
            last = null != rightFirstAndLast.last ? rightFirstAndLast.last : rightFirstAndLast.first;
        else if (null != leftFirstAndLast)
            last = null != leftFirstAndLast.last ? leftFirstAndLast.last : leftFirstAndLast.first;

        // 返回子树的头/尾结点
        return new FirstAndLast(parent, last);
    }

    static class FirstAndLast {
        TreeNode first;
        TreeNode last;

        public FirstAndLast(TreeNode first, TreeNode last) {
            this.first = first;
            this.last = last;
        }
    }
}
