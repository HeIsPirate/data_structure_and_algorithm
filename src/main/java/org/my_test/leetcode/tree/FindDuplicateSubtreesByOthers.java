package org.my_test.leetcode.tree;

import org.my_test.leetcode.util.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/find-duplicate-subtrees/">寻找重复的子树</a>
 * <p><a href="https://leetcode.cn/problems/find-duplicate-subtrees/comments/1303491">别人完成的</a></p>
 */
@SuppressWarnings("unused")
public class FindDuplicateSubtreesByOthers {
    //保存每棵树序列化后的样子与出现次数的映射关系
    Map<String, Integer> map = new HashMap<>();
    List<TreeNode> res = new LinkedList<>();

    /**
     * <p>和我的区别是: 我用了两次遍历 !!!</p>
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        serialize(root);
        return res;
    }

    // 序列化一棵树，并查看是否出现过
    private String serialize(TreeNode root) {
        if (root == null) return "#";
        String s = serialize(root.left) + ","
                + serialize(root.right) + ","
                + root.val;
        Integer freq = map.getOrDefault(s, 0);
        if (freq == 1) {
            //已经出现过一次则加入结果集
            res.add(root);
        }
        map.put(s, freq + 1);

        return s;
    }
}
