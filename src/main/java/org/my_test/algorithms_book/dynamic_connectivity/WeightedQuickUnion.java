package org.my_test.algorithms_book.dynamic_connectivity;

public class WeightedQuickUnion extends QuickUnion {
    public WeightedQuickUnion(Object[] array) {
        super(array);
    }

    @Override
    public void union(Object p, Object q) {
        Node pNode = findNode(p);
        Node qNode = findNode(q);

        if (pNode.connectId.equals(qNode.connectId)) {
            return;
        }

        int pTreeNodeCount = pNode.treeNodeCount;
        int qTreeNodeCount = qNode.treeNodeCount;

        // 是小树的根节点 指向 大树的根节点
        // 这样大树的节点数增加了, 但是大树的高度并未增加! 对应的, 小树的节点数不变, 而小树的高度增加一!
        // 从而增加树的平衡性
        if (pTreeNodeCount >= qTreeNodeCount) {
            qNode.nextNode = pNode;
            pNode.treeNodeCount += qTreeNodeCount;
        } else {
            pNode.nextNode = qNode;
            qNode.treeNodeCount += pTreeNodeCount;
        }
    }
}
