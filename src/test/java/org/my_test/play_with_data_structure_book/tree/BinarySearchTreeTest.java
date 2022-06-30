package org.my_test.play_with_data_structure_book.tree;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class BinarySearchTreeTest {
    @Test
    public void buildTree() {
        BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<>();

        List<Integer> list = Arrays.asList(62, 88, 58, 47, 35, 73, 51, 99, 37, 93);
        for (Integer integer : list) {
            tree.add(integer, integer);
        }

        List<BinarySearchTree.Node<Integer, Integer>> inorderList = tree.inorderTraversal();
        System.out.println(inorderList);

        tree.delete(47);
        // tree.delete(35);
        List<BinarySearchTree.Node<Integer, Integer>> afterDeleteList = tree.inorderTraversal();
        System.out.println(afterDeleteList);
    }
}
