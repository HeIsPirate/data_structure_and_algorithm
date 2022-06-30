package org.my_test.leetcode.tree;

import org.my_test.leetcode.util.TreeNode;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/find-duplicate-subtrees/">寻找重复的子树</a>
 * <p>1> 关键在于, 通过将树序列化为字符串, 判断树的结构是否一样!</p>
 * <p>2> 如何序列化呢? 当然是通过遍历了! 应该选择后序遍历, 从左右子树(即底层开始), 这样一次就能遍历出所有子树的序列化字符串!!!</p>
 * <p>3> 同时要小心树的结构不同, 序列化结果一定要不同, <br>
 * 如(1-2-3)和(1-3-null-null-2)两个后序遍历的结果都是2-3-1, 因此需要添加空链接标识!, 这样后序遍历的结果分别是2-3-1, 3-null-2-1, 序列化结果唯一了
 * </p>
 */
@SuppressWarnings({"CommentedOutCode", "unused"})
public class FindDuplicateSubtrees {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        return find(root);
    }

    private List<TreeNode> find(TreeNode parent) {
        /*Set<TreeNode> traversedNodeRecord = new HashSet<>();*/
        Set<String> treeStringSet = new HashSet<>();
        Map<String, TreeNode> sameTreeStringMap = new HashMap<>();
        Map<TreeNode, String> subTreeStringCache = new HashMap<>();
        // 层序遍历
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(parent);
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            if (null == node) {
                continue;
            }
            // 这一部分原本是为了防止重复遍历, 根本没有防止住
            // 比如 a-b-c, a这棵树会遍历子树b-c, b这棵树也会遍历b-c
            // 需要从叶子层开始 (从小树开始), 才知道哪些子树已生成字符串
            /*if (traversedNodeRecord.contains(node)) {continue;} traversedNodeRecord.add(node);*/

            // 序列化树为字符串
            // String serializeTreeToString = this.serializeByLevelOrder(node);
            String serializeTreeToString = this.serializeByInOrder(node, subTreeStringCache);
            // System.out.println(serializeTreeToString);

            // 将`树的字符串`添加进集合, 判断是否有重复的`树的字符串`
            boolean existSameTree = treeStringSet.contains(serializeTreeToString);
            if (existSameTree) {
                // String showTreeByLevelOrder = "[" + levelOrder(node).stream().map(i -> null == i ? "null" : String.valueOf(i)).collect(Collectors.joining(",")) + "]";
                // System.out.println("重复树: " + showTreeByLevelOrder);
                /*if ("[2,3,0]".equals(showTreeByLevelOrder)) {

                }*/
                sameTreeStringMap.put(serializeTreeToString, node);
            } else {
                treeStringSet.add(serializeTreeToString);
                /*if ("2-left-3-right-0".equals(serializeTreeToString)) {
                    this.serializeByInOrder(node, subTreeStringCache);
                }*/
            }
            //
            nodeQueue.add(node.left);
            nodeQueue.add(node.right);
        }
        return sameTreeStringMap.values().stream().toList();
    }

    /**
     * 后序遍历 (从左右子树开始遍历, 然后再遍历父结点)
     */
    private String serializeByInOrder(TreeNode root, Map<TreeNode, String> subTreeStringCache) {
        if (null == root) {
            return null;
        }
        String treeStringCache = subTreeStringCache.get(root);
        if (null != treeStringCache) {
            return treeStringCache;
        }
        String a = serializeByInOrder(root.left, subTreeStringCache);
        String b = serializeByInOrder(root.right, subTreeStringCache);
        String treeString = (null == a ? "-left-null" : "-left-" + a) + (null == b ? "-right-null" : "-right-" + b) + root.val;
        subTreeStringCache.put(root, treeString);
        return treeString;
    }

    /**
     * 层序遍历, 将子树序列化为字符串
     */
    private String serializeByLevelOrder(TreeNode root) {
        List<String> treeValueList = new LinkedList<>();
        // 层序遍历
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            treeValueList.add(null == node ? "X" : String.valueOf(node.val));
            if (null == node) {
                continue;
            }
            nodeQueue.add(node.left);
            nodeQueue.add(node.right);
        }
        return String.join("-", treeValueList);
    }

    public static List<Integer> levelOrder(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        List<TreeNode> levelNodes = new LinkedList<>();
        List<TreeNode> nextLevelNodes = new LinkedList<>();
        if (null == root) {
            return new ArrayList<>();
        }
        levelNodes.add(root);

        while (!levelNodes.isEmpty()) {
            List<Integer> levelNodeValues = new LinkedList<>();
            boolean allNull = true;
            for (TreeNode node : levelNodes) {
                levelNodeValues.add(null == node ? null : node.val);
                nextLevelNodes.add(null == node ? null : node.left);
                nextLevelNodes.add(null == node ? null : node.right);
                if (null != node && (null != node.left || null != node.right)) allNull = false;
            }
            result.addAll(levelNodeValues);

            if (allNull) {
                break;
            }

            levelNodes = nextLevelNodes;
            nextLevelNodes = new LinkedList<>();
        }

        return result;
    }
}
