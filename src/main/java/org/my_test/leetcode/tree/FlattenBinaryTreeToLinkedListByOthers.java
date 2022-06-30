package org.my_test.leetcode.tree;

import org.my_test.leetcode.util.TreeNode;

/**
 * <a href="https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/">二叉树展开为链表</a>
 * <br>
 * 原地算法, 且空间复杂度O(1)
 */
@SuppressWarnings({"unused"})
public class FlattenBinaryTreeToLinkedListByOthers {

    /**
     * <a href="https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/solution/er-cha-shu-zhan-kai-wei-lian-biao-by-leetcode-solu/">官方</a>
     * <br>
     * 好巧妙啊
     * <br>
     * 问题转化成寻找当前节点的前驱节点<br>
     * 比如官方示例: [1,2,5,3,4,null,6]<br>
     * 可以发现, 所有反斜杠的边, 和结果中顺序是一样的, 要做的就是消灭正斜杠<br>
     * 当curr = root时, 第一遍循环将5-6弹出并转移为4的右结点, 并将2子树变为右子树<br>
     * <br>
     * 没有用到递归(前中后序)<br>
     */
    public void flattenByLeetcode(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                // 寻找curr.right(右子结点)的前驱结点(predecessor), 就是左子树的最右结点!
                TreeNode next = curr.left;
                TreeNode predecessor = next;
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                predecessor.right = curr.right; // 拼接为链表
                curr.left = null;
                curr.right = next; // 原curr.right已经转移走了, 将左子树变右子树, 左变右原因在于, 最后的链表是右倾斜的, 这样能保证遍历所有的结点
            }
            curr = curr.right; // 继续遍历右
        }
    }

    /**
     * <a href="https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/comments/71016">别人实现的, 简洁</a>
     * <br>
     * 后序遍历
     */
    public void flatten(TreeNode root) {
        if (root == null) return;

        flatten(root.left);
        flatten(root.right);

        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = null;
        while (root.right != null) root = root.right;
        root.right = tmp;
    }
}