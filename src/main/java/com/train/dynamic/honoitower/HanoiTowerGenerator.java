package com.train.dynamic.honoitower;

import com.train.TeConsumer;
import com.train.dynamic.BitUtil;

public class HanoiTowerGenerator {
    public void generate(int n, TeConsumer<Integer, Integer, Boolean> action) {
        int i = 1;
        int max = 1 << n;

        while (i < max) {
            int i1 = BitUtil.searchFirstIndexForBitInOne(i);
            int i2 = BitUtil.searchSecondIndexForBitInOne(i);
            if(i2 == -1) {
                action.accept(i1, Integer.MAX_VALUE, true);
            } else {
                action.accept(i1, i2, ((i2 - i1) & 1) == 1);
            }
            i++;
        }
    }
}
