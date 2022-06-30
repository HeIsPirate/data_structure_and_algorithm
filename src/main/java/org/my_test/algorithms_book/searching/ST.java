package org.my_test.algorithms_book.searching;

public interface ST<K, V> {
    void put(K k, V v);

    V get(K k);

    void delete(K k);
}
