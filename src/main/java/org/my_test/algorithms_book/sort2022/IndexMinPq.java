package org.my_test.algorithms_book.sort2022;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 最小`索引优先队列`<br>
 * <a href="https://algs4.cs.princeton.edu/24pq/IndexMinPQ.java.html">书上的IndexMinPQ.java</a>, 不同于书上 (使用`qp`, `keys`两个数组), 这里使用map做反向索引
 */
@SuppressWarnings("DanglingJavadoc")
public class IndexMinPq<T extends Comparable<T>> {
    /**
     * 元素个数
     */
    private int size;
    /**
     * 二叉堆, 不允许重复元素
     */
    private final Object[] heap;
    /**
     * 元素索引Map, 键: 元素, 值: 堆下标 (一对一的, 所以二叉堆中不允许重复元素)<br>
     */
    private final Map<Object, Integer> elements;

    public IndexMinPq(int maxLength) {
        this.heap = new Object[maxLength];
        this.elements = new HashMap<>(maxLength, 1);
    }

    public void insert(T item) {
        // 不允许重复
        if (this.elements.containsKey(item))
            return;

        heap[this.size] = Objects.requireNonNull(item);
        this.size++;
        swim(this.size - 1);
    }

    public void change(int index, T item) {
        T t = this.elementData(index);
        heap[index] = Objects.requireNonNull(item);
        this.elements.remove(t);

        /**
         * 维护二叉堆
         * 原先我是这样实现的, 判断应该执行sink还是swim:
         * <pre>
         *     int compare = item.compareTo(t);
         *     if (compare > 0) {
         *         sink(index);
         *     } else if (compare < 0) {
         *         swim(index);
         *     }
         * </pre>
         * 但sink和swim方法自带校验, 因此按照书上代码简化了, 最终效果一样 (感觉我实现的效果会稍高些)
         */
        sink(index);
        swim(index);
    }

    public void changeTo(T from, T to) {
        Integer index = this.elements.get(from);
        Objects.requireNonNull(index);
        this.change(index, to);
    }

    boolean contains(int k) {
        //TODO 这个api干什么用的?
        return false;
    }

    public void delete(T item) {
        Integer index = this.elements.get(item);
        Objects.requireNonNull(index);
        this.elements.remove(item);
        this.heap[index] = this.heap[this.size - 1];
        this.size--;
        this.sink(index);
    }

    public void tryDelete(T item) {
        Integer index = this.elements.get(item);
        if (null == index)
            return;
        this.delete(item);
    }

    public T delMin() {
        if (this.size == 0) return null;
        T min = this.elementData(0);
        this.elements.remove(min);

        this.heap[0] = this.elementData(this.size - 1);
        this.size--;
        this.sink(0);
        return min;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void sink(int index) {
        int maxIndex = this.size - 1;
        T temp = this.elementData(index);
        int nowIndex = index;

        while (2 * nowIndex + 1 <= maxIndex) {
            int smallerChildIndex = 2 * nowIndex + 1;
            if (smallerChildIndex < maxIndex && greaterThan(smallerChildIndex, smallerChildIndex + 1)) {
                smallerChildIndex++;
            }

            if (smallerThan(temp, smallerChildIndex)) break;

            heap[nowIndex] = heap[smallerChildIndex];
            elements.put(heap[nowIndex], nowIndex);
            nowIndex = smallerChildIndex;
        }

        heap[nowIndex] = temp;
        elements.put(temp, nowIndex);
    }

    private void swim(int index) {
        T temp = this.elementData(index);
        int nowIndex = index;

        while (nowIndex > 0) {
            int parent = (nowIndex - 1) / 2;
            if (smallerThan(parent, temp)) break;

            Object parentValue = heap[parent];
            heap[nowIndex] = parentValue;
            elements.put(parentValue, nowIndex);
            nowIndex = parent;
        }

        heap[nowIndex] = temp;
        elements.put(temp, nowIndex);
    }

    private boolean smallerThan(T a, int b) {
        return a.compareTo(this.elementData(b)) < 0;
    }

    private boolean smallerThan(int a, T b) {
        return this.elementData(a).compareTo(b) < 0;
    }

    private boolean greaterThan(int a, int b) {
        return this.elementData(a).compareTo(this.elementData(b)) > 0;
    }

    @SuppressWarnings("unchecked")
    private T elementData(int index) {
        return (T) heap[index];
    }

    @Override
    public String toString() {
        return "{heap: " + Arrays.toString(this.heap) + ", elements: " + this.elements + "}";
    }
}