package org.my_test.leetcode.tree;

import org.testng.annotations.Test;

import java.util.List;

public class ThroneInheritanceTest {


    @Test
    public void test() {
        // ["ThroneInheritance","birth","birth","birth","birth","birth","birth","getInheritanceOrder","death","getInheritanceOrder"]
        //[["king"],["king","andy"],["king","bob"],["king","catherine"],["andy","matthew"],["bob","alex"],["bob","asha"],[null],["bob"],[null]]
        ThroneInheritanceByIterator.ThroneInheritance house = new ThroneInheritanceByIterator.ThroneInheritance("king");
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

    @Test
    public void test2() {
        // ["ThroneInheritance","birth","death","birth","getInheritanceOrder","birth","death","getInheritanceOrder"]
        //[["king"],["king","clyde"],["king"],["clyde","shannon"],[null],["shannon","scott"],["clyde"],[null]]
        ThroneInheritanceByIterator.ThroneInheritance house = new ThroneInheritanceByIterator.ThroneInheritance("king");
        house.birth("king", "clyde");
        house.death("king");
        house.birth("clyde", "shannon");

        List<String> inheritanceOrder = house.getInheritanceOrder();
        System.out.println(inheritanceOrder);

        house.birth("shannon", "scott");
        house.death("clyde");

        /**
         *               king (x)
         *               clyde
         *               shannon (x)
         *               scott
         */

        List<String> newInheritanceOrder = house.getInheritanceOrder();
        System.out.println(newInheritanceOrder);
    }

    @Test
    public void test3() {
        // ["ThroneInheritance","getInheritanceOrder","birth","birth","birth","birth","getInheritanceOrder","birth","getInheritanceOrder"]
        // [["king"],[null],["king","clyde"],["clyde","shannon"],["shannon","scott"],["king","keith"],[null],["clyde","joseph"],[null]]
        // 预期结果:
        // [null,["king"],null,null,null,null,["king","clyde","shannon","scott","keith"],null,["king","clyde","shannon","scott","joseph","keith"]]
        ThroneInheritanceByIterator.ThroneInheritance house = new ThroneInheritanceByIterator.ThroneInheritance("king");

        List<String> inheritanceOrder = house.getInheritanceOrder();
        System.out.println(inheritanceOrder);

        house.birth("king", "clyde");
        house.birth("clyde", "shannon");
        house.birth("shannon", "scott");
        house.birth("king", "keith");

        inheritanceOrder = house.getInheritanceOrder();
        System.out.println(inheritanceOrder);

        house.birth("clyde", "joseph");

        /**
         *                            king
         *       clyde                keith
         *       shannon
         *       scott
         */

        inheritanceOrder = house.getInheritanceOrder();
        System.out.println(inheritanceOrder);
    }
}