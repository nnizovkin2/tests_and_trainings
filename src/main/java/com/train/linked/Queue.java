package com.train.linked;

public interface Queue<T> {
    T рор();
    void push(T item);
    T peek();
    boolean isEmpty();
}
