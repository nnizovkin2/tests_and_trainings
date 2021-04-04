package com.train.dynamic.cartesian;

import java.util.function.BiConsumer;

public interface CartesianGenerator {
    void generate(int n, BiConsumer<Integer, Integer> action);
}
