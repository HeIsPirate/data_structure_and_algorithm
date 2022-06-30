package org.my_test.leetcode.tree;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/maximum-depth-of-n-ary-tree/">N叉树的最大深度</a>
 */
public class MaximumDepthOfTree {
    public static void main(String[] args) {
        Node rightChild = new Node(3);
        Node left = new Node(2);
        Node right = new Node(2, List.of(rightChild));
        Node root = new Node(1, Arrays.asList(left, right));

        int depth = new MaximumDepthOfTree().maxDepth(root);
        System.out.println(depth);
    }

    public int maxDepth(Node root) {
        if (null == root) {
            return 0;
        }

        List<Map.Entry<Node, Integer>> levelNodes = new ArrayList<>();
        levelNodes.add(Map.entry(root, 1));

        while (true) {
            List<Map.Entry<Node, Integer>> nextLevelNodes = new ArrayList<>();

            for (Map.Entry<Node, Integer> nodeEntry : levelNodes) {
                Node node = nodeEntry.getKey();
                Integer nodeDepth = nodeEntry.getValue();
                List<Node> childrenNodes = node.children;

                if (null != childrenNodes) {
                    int childrenDepth = nodeDepth + 1;
                    nextLevelNodes.addAll(childrenNodes.stream().map(i -> Map.entry(i, childrenDepth)).toList());
                }
            }

            if (nextLevelNodes.isEmpty()) {
                Comparator<Map.Entry<Node, Integer>> ascByDepth = Map.Entry.comparingByValue();
                levelNodes.sort(ascByDepth.reversed());
                return levelNodes.get(0).getValue();
            }

            levelNodes = nextLevelNodes;
        }
    }

    @SuppressWarnings("unused")
    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}