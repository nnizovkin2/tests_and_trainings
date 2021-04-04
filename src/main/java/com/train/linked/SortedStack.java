package com.train.linked;

import com.train.exception.AddNullElementException;

public class SortedStack<T extends Comparable<T>> implements Stack<T> {
    private LinkedStack<T> data = new LinkedStack<>();

    @Override
    public T pop() {
        return data.pop();
    }

    @Override
    public void push(T item) {
        if(item == null) {
            throw new AddNullElementException();
        }

        if(data.isEmpty() || item.compareTo(data.peek()) <= 0) {
            data.push(item);
            return;
        }

        LinkedStack<T> temp = new LinkedStack<>();
        while(!data.isEmpty() && item.compareTo(data.peek()) > 0) {
            temp.push(data.pop());
        }

        data.push(item);

        while(!temp.isEmpty()) {
            data.push(temp.pop());
        }
    }

    @Override
    public T peek() {
        return data.peek();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }
}
