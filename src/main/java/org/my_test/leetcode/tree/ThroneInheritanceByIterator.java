package org.my_test.leetcode.tree;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/throne-inheritance/">王位继承顺序</a>
 * <ul>
 *     <li>人死了, 那么他就不在继承序列里了, 但是他的后代依然可以继承, 所以需要遍历死人 <br>(人死了, 不影响遍历, 只是不添加进继承序列里</li>
 *     <li>继承顺序: <ul>
 *         <li>长子继承制</li>
 *         <li>没有后代, 则死后由本人的弟弟们继承</li>
 *         <li>本人没后代且没弟弟, 则死后由本人的叔叔们继承</li>
 *     </ul></li>
 * </ul>
 */
public class ThroneInheritanceByIterator {

    public static class ThroneInheritance {
        private final Person king;
        private final Map<String, Person> personNameMap = new HashMap<>();

        public ThroneInheritance(String kingName) {
            king = new Person(kingName, personNameMap).setKing(true);
        }

        public void birth(String parentName, String childName) {
            Person parent = getPersonByName(parentName, king);
            Objects.requireNonNull(parent);
            if (null == parent.oldestChild) {
                parent.oldestChild = new Person(childName, personNameMap).setParent(parent);
                return;
            }
            Person curChild = parent.oldestChild;
            while (null != curChild.nextSibling) {
                curChild = curChild.nextSibling;
            }
            curChild.nextSibling = new Person(childName, personNameMap).setParent(parent);
        }

        public void death(String name) {
            Person person = getPersonByName(name, king);
            Objects.requireNonNull(person);
            person.isDead = true;
        }

        /**
         * 因为name和Person是一对一的关系!
         * 所有通过HashMap直接获取即可, 不需要遍历树
         */
        private Person getPersonByName(String name, @SuppressWarnings("unused") Person person) {
            return personNameMap.get(name);
        }

        /**
         * {@link #getPersonByName(String, Person)}
         */
        @SuppressWarnings("unused")
        @Deprecated
        private Person getPersonByNameOld(String name, Person person) {
            if (name.equals(person.name)) {
                return person;
            }
            if (null == person.oldestChild) {
                return null;
            }
            Person curChild = person.oldestChild;
            while (null != curChild) {
                Person matched = getPersonByName(name, curChild);
                if (null != matched) {
                    return matched;
                }
                curChild = curChild.nextSibling;
            }
            return null;
        }

        @SuppressWarnings("ConditionalBreakInInfiniteLoop")
        public List<String> getInheritanceOrder() {
            List<String> inheritanceOrderList = new LinkedList<>(); // 继承人顺序列表
            Set<String> inheritanceOrderSet = new HashSet<>(); // 继承人集合
            Set<String> traversedSet = new HashSet<>(); // 记录遍历过的人物, 包括已经死去的

            Person curPerson = king;
            while (true) {
                if (null == curPerson) {
                    break;
                }

                boolean hasTraversed = traversedSet.contains(curPerson.name);
                if (!hasTraversed) traversedSet.add(curPerson.name);

                // 没有死去, 且未添加进继承列表, 则添加
                if (!curPerson.isDead && !inheritanceOrderSet.contains(curPerson.name)) {
                    inheritanceOrderList.add(curPerson.name);
                    inheritanceOrderSet.add(curPerson.name);
                }

                // 只有当该人物未遍历过, 才考虑其大儿子
                // 因为如果遍历过, 肯定已经考虑过他的所有儿子了
                if (!hasTraversed) {
                    Person oldestChild = curPerson.oldestChild;
                    if (null != oldestChild) {
                        curPerson = oldestChild;
                        continue;
                    }
                }

                // 走到这, 有两种可能: 
                // 1. 本人没有后代, 则需要考虑本人的弟弟们了
                // 2. 本人已经遍历过, 再次遍历时, 也需要考虑本人的弟弟们了
                Person nextSibling = curPerson.nextSibling;
                if (null != nextSibling) {
                    curPerson = nextSibling;
                    continue;
                }

                // 走到这说明:
                // 本人的所有儿子和弟弟都已经考虑了, 因此需要考虑本人的`父亲的弟弟`了,
                // 如果父亲没有弟弟, 则需要考虑爷爷辈了 (递归向上过程!)
                //
                // 但是: 不能设置curPerson为`父亲的弟弟`, 比如king -> a -> b -> c, c压根就没有`父亲的弟弟`!
                //
                // 思考: Person有三个指针: 父亲, 大儿子, 弟弟
                // 为了完成递归向上的过程, 就需要设置curPerson = parent, 然后在下次循环中, 完成curPerson变为父亲的弟弟过程!!!
                // 通过curPerson = curPerson.oldestChild / curPerson.nextSibling / curPerson.parent 完成向下/向右/向上的递归
                //
                // 设置curPerson为父亲即可, 因为在下一个循环中, 父亲已经遍历过, 自动变为父亲的弟弟
                curPerson = curPerson.parent;
            }
            return inheritanceOrderList;
        }

        @SuppressWarnings("unused")
        public static class Person {
            private final String name;
            private boolean isDead;
            private boolean isKing;
            private Person parent; // 父母
            private Person nextSibling; // 弟弟
            private Person oldestChild; // 大儿子

            public Person(String name, Map<String, Person> personNameMap) {
                this.name = name;
                personNameMap.put(name, this);
            }

            public String getName() {
                return name;
            }

            public boolean isDead() {
                return isDead;
            }

            public Person setDead(boolean dead) {
                isDead = dead;
                return this;
            }

            public boolean isKing() {
                return isKing;
            }

            public Person setKing(boolean king) {
                isKing = king;
                return this;
            }

            public Person getParent() {
                return parent;
            }

            public Person setParent(Person parent) {
                this.parent = parent;
                return this;
            }

            public Person getNextSibling() {
                return nextSibling;
            }

            public Person setNextSibling(Person nextSibling) {
                this.nextSibling = nextSibling;
                return this;
            }

            public Person getOldestChild() {
                return oldestChild;
            }

            public Person setOldestChild(Person oldestChild) {
                this.oldestChild = oldestChild;
                return this;
            }
        }
    }
}
