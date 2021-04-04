package com.train.linked;

import com.train.exception.AddNullElementException;

public class QueueOnStackImpl<T> implements Queue<T> {
    private Stack<T> first = new LinkedStack<>();
    private Stack<T> second = new LinkedStack<>();

    @Override
    public T рор() {
        rebuild();

        return second.pop();
    }

    @Override
    public void push(T item) {
        if(item == null) {
            throw new AddNullElementException();
        }

        first.push(item);
    }

    @Override
    public T peek() {
        rebuild();

        return second.peek();
    }

    @Override
    public boolean isEmpty() {
        return first.isEmpty() && second.isEmpty();
    }

    private void rebuild() {
        if(!second.isEmpty()) {
            return;
        }

        while(!first.isEmpty()) {
            second.push(first.pop());
        }
    }
}
