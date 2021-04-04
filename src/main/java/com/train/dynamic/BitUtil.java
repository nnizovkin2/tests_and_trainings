package com.train.dynamic;

public class BitUtil {
    public static int searchFirstIndexForBitInOne(int i) {
        int j = 0;
        while (((1 << j) & i) == 0) {
            if(j == 31) {
                return -1;
            }
            j++;
        }
        return j;
    }

    public static int searchSecondIndexForBitInOne(int i) {
        int j = searchFirstIndexForBitInOne(i) + 1;
        if(j == -1) {
            return -1;
        }

        while (((1 << j) & i) == 0) {
            if(j == 31) {
                return -1;
            }
            j++;
        }
        return j;
    }
}
