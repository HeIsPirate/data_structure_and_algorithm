package org.my_test.algorithms_book.sort;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InsertSort<T extends Comparable<T>> extends AbstractSort<T> {
    @Override
    public void sort(T[] arr) {
        int length = arr.length;
        // 外循环
        for (int i = 1; i < length; i++) {
            T temp = arr[i];
            // 内循环, 索引下标从大到小遍历左半部分
            for (int j = i - 1; j >= 0; j--) {
                T each = arr[j];
                // 需要移动位置
                if (temp.compareTo(each) < 0) {
                    // each原位置是: j, 向右移动一位, 新位置: j+1
                    // 移动后, j位置可以清空的, 只是为了速度未清空
                    arr[j + 1] = each;
                    // 特殊情况, 到头了
                    if (j == 0) {
                        arr[0] = temp;
                    }
                }
                // 遍历到: 已经插入正确的顺序, 可以提前终止
                if (temp.compareTo(each) >= 0) {
                    arr[j + 1] = temp;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Random random = new Random();

        Integer[] before = new Integer[100];
        IntStream.range(0, 100).forEach(i -> before[i] = random.nextInt(100));
        System.out.println(Arrays.stream(before).map(String::valueOf).collect(Collectors.joining(",")));

        new InsertSort<Integer>().sort(before);
        System.out.println(Arrays.stream(before).map(String::valueOf).collect(Collectors.joining(",")));
    }
}
