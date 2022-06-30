package org.my_test.leetcode.basic;

/**
 * <a href="https://leetcode.cn/problems/add-two-numbers/">两数相加</a>
 */
@SuppressWarnings({"DanglingJavadoc", "JavadocBlankLines"})
public class AddTwoNumbers {

    public static void main(String[] args) {
        /**
         * 输入：l1 = [2,4,3], l2 = [5,6,4]
         * 输出：[7,0,8]
         * 解释：342 + 465 = 807
         *
         * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
         * 输出：[8,9,9,9,0,0,0,1]
         */

        ListNode l1 = null;
        ListNode l2 = null;

        ListNode l1Prev = null;
        ListNode l2Prev = null;

        for (int i = 0; i < 7; i++) {
            if (l1 == null) {
                l1 = l1Prev = new ListNode(9);
                continue;
            }
            l1Prev.next = new ListNode(9);
            l1Prev = l1Prev.next;
        }
        for (int i = 0; i < 4; i++) {
            if (l2 == null) {
                l2 = l2Prev = new ListNode(9);
                continue;
            }
            l2Prev.next = new ListNode(9);
            l2Prev = l2Prev.next;
        }

        ListNode temp = new Solution().addTwoNumbers(l1, l2);
        while (temp != null) {
            System.out.println(temp.val);
            temp = temp.next;
        }
    }

    static class Solution {
        @SuppressWarnings("UnusedAssignment")
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode headNode = null;
            ListNode prevNode = null;
            int prevTwoDigit = 0;

            for (int i = 0; i < 100; i++) {
                int l1Val = l1 != null ? l1.val : 0;
                int l2Val = l2 != null ? l2.val : 0;

                int sum = l1Val + l2Val + prevTwoDigit;
                int twoDigit = sum / 10;
                int oneDigit = sum - twoDigit * 10;

                ListNode thisNode = new ListNode(oneDigit);
                prevTwoDigit = twoDigit;

                if (null == headNode) {
                    headNode = prevNode = thisNode;
                } else {
                    prevNode.next = thisNode;
                    prevNode = thisNode;
                }

                l1 = l1 != null ? l1.next : null;
                l2 = l2 != null ? l2.next : null;

                if (l1 == null && l2 == null) {
                    if (prevTwoDigit == 1) {
                        ListNode tailNode = new ListNode(1);
                        prevNode.next = tailNode;
                        prevNode = tailNode;
                    }
                    break;
                }
            }

            return headNode;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        @SuppressWarnings("unused")
        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        @SuppressWarnings("unused")
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
