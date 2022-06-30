package org.my_test.leetcode.sort;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <a href="https://leetcode.cn/problems/maximum-units-on-a-truck/">卡车上的最大单元数</a>
 */
@SuppressWarnings({"NewClassNamingConvention", "DanglingJavadoc"})
public class MaximumUnitsOnTruck {

    @Test
    public void test() {
        /**
         * 输入：boxTypes = [[5,10],[2,5],[4,7],[3,9]], truckSize = 10
         * 输出：91
         */
        int[][] boxTypes = new int[][]{
                new int[]{5, 10},
                new int[]{2, 5},
                new int[]{4, 7},
                new int[]{3, 9}
        };
        int maximumUnits = maximumUnits(boxTypes, 10);
        System.out.println(maximumUnits);
    }

    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Comparator<int[]> ascByBoxUnit = Comparator.comparing(boxType -> boxType[1]);
        Arrays.sort(boxTypes, ascByBoxUnit.reversed());

        int remainingSize = truckSize;
        int maxUnitSize = 0;

        for (int[] boxType : boxTypes) {
            int boxSize = boxType[0];
            int boxUnitSize = boxType[1];
            int validBoxSize = Math.min(remainingSize, boxSize);

            maxUnitSize += validBoxSize * boxUnitSize;
            remainingSize -= validBoxSize;

            if (remainingSize <= 0) {
                break;
            }
        }

        return maxUnitSize;
    }
}
