package com.train.dynamic.cartesian;

import java.util.function.BiConsumer;

public class GreyCodeGenerator implements CartesianGenerator {
    public void generate(int n, BiConsumer<Integer, Integer> action) {
        boolean parityBit = false;
        int j = 0;
        int r = 0;

        while(j != n) {
            action.accept(n, r);
            parityBit = !parityBit;
            if(parityBit) {
                j = 0;
            } else {
                j = 1;
                while((1 << (j - 1) & r) == 0) {
                    j++;
                }
            }

            r ^= 1 << j;
        }
    }
}
