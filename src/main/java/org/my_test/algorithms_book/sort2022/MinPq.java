package org.my_test.algorithms_book.sort2022;

/**
 * 获取最小值的优先队列
 */
@SuppressWarnings("DanglingJavadoc")
public class MinPq<T extends Comparable<T>> {
    /**
     * 二叉堆元素数
     */
    private int size;
    /**
     * 二叉堆
     */
    private final Object[] heap;

    public MinPq(int maxLength) {
        heap = new Object[maxLength];
    }

    /**
     * 加入元素
     */
    public void add(T t) {
        if (size == heap.length) {
            throw new RuntimeException("heap out index");
        }
        heap[size] = t;
        size++;
        swim(size - 1);
    }

    /**
     * 弹出最小值
     */
    public T delMin() {
        T min = elementData(0);
        heap[0] = heap[size - 1];
        heap[size - 1] = null;
        size--;
        this.sink(0);
        return min;
    }

    public T getMin() {
        if (size > 0)
            return elementData(0);
        return null;
    }

    /**
     * 上浮<br>
     * 书上是这样的: {@link #swimFromBook(int)}, 非常简洁
     */
    private void swim(int index) {
        T temp = elementData(index);
        int checkIndex = index;

        for (int parentIndex = (index - 1) / 2; parentIndex >= 0; parentIndex = (parentIndex - 1) / 2) {
            T parent = elementData(parentIndex);
            if (temp.compareTo(parent) > 0) {
                break;
            }
            heap[checkIndex] = parent;
            checkIndex = parentIndex;

            if (parentIndex == 0) { // 防止死循环: 0 = (0-1)/2
                break;
            }
        }

        heap[checkIndex] = temp;
    }

    @SuppressWarnings("unused")
    private void swimFromBook(int k) {
        /**
         * 和我的区别:
         * `k > 0`即表示有父结点! 不需要计算`父结点下标`
         * 这样也避免了 0 == (0-1)/2
         */
        while (k > 0 && greater((k - 1) / 2, k)) {
            exch((k - 1) / 2, k);
            k = (k - 1) / 2;
        }
    }

    /**
     * 下沉
     */
    @SuppressWarnings("SameParameterValue")
    private void sink(final int index) {
        T temp = elementData(index);
        int checkIndex = index;

        while (2 * checkIndex + 1 <= size - 1) {
            int leftIndex = 2 * checkIndex + 1;
            int rightIndex = leftIndex + 1;

            int minIndex = leftIndex;
            if (rightIndex <= size - 1 && elementData(rightIndex).compareTo(elementData(leftIndex)) < 0)
                minIndex = rightIndex;

            if (temp.compareTo(elementData(minIndex)) < 0)
                break;

            heap[checkIndex] = heap[minIndex];
            checkIndex = minIndex;
        }

        heap[checkIndex] = temp;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    private boolean greater(int a, int b) {
        return this.elementData(a).compareTo(this.elementData(b)) > 0;
    }

    private void exch(int a, int b) {
        T temp = this.elementData(a);
        heap[a] = heap[b];
        heap[b] = temp;
    }

    @SuppressWarnings("unchecked")
    T elementData(int index) {
        return (T) heap[index];
    }

    @Override
    public String toString() {
        return "{min: %s, size: %s}".formatted(this.heap[0], this.size);
    }
}
