package org.my_test.algorithms_book.dynamic_connectivity;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class QuickUnion extends AbstractUnionFind {
    protected final List<Node> siteNodeList = new LinkedList<>();

    protected static class Node {
        protected final Object site;
        protected final String connectId;
        protected Node nextNode;
        protected int treeNodeCount;

        private Node(Object site, String connectId, Node nextNode, int treeNodeCount) {
            this.site = site;
            this.connectId = connectId;
            this.nextNode = nextNode;
            this.treeNodeCount = treeNodeCount;
        }
    }

    public QuickUnion(Object[] array) {
        super(array);
        for (Object site : array) {
            final int treeNodeCount = 1;
            siteNodeList.add(new Node(site, UUID.randomUUID().toString(), null, treeNodeCount/*初始为一, 本类不统计*/));
        }
    }

    protected Node findNode(Object p) {
        for (Node node : this.siteNodeList) {
            if (p == node.site) {
                // 到达树的顶层
                if (node.nextNode == null) {
                    return node;
                } else {
                    // 递归
                    return this.findNode(node.nextNode.site);
                }
            }
        }
        throw new NoSuchElementException("site: " + p + " is not exists");
    }

    @Override
    public String find(Object p) {
        return this.findNode(p).connectId;
    }

    @Override
    public void union(Object p, Object q) {
        Node pNode = findNode(p);
        Node qNode = findNode(q);
        if (!pNode.connectId.equals(qNode.connectId)) {
            pNode.nextNode = qNode;
        }
    }
}