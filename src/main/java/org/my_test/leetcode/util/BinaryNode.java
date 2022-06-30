package org.my_test.leetcode.util;

/**
 * 树结点
 *
 * @param <NODE_TYPE>  标识结点确切类型 <pre> class StudentNode extends BinaryNode&lt;StudentNode, Student&gt;</pre>
 * @param <VALUE_TYPE> 结点内容类型
 */
public abstract class BinaryNode<NODE_TYPE extends BinaryNode<NODE_TYPE, VALUE_TYPE>, VALUE_TYPE> {
    public VALUE_TYPE val;
    public NODE_TYPE left;
    public NODE_TYPE right;

    public abstract NODE_TYPE newNode();

    @SuppressWarnings("unchecked")
    public NODE_TYPE setLeft(NODE_TYPE left) {
        this.left = left;
        return (NODE_TYPE) this;
    }

    @SuppressWarnings("unchecked")
    public NODE_TYPE setRight(NODE_TYPE right) {
        this.right = right;
        return (NODE_TYPE) this;
    }

    @SuppressWarnings("unchecked")
    public NODE_TYPE setVal(VALUE_TYPE val) {
        this.val = val;
        return (NODE_TYPE) this;
    }
}