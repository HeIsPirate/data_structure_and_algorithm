package org.my_test.leetcode.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <a href="https://leetcode.cn/problems/operations-on-tree/">树上的操作</a>
 */
public class OperationsOnTree {
    public static class LockingTree {

        Lock[] lockArray;

        /**
         * @param parent 每个结点对应一个数组下标, 每个结点都有唯一的父结点, 这个父结点下标, 就是数组值
         */
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
            }
        }

        /**
         * 加排他锁
         */
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        public boolean lock(int num, int user) {
            Lock lock = lockArray[num];
            if (lock.locked) {
                return false;
            }
            if (-1 == lock.parentIndex) {
                lock.locked = true;
                lock.lockedUser = user;
                return true;
            }
            // 加锁时, 需要在父结点链上加入记录
            Lock curParent = lockArray[lock.parentIndex];
            while (true) {
                curParent.lockedChildren.add(num);
                if (-1 == curParent.parentIndex) {
                    break;
                }
                curParent = lockArray[curParent.parentIndex];
            }
            // 锁
            lock.locked = true;
            lock.lockedUser = user;
            return true;
        }

        public boolean unlock(int num, int user) {
            Lock lock = lockArray[num];
            if (-1 != user && (null == lock.lockedUser || lock.lockedUser != user)) {
                return false;
            }
            if (-1 == lock.parentIndex) {
                lock.locked = false;
                lock.lockedUser = null;
                return true;
            }
            // 解锁时, 需要在父结点链上去掉记录
            Lock curParent = lockArray[lock.parentIndex];
            while (true) {
                curParent.lockedChildren.remove((Object) num);
                if (-1 == curParent.parentIndex) {
                    break;
                }
                curParent = lockArray[curParent.parentIndex];
            }
            // 解锁
            lock.locked = false;
            lock.lockedUser = null;
            return true;
        }

        public boolean upgrade(int num, int user) {
            Lock lock = lockArray[num];
            if (lock.locked) {
                return false;
            }
            // 至少有一个上锁状态的子孙节点, 可以是 任意 用户上锁的
            if (lock.lockedChildren.isEmpty()) {
                return false;
            }
            // 没有任何上锁的祖先节点
            boolean existsParentBeenLocked = false;
            int curParent = lock.parentIndex;
            while (-1 != curParent) {
                if (lockArray[curParent].locked) {
                    existsParentBeenLocked = true;
                    break;
                }
                curParent = lockArray[curParent].parentIndex;
            }
            if (existsParentBeenLocked) {
                return false;
            }
            // 该节点的所有子孙节点 解锁
            LinkedList<Integer> copyChildren = new LinkedList<>(lock.lockedChildren);
            for (Integer lockedChild : copyChildren) {
                this.unlock(lockedChild, -1);
            }
            // 调用加锁
            this.lock(num, user);
            return true;
        }

        public static class Lock {
            Integer lockedUser;
            // 记录子孙结点的锁, 这样upgrade时不要需要遍历所有子孙结点
            List<Integer> lockedChildren = new LinkedList<>();
            boolean locked;
            int parentIndex;

            @Override
            public String toString() {
                return """
                        {l: %s, u: %s, c: %s}""".formatted(locked, lockedUser, lockedChildren.stream().map(Object::toString).collect(Collectors.joining(",")));
            }
        }
    }
}
