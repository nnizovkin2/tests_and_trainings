package com.train.linked;

public interface Stack<T> {
    T pop();
    void push(T item);
    T peek();
    boolean isEmpty();
}
