package com.train.dynamic.cartesian;

import java.util.function.BiConsumer;

import static com.train.dynamic.BitUtil.searchFirstIndexForBitInOne;

public class NNZGreyCodeGenerator implements CartesianGenerator {
    @Override
    public void generate(int n, BiConsumer<Integer, Integer> action) {
        int max = 1 << n;
        int i = 0;
        int greyCode = 0;
        while(i < max) {
            action.accept(n, greyCode);
            i++;
            int j = searchFirstIndexForBitInOne(i);
            greyCode ^= 1 << j;
        }
    }
}
