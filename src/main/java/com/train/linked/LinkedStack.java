package com.train.linked;

import com.train.exception.AddNullElementException;
import com.train.exception.EmptyStackException;

public class LinkedStack<T> implements Stack<T> {
    private static class StackNode<T> {
        private final T data;
        private StackNode<T> next;

        public StackNode(T data) {
            this.data = data;
        }
    }

    private StackNode<T> top;

    public T pop() {
        if (top == null) throw new EmptyStackException();
        T item = top.data;
        top = top.next;
        return item;
    }

    public void push(T item) {
        if(item == null) {
            throw new AddNullElementException();
        }

        StackNode<T> t = new StackNode<>(item);
        t.next = top;
        top = t;
    }

    public T peek() {
        if (top == null) throw new EmptyStackException();
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public String toString() {
        StackNode<T> node = top;
        StringBuilder builder = new StringBuilder();
        while(node != null) {
            builder.append(node.data);
            builder.append(' ');
            node = node.next;
        }

        return builder.toString();
    }
}
