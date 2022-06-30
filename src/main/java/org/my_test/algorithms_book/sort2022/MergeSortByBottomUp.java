package org.my_test.algorithms_book.sort2022;

import org.my_test.algorithms_book.sort.AbstractSort;

import java.util.Arrays;

/**
 * 归并排序 (自底向上)
 */
@SuppressWarnings({"DanglingJavadoc", "JavadocBlankLines"})
public class MergeSortByBottomUp<T extends Comparable<T>> extends AbstractSort<T> {
    @Override
    public void sort(T[] arr) {
        int maxIndex = arr.length - 1;

        /**
         * 1. 先两两合并
         * 2. 再四四合并
         * 3. 再八八合并
         * 4. ~~~~
         * 5. 直到超过数组长度
         *
         * 间距每次乘2
         */
        for (int i = 2; ; i = i * 2) {
            int j = 0;
            /**
             * 每间距i合并
             */
            while (j <= maxIndex) {
                int lo = j;
                int split = Math.min(j + i / 2, maxIndex); // 计算子数组split位置
                int hi = Math.min(maxIndex, j + i - 1);

                System.out.printf("i: %s, 合并: [%s-%s]与[%s-%s]%n", i, lo, split - 1, split, hi);
                this.merge(arr, lo, split, hi);

                j += i; // 间距i
            }
            /**
             * 终结条件, 最后一次间距i大于数组长度时, 数组已全部排序
             * 比如maxIndex=26, 间距i每次乘2, 当i=32时, 将[0至15]与[16至26]归并, 结果是一个完整有序数组
             */
            if (i > maxIndex)
                break;
        }
    }


    /**
     * 合并两子数组, 分别是: [lo 至 split-1], [split 至 hi] <br>
     * (包含lo, hi下标)
     *
     * @param lo    左子数组起始下标
     * @param split 用来分隔子数组的, 它是右子数组起始下标
     * @param hi    右子数组结束下标
     */
    private void merge(T[] arr, int lo, int split, int hi) {
        if (hi <= lo) return;

        /**
         * 归并需要辅助索引, 因为可能没有空余空间, 比如[3,2]与[1,4]归并
         * 需要完整copy一份, 这样下标才能对应
         */
        T[] copy = Arrays.copyOf(arr, arr.length);

        int i = lo; // `左子数组`索引
        // int split = lo + (hi - lo) / 2 + 1; // 无法计算出, 因为它与上次间距有关, 不一定在中间位置! (左右子数组长度不一定一样) 比如最后一次合并为完整数组时有问题
        int j = split; // `右子数组`索引
        int index = lo; // 归并后索引

        while (i < split || j <= hi) {
            T iVal = i < split ? copy[i] : null;
            T jVal = j <= hi ? copy[j] : null;

            if (jVal == null || (null != iVal && less(iVal, jVal))) {
                i++;
                arr[index] = iVal;
            } else {
                j++;
                arr[index] = jVal;
            }
            index++;
        }
    }

    /**
     * 当数组长度为27时:
     *
     * i: 2, 合并: [0-0]与[1-1]
     * i: 2, 合并: [2-2]与[3-3]
     * i: 2, 合并: [4-4]与[5-5]
     * i: 2, 合并: [6-6]与[7-7]
     * i: 2, 合并: [8-8]与[9-9]
     * i: 2, 合并: [10-10]与[11-11]
     * i: 2, 合并: [12-12]与[13-13]
     * i: 2, 合并: [14-14]与[15-15]
     * i: 2, 合并: [16-16]与[17-17]
     * i: 2, 合并: [18-18]与[19-19]
     * i: 2, 合并: [20-20]与[21-21]
     * i: 2, 合并: [22-22]与[23-23]
     * i: 2, 合并: [24-24]与[25-25]
     * i: 2, 合并: [26-25]与[26-26]
     *
     * i: 4, 合并: [0-1]与[2-3]
     * i: 4, 合并: [4-5]与[6-7]
     * i: 4, 合并: [8-9]与[10-11]
     * i: 4, 合并: [12-13]与[14-15]
     * i: 4, 合并: [16-17]与[18-19]
     * i: 4, 合并: [20-21]与[22-23]
     * i: 4, 合并: [24-25]与[26-26]
     *
     * i: 8, 合并: [0-3]与[4-7]
     * i: 8, 合并: [8-11]与[12-15]
     * i: 8, 合并: [16-19]与[20-23]
     * i: 8, 合并: [24-25]与[26-26]
     *
     * i: 16, 合并: [0-7]与[8-15]
     * i: 16, 合并: [16-23]与[24-26]
     *
     * i: 32, 合并: [0-15]与[16-26]       // 比如这里, lo=0, hi=26, split是16, split不在中间位置
     */
}
