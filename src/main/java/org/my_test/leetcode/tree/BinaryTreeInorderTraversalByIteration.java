package org.my_test.leetcode.tree;

import org.my_test.leetcode.util.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/binary-tree-inorder-traversal/">二叉树的中序遍历</a>
 * <p>通过迭代的方式</p>
 */
@SuppressWarnings({"UnnecessaryLocalVariable", "DanglingJavadoc"})
public class BinaryTreeInorderTraversalByIteration {
    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);

        TreeNode two = new TreeNode(2);
        head.left = two;

        TreeNode three = new TreeNode(3);
        head.right = three;

        TreeNode four = new TreeNode(4);
        two.right = four;

        /**
         *       1
         *   2      3
         *     4
         */

        List<Integer> result = new BinaryTreeInorderTraversalByIteration().inorderTraversal(head);
        System.out.println(result);
    }

    /**
     * <p>有种说法是: 递归的本质是压栈，循环中加上栈操作，其实和递归没有本质区别。</p>
     * <p></p>
     * <p>递归算法实际上是一种分而治之的方法</p>
     * <p></p>
     * <p>因为本质上讲， 递归和循环都是上一轮和下一轮执行相同的操作， </p>
     * <p>唯一不同的区别是， 递归有参数的历史记录， 循环没有（如果你没有手工记录的话）。</p>
     * <p>那么很自然， 你在循环中引入一个堆栈结构， 手工压栈， </p>
     * <p>这样循环变量（或参数） 也就有了历史记录。 这样， 所有的递归都可以用循环来代替</p>
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        Deque<TreeNode> leftNodeStack = new LinkedList<>();

        TreeNode parentNode = root;
        boolean bottom = false;
        while (true) {
            // 1. 遍历`左子结点`, 先将`左子结点`入栈, 不添加进result
            if (!bottom) {
                // `左子结点`遍历结束
                if (null == parentNode) {
                    bottom = true;
                    continue;
                }
                leftNodeStack.addFirst(parentNode);
                parentNode = parentNode.left;
            }
            // 2. `左子结点`遍历到底, 才开始出栈
            else if (/*bottom == true && */ !leftNodeStack.isEmpty()) {
                parentNode = leftNodeStack.pollFirst();
                result.add(parentNode.val);
                // 如果`右结点`不为空, 则视`右结点`为`父结点`, 继续遍历`左子结点`入栈 !!
                // `右结点`的`左子结点`入的是同一个栈, 不需要新建栈!!!
                if (null != parentNode.right) {
                    parentNode = parentNode.right;
                    bottom = false;
                }
            }
            // 3.
            else {
                break;
            }
        }

        return result;
    }
}
