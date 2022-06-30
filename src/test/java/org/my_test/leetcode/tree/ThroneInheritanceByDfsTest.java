package org.my_test.leetcode.tree;

import org.testng.annotations.Test;

import java.util.List;

@SuppressWarnings("DanglingJavadoc")
public class ThroneInheritanceByDfsTest {


    @Test
    public void test() {
        // ["ThroneInheritance","birth","birth","birth","birth","birth","birth","getInheritanceOrder","death","getInheritanceOrder"]
        // [["king"],["king","andy"],["king","bob"],["king","catherine"],["andy","matthew"],["bob","alex"],["bob","asha"],[null],["bob"],[null]]
        // 预期结果
        // [null,null,null,null,null,null,null,["king","andy","matthew","bob","alex","asha","catherine"]
        // ,null,["king","andy","matthew","alex","asha","catherine"]]
        ThroneInheritanceByDfs house = new ThroneInheritanceByDfs("king");
        house.birth("king", "andy");
        house.birth("king", "bob");
        house.birth("king", "catherine");
        house.birth("andy", "matthew");
        house.birth("bob", "alex");
        house.birth("bob", "asha");

        /**
         *                     king
         *        andy          bob          catherine
         *        matthew    alex asha
         */

        List<String> inheritanceOrder = house.getInheritanceOrder();
        System.out.println(inheritanceOrder);

        house.death("bob");

        List<String> newInheritanceOrder = house.getInheritanceOrder();
        System.out.println(newInheritanceOrder);
    }
}