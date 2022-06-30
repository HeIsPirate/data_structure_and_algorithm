package org.my_test.leetcode.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <p><a href="https://leetcode.cn/problems/throne-inheritance/">王位继承顺序</a></p>
 * <p>通过`深度优先遍历`实现</p>
 */
public class ThroneInheritanceByDfs {
    private final Person king;
    private final Map<String, Person> personNameMap = new HashMap<>();

    public ThroneInheritanceByDfs(String kingName) {
        king = new Person(kingName, null, personNameMap);
    }

    public void birth(String parentName, String childName) {
        Person parent = personNameMap.get(parentName);
        Person newChild = new Person(childName, parent, personNameMap);
        if (null == parent.youngestChild) {
            parent.oldestChild = parent.youngestChild = newChild;
            return;
        }
        parent.youngestChild.nextSibling = newChild;
        parent.youngestChild = newChild;
    }

    public void death(String name) {
        personNameMap.get(name).isDead = true;
    }

    public List<String> getInheritanceOrder() {
        LinkedList<String> order = new LinkedList<>();
        dfs(king, order);
        return order;
    }

    private void dfs(Person parent, List<String> inheritanceOrder) {
        if (!parent.isDead)
            inheritanceOrder.add(parent.name);

        Person child = parent.oldestChild;
        while (null != child) {
            dfs(child, inheritanceOrder);
            child = child.nextSibling;
        }
    }

    private static class Person {
        private final String name;
        private boolean isDead;
        private boolean isKing;
        @SuppressWarnings("FieldCanBeLocal")
        private final Person parent; // 父母
        private Person nextSibling; // 弟弟
        private Person oldestChild; // 最大儿子
        private Person youngestChild; // 最小儿子

        public Person(String name, Person parent, Map<String, Person> personNameMap) {
            this.name = name;
            this.parent = parent;
            personNameMap.put(name, this);
        }
    }
}
