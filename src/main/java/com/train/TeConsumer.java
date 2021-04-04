package com.train;

@FunctionalInterface
public interface TeConsumer<First, Second, Third> {
    void accept(First f, Second s, Third t);
}
