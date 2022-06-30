package org.my_test.leetcode.util;

import org.my_test.leetcode.tree.BinaryTreeLevelOrderTraversal;

import java.util.*;

/**
 * 层序构建树
 */
public class TreeUtil {

    /**
     * 以leetcode方式构建树
     */
    public static TreeNode build(String valueArrayString) {
        if (valueArrayString.startsWith("[")) valueArrayString = valueArrayString.substring(1);
        if (valueArrayString.endsWith("]"))
            valueArrayString = valueArrayString.substring(0, valueArrayString.length() - 1);
        if (valueArrayString.isEmpty())
            return null;
        Integer[] arr = Arrays.stream(valueArrayString.split(",")).map(i -> (null == i || "null".equals(i.trim())) ? null : Integer.parseInt(i.trim())).toArray(Integer[]::new);
        // 使用队列来存储每一层的非空节点，下一层的数目要比上一层高
        ArrayDeque<TreeNode> pre = new ArrayDeque<>();
        TreeNode root = new TreeNode(arr[0]);
        pre.addLast(root);
        // 表示要遍历的下一个节点
        int index = 0;
        while (!pre.isEmpty()) {
            ArrayDeque<TreeNode> cur = new ArrayDeque<>();
            while (!pre.isEmpty()) {
                TreeNode node = pre.removeFirst();
                TreeNode left = null;
                TreeNode right = null;
                // 如果对应索引上的数组不为空的话就创建一个节点,进行判断的时候，
                // 要先索引看是否已经超过数组的长度，如果索引已经超过了数组的长度，那么剩下节点的左右子节点就都是空了
                // 这里index每次都会增加，实际上是不必要的，但是这样写比较简单
                if (++index < arr.length && arr[index] != null) {
                    left = new TreeNode(arr[index]);
                    cur.addLast(left);
                }
                if (++index < arr.length && arr[index] != null) {
                    right = new TreeNode(arr[index]);
                    cur.addLast(right);
                }
                node.left = left;
                node.right = right;
            }
            pre = cur;
        }
        return root;
    }

    /**
     * {@link BinaryTreeLevelOrderTraversal#levelOrder(BinaryTreeLevelOrderTraversal.TreeNode)}
     */
    @SuppressWarnings("JavadocReference")
    public static <A extends BinaryNode<A, B>, B> List<List<B>> levelOrder(A root) {
        List<List<B>> result = new LinkedList<>();
        List<A> levelNodes = new LinkedList<>();
        List<A> nextLevelNodes = new LinkedList<>();

        if (null == root) {
            return new ArrayList<>();
        }

        levelNodes.add(root);

        while (!levelNodes.isEmpty()) {
            List<B> levelNodeValues = new LinkedList<>();
            for (A node : levelNodes) {
                levelNodeValues.add(node.val);
                if (null != node.left) nextLevelNodes.add(node.left);
                if (null != node.right) nextLevelNodes.add(node.right);
            }
            result.add(levelNodeValues);

            levelNodes = nextLevelNodes;
            nextLevelNodes = new LinkedList<>();
        }

        return result;
    }
}