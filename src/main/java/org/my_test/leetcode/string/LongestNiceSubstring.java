package org.my_test.leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <a href="https://leetcode.cn/problems/longest-nice-substring/">最长的美好子字符串</a>
 */
public class LongestNiceSubstring {
    public static void main(String[] args) {
        System.out.println(new LongestNiceSubstring().longestNiceSubstring("YazaAay"));
        ;
    }

    public String longestNiceSubstring(String s) {
        Map<Character, List<Integer>> charIndexMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            List<Integer> indexList = charIndexMap.computeIfAbsent(c, character -> new ArrayList<>());
            indexList.add(i);
        }

        int longestStart = 0;
        int longestEnd = s.length() - 1;
        int start = 0;
        int end = s.length() - 1;
        boolean checkStart = true;
        boolean checkEnd = false;

        while (start <= end) {
            char a = s.charAt(start);
            char a2 = Character.isLowerCase(a) ? Character.toUpperCase(a) : Character.toLowerCase(a);
            char z = s.charAt(end);
            char z2 = Character.isLowerCase(z) ? Character.toUpperCase(z) : Character.toLowerCase(z);

            if (checkStart) {
                List<Integer> indexList = charIndexMap.get(a2);
                boolean exist = indexList.stream().anyMatch(i -> i >= longestStart && i <= longestEnd);
                if (!exist) {
                    start++;
                    continue;
                }
                checkStart = false;
                checkEnd = true;
            }

        }

        //TODO 最长的美好子字符串
        return null;
    }

    private boolean checkStart(Map<Character, List<Integer>> charIndexMap, char c, int maxEnd) {
        List<Integer> indexList = charIndexMap.get(c);
        return indexList.stream().anyMatch(i -> i <= maxEnd);
    }
}
