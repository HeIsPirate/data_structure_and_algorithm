package org.my_test.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.my_test.leetcode.tree.OperationsOnTreeDeprecated.LockingTree.LockType.X;

/**
 * <a href="https://leetcode.cn/problems/operations-on-tree/">树上的操作</a>
 * <p>理解有误, 要先把题目读明白才开始!!</p>
 */
@Deprecated
@SuppressWarnings("all")
public class OperationsOnTreeDeprecated {
    public static class LockingTree {

        Lock[] lockArray;

        public LockingTree(int[] parent) {
            lockArray = new Lock[parent.length];
            for (int i = 0; i < lockArray.length; i++) {
                lockArray[i] = new Lock();
            }

            for (int i = 0; i < parent.length; i++) {
                if (0 == i) {
                    lockArray[0].parentIndex = -1;
                    continue;
                }
                lockArray[i].parentIndex = parent[i];
                lockArray[parent[i]].children.add(i);
            }
        }

        /**
         * 加排他锁
         */
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        public boolean lock(int num, int user) {
            Lock lock = lockArray[num];
            if (X == lock.lockType) {
                return false;
            }
            if (-1 == lock.parentIndex) {
                lock.lockType = X;
                lock.xUser.add(user);
                return true;
            }
            //
            Lock curParent = lockArray[lock.parentIndex];
            List<Lock> ixLock = new LinkedList<>();
            while (true) {
                if (null == curParent.lockType) {
                    curParent.lockType = LockType.IX;
                    curParent.ixUser.add(user);
                    ixLock.add(curParent);
                } else {
                    curParent.ixUser.add(user);
                    ixLock.add(curParent);
                }
                if (-1 == curParent.parentIndex) {
                    break;
                }
                curParent = lockArray[curParent.parentIndex];
            }
            //
            lock.lockType = X;
            lock.user.add(user);
            return true;
        }

        public boolean unlock(int num, int user) {
            Lock lock = lockArray[num];
            if (!lock.user.contains(user)) {
                return false;
            }
            if (-1 == lock.parentIndex) {
                lock.lockType = null;
                lock.user.clear();
                return true;
            }
            //
            Lock curParent = lockArray[lock.parentIndex];
            while (true) {
                if (X == curParent.lockType) {
                    break;
                }
                List<Integer> userList = curParent.user;
                userList.remove((Object) user);
                if (userList.isEmpty()) {
                    curParent.lockType = null;
                }
                if (-1 == curParent.parentIndex) {
                    break;
                }
                curParent = lockArray[curParent.parentIndex];
            }
            //
            lock.lockType = null;
            lock.user.clear();
            return true;
        }

        @SuppressWarnings("CollectionAddAllCanBeReplacedWithConstructor")
        public boolean upgrade(int num, int user) {
            Lock lock = lockArray[num];
            if (lock.lockType != LockType.IX) {
                return false;
            }
            Queue<Integer> children = new LinkedList<>();
            children.addAll(lock.children);
            while (!children.isEmpty()) {
                Integer childIndex = children.poll();
                Lock child = lockArray[childIndex];
                if (null != child.children && null != child.lockType) {
                    children.addAll(child.children);
                }
                child.lockType = null;
                child.user.clear();
            }
            lock.lockType = X;
            lock.user.clear();
            lock.user.add(user);
            return true;
        }

        public static class Lock {
            List<Integer> ixUser = new LinkedList<>();
            List<Integer> xUser = new LinkedList<>();
            List<Integer> user = new LinkedList<>();
            LockType lockType;
            int parentIndex;
            List<Integer> children = new ArrayList<>();

            @Override
            public String toString() {
                return """
                        {lockType: %s, user: %s}""".formatted(lockType, user);
            }
        }

        public enum LockType {
            IX, // 意向排他锁
            X // 排他锁
        }
    }
}
