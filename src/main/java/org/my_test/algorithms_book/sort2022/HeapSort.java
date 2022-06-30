package org.my_test.algorithms_book.sort2022;

import org.my_test.algorithms_book.sort.AbstractSort;

public class HeapSort<T extends Comparable<T>> extends AbstractSort<T> {
    @Override
    public void sort(T[] arr) {
        buildHeap(arr);
    }

    /**
     * 构建大顶堆
     */
    private void buildHeap(T[] arr) {
        int maxParentNodeIndex = (arr.length / 2) - 1;

        for (int i = maxParentNodeIndex; i >= 0; i--)
            sink(arr, i, arr.length - 1);


        int i = arr.length - 1; // 二叉堆最大下标
        while (i > 0) {
            exch(arr, i, 0);
            i--;
            sink(arr, 0, i);
        }
    }

    /**
     * @param arr          数组
     * @param sinkIndex    下沉元素下标
     * @param heapMaxIndex 堆排序时, 二叉堆的大小是不断缩小的
     */
    private void sink(final T[] arr, final int sinkIndex, final int heapMaxIndex) {
        T sinkValueTemp = arr[sinkIndex]; // 下沉元素的值
        int p = sinkIndex; // 父结点下标
        // while (p <= (heapMaxIndex - 1) / 2) { // 不应该用`除`, (0-1)/2 => 0
        while (2 * p + 1 <= heapMaxIndex) {
            int j = p * 2 + 1; // 左子结点下标
            int k = j + 1; // 右子节点下标

            if (k <= heapMaxIndex && great(arr[k], arr[j])) j = k; // 如果右结点存在, 比较左右结点大小, 这样j=左右结点中较大的那个下标

            if (great(sinkValueTemp, arr[j])) break;
            else {
                arr[p] = arr[j]; // j的值较大, 因此上浮到父结点, 但是此时没有更新j位置值, 是为了防止需要继续下沉导致重复赋值
                p = j; // p值变为j, 继续尝试下沉 (但是j位置暂时还是废值)
            }
        }

        arr[p] = sinkValueTemp; // 结束完赋值
    }

    /**
     *
     */
    @SuppressWarnings("unused")
    private void sinkByExchange(final T[] arr, final int sinkIndex) {
        int i = sinkIndex; // 父结点下标
        while (i <= (arr.length / 2) - 1) {
            int j = i * 2 + 1; // 左子结点下标
            int k = j + 1; // 右子节点下标

            if (k < arr.length && great(arr[k], arr[j])) j = k;

            if (great(arr[i], arr[j])) break;
            else {
                exch(arr, i, j); // 交换
                i = j;
            }
        }
    }
}
