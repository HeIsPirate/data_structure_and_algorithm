package org.my_test.leetcode.tree;

import java.util.*;

/**
 * <a href="https://leetcode.cn/problems/throne-inheritance/">王位继承顺序</a>
 * <br/>
 * <a href="https://leetcode.cn/problems/throne-inheritance/comments/608109">别人实现的</a>
 */
public class ThroneInheritanceByOthers {
    public static class ThroneInheritance {

        String kingName;

        /**
         * key: name
         * value: 后代列表
         */
        Map<String, List<String>> map = new HashMap<>();

        Set<String> deathSet = new HashSet<>();

        public ThroneInheritance(String kingName) {
            this.kingName = kingName;
            map.put(kingName, new LinkedList<>());
        }

        public void birth(String parentName, String childName) {
            map.get(parentName).add(childName);
            map.put(childName, new LinkedList<>());
        }

        public void death(String name) {
            deathSet.add(name);
        }

        public List<String> getInheritanceOrder() {
            List<String> result = new LinkedList<>();
            dfs(result, kingName);
            return result;
        }

        private void dfs(List<String> result, String name) {
            if (!deathSet.contains(name)) {
                result.add(name);
            }
            for (String subName : map.get(name)) {
                dfs(result, subName);
            }
        }
    }
}
