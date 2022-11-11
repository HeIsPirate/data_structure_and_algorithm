package org.my_test.leetcode.tree;

import org.my_test.leetcode.util.TreeNode;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/symmetric-tree/">对称二叉树</a>
 */
@SuppressWarnings("DanglingJavadoc")
public class SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        if (null == root) {
            return true;
        }
        if (null == root.left && null == root.right) {
            return true;
        }
        if (null == root.left || null == root.right) {
            return false;
        }


        /**
         * 2022-10-15
         * 层序遍历
         */
        {
            LinkedList<TreeNode> levelNodeList = new LinkedList<>();
            LinkedList<TreeNode> nextLevelNodeList = new LinkedList<>();
            levelNodeList.add(root);
            boolean hasNextLevel = false;

            while (!levelNodeList.isEmpty()) {
                TreeNode node = levelNodeList.pollFirst();
                TreeNode left = null == node ? null : node.left;
                TreeNode right = null == node ? null : node.right;

                nextLevelNodeList.add(left);
                nextLevelNodeList.add(right);
                if (null != left || null != right) hasNextLevel = true;

                if (levelNodeList.isEmpty() && hasNextLevel) {
                    levelNodeList = nextLevelNodeList;
                    nextLevelNodeList = new LinkedList<>();
                    hasNextLevel = false;
                    boolean isSymmetricList = this.checkListSymmetric(levelNodeList);
                    if (!isSymmetricList) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * 通过"倒序后比较"判断是否对称
     */
    boolean checkListSymmetric(LinkedList<TreeNode> list) {
        LinkedList<TreeNode> copy = new LinkedList<>(list);
        Collections.reverse(copy);
        return equals(list, copy);
    }

    /**
     * 通过值比较
     */
    public static boolean equals(LinkedList<TreeNode> a, LinkedList<TreeNode> b) {
        ListIterator<TreeNode> e1 = a.listIterator();
        ListIterator<TreeNode> e2 = b.listIterator();
        while (e1.hasNext() && e2.hasNext()) {
            TreeNode o1 = e1.next();
            TreeNode o2 = e2.next();
            Integer o1Val = null == o1 ? null : o1.val;
            Integer o2Val = null == o2 ? null : o2.val;
            if (!Objects.equals(o1Val, o2Val)) return false;
        }
        return !(e1.hasNext() || e2.hasNext());
    }
}
