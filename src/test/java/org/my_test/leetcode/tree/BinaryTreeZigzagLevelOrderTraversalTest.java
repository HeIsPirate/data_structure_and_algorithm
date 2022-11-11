package org.my_test.leetcode.tree;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.my_test.leetcode.util.TreeNode;
import org.my_test.leetcode.util.TreeUtil;
import org.testng.annotations.Test;

import java.util.List;

public class BinaryTreeZigzagLevelOrderTraversalTest {

    @SuppressWarnings("rawtypes")
    @Test
    public void one() throws JsonProcessingException {
        TreeNode root = TreeUtil.build("[3,9,20,null,null,15,7]");
        List<List<Integer>> result = new BinaryTreeZigzagLevelOrderTraversal().zigzagLevelOrder(root);
        List list = new ObjectMapper().readValue("[[3],[20,9],[15,7]]", List.class);
        assert list.equals(result);
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void two() throws JsonProcessingException {
        TreeNode root = TreeUtil.build("[1]");
        List<List<Integer>> result = new BinaryTreeZigzagLevelOrderTraversal().zigzagLevelOrder(root);
        List list = new ObjectMapper().readValue("[[1]]", List.class);
        assert list.equals(result);
    }

    @SuppressWarnings("rawtypes")
    @Test
    public void three() throws JsonProcessingException {
        TreeNode root = TreeUtil.build("[]");
        List<List<Integer>> result = new BinaryTreeZigzagLevelOrderTraversal().zigzagLevelOrder(root);
        List list = new ObjectMapper().readValue("[]", List.class);
        assert list.equals(result);
    }
}
