package org.my_test.algorithms_book.dynamic_connectivity;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class QuickUnionWithPathCompression extends QuickUnion {
    public QuickUnionWithPathCompression(Object[] array) {
        super(array);
    }

    @Override
    protected Node findNode(Object p) {
        return this.findNodeWithPathCompression(p);
    }

    private Node findNodeWithPathCompression(Object p) {
        List<Node> nodePathRecord = new LinkedList<>();
        for (Node node : this.siteNodeList) {
            if (p == node.site) {
                // 到达树的顶层
                if (node.nextNode == null) {
                    this.compressNodePath(nodePathRecord, node);
                    return node;
                } else {
                    nodePathRecord.add(node);
                    // 递归
                    return this.findNode(node.nextNode.site);
                }
            }
        }
        throw new NoSuchElementException("site: " + p + " is not exists");
    }

    private void compressNodePath(List<Node> subTree, Node topNode) {
        subTree.forEach(node -> {
            node.nextNode = topNode;
        });
    }
}
