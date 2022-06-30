package org.my_test.leetcode.data_structure;

/**
 * <a href="https://leetcode.cn/problems/min-stack/">最小栈</a>
 */
public class MinStackClass {

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }

    static class MinStack {
        @SuppressWarnings("InnerClassMayBeStatic")
        class Node {
            int val;
            Node next;
        }

        Node head;
        Node min;

        public MinStack() {

        }

        private void setMin() {
            if (null == head) {
                min = null;
                return;
            }
            Node each = head;
            do {
                if (min == null || min.val > each.val) {
                    min = each;
                }
                each = each.next;
            } while (null != each);
        }

        public void push(int val) {
            if (null == head) {
                head = new Node();
                head.val = val;
                setMin();
                return;
            }

            Node newNode = new Node();
            newNode.val = val;
            newNode.next = head;
            head = newNode;

            setMin();
        }

        public void pop() {
            head = head.next;
            min = null;
            setMin();
        }

        public int top() {
            return null == head ? 0 : head.val;
        }

        public int getMin() {
            return null == min ? 0 : min.val;
        }
    }

    /**
     * 其他人提供的有方法
     */
    @SuppressWarnings({"InnerClassMayBeStatic", "unused"})
    class MinStackByOthers {
        private Node head;

        public void push(int x) {
            if (head == null)
                head = new Node(x, x);
            else
                head = new Node(x, Math.min(x, head.min), head);
        }

        public void pop() {
            head = head.next;
        }

        public int top() {
            return head.val;
        }

        public int getMin() {
            return head.min;
        }

        private class Node {
            int val;
            int min; // 这个min, 保存的是, 所有在这个节点底下中最小的
            Node next;

            private Node(int val, int min) {
                this(val, min, null);
            }

            private Node(int val, int min, Node next) {
                this.val = val;
                this.min = min;
                this.next = next;
            }
        }
    }
}