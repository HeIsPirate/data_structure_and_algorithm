package org.my_test.algorithms_book.sort;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShellSort<T extends Comparable<T>> extends AbstractSort<T> {
    @Override
    public void sort(T[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap = gap / 2) {
            System.out.printf("-------------------------------------------gap: %s-------------------------------------------%n", gap);
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                while (j - gap >= 0 && arr[j].compareTo(arr[j - gap]) < 0) {
                    System.out.printf("j: %s, j-gap: %s%n", j, (j - gap));
                    //插入排序采用交换法
                    swap(arr, j, j - gap);
                    j -= gap;
                }
                System.out.println(Arrays.stream(arr).map(String::valueOf).collect(Collectors.joining(",")));
            }
        }
    }

    public void sortByUsingMyWay(T[] arr) {
        // 只需要是一个递减且最后一位是1的序列
        int[] gap_arr = new int[]{6, 3, 1};
        for (int gap : gap_arr) {
            // 每个子数组起始下标, 起始下标最大不会超过gap
            for (int first = 0; first < gap; first++) {
                // 确定gap, first后, 就形成了一个个子数组: first, first+gap, first+gap*2, first+gap*3, first+gap*4 ~~~
                // 对子数组进行插入排序
                // 先是插入排序的外循环
                //     i: 子数组的下标
                //     first + gap * i: 子数组元素在arr中的实际下标. 取数据时需要使用实际下标
                for (int i = 1; (first + gap * i) < arr.length; i++) {
                    T temp = arr[first + gap * i];
                    // 再是插入排序的内循环
                    // j: 子数组左半已排序部分的下标, 下标从大到小遍历
                    for (int j = i - 1; j >= 0 && (first + gap * j) < arr.length; j--) {
                        if (less(temp, arr[first + gap * j])) {
                            exch(arr, first + gap * (j + 1), first + gap * j);
                        }
                    }
                }
            }
        }
    }

    public void sortByUsingMyWay_New(T[] arr) {
        int[] gap_arr = new int[]{6, 3, 1};
        for (int gap : gap_arr) {
            for (int i = 0; i < arr.length; i++) {
                // 跳过, 是因为小于gap的, 都是子数组的首个元素
                if (i < gap) {
                    continue;
                }
                // temp: 待插入到"对应子数组的有序部分"的元素
                // l=i-gap: "对应数组的有序部分"的最大下标
                // l依次减去gap, 即子数组有序部分的下标
                // 当l<0时, 表示已经遍历到了子数组的第一个元素
                T temp = arr[i];
                int l = i - gap;
                while (l >= 0) {
                    T each = arr[l];
                    if (less(temp, each)) {
                        exch(arr, l + gap, l);
                        l -= gap;
                    } else {
                        break;
                    }
                }
            }
        }
    }

    public void sortFromBook(T[] arr) {
        int N = arr.length;
        int h = 1;
        while (h < N / 3) {
            // h = 1, 4, 13, 40 ~~~
            // 这里循环结束后的 h=13
            h = 3 * h + 1;
        }

        // h变化: 13 -> 4 -> 1
        while (h >= 1) {
            System.out.printf("-------------------------------------------h: %s-------------------------------------------%n", h);
            for (int i = h; i < N; i++) {
                // 0 -> 4
                // 1 -> 5
                // 2 -> 6
                // 3 -> 7
                // 4 -> 8 此时和第一行联上了 0 4 8 12 ~~~ 是一个数组, 这里是按下标遍历的, 不是按数组一个一个遍历, 所以不太容易看出来
                // ...
                // 8 -> 12
                // ...
                // 12 -> 16
                // ...
                for (int j = i; print(i, j, j - h) && j >= h && less(arr[j], arr[j - h]); j = j - h) {
                    System.out.println(String.format("%-15s%-15s", "arr[j]= " + arr[j], "arr[j-h]= " + arr[j - h]));
                    exch(arr, j, j - h);
                }
            }
            System.out.println(Arrays.stream(arr).map(String::valueOf).collect(Collectors.joining(",")));
            h = h / 3;
        }
    }

    private static boolean print(int a, int b, int c) {
        System.out.println(String.format("%-15s%-15s%-15s", "i= " + a, "j= " + b, "j-h=" + c));
        return true;
    }

    public void swap(T[] arr, int a, int b) {
        T temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        char[] before1 = "SHELLSORTEXAMPLE".toCharArray();
        Character[] before = new Character[before1.length];
        for (int i = 0; i < before1.length; i++) {
            before[i] = before1[i];
        }

        System.out.println(Stream.of(before).map(String::valueOf).collect(Collectors.joining(",")));

        // new ShellSort().sort(before);
        // new ShellSort().sortFromBook(before);
        // new ShellSort().sortByUsingMyWay(before);
        new ShellSort<Character>().sortByUsingMyWay_New(before);

        System.out.println(Arrays.stream(before).map(String::valueOf).collect(Collectors.joining(",")));
    }
}
