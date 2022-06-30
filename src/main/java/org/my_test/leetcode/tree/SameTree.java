package org.my_test.leetcode.tree;

import org.my_test.leetcode.util.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <a href="https://leetcode.cn/problems/same-tree/">相同的树</a>
 */
@SuppressWarnings({"DanglingJavadoc", "UnnecessaryContinue", "NumberEquality"})
public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        /**
         * 层序遍历
         */
        Deque<TreeNode> pNodeQueue = new LinkedList<>();
        pNodeQueue.add(p);
        Deque<TreeNode> qNodeQueue = new LinkedList<>();
        qNodeQueue.add(q);

        while (!pNodeQueue.isEmpty()) {
            TreeNode pNode = pNodeQueue.poll();
            TreeNode qNode = qNodeQueue.poll();

            if (null == pNode) {
                if (null != qNode)
                    return false;
                continue;
            } else {
                if (null == qNode)
                    return false;

                if (pNode.val != qNode.val)
                    return false;

                pNodeQueue.add(pNode.left);
                pNodeQueue.add(pNode.right);
                qNodeQueue.add(qNode.left);
                qNodeQueue.add(qNode.right);
            }
        }

        return true;
    }
}
