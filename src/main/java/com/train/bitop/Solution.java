package com.train.bitop;

public class Solution {
    static int clearBitsithrough0( int num, int i) {
        int mask = ~(- 1 >>> (31 - i));
        return num & mask;
    }

    public static void main(String[] args) {
//        System.out.println(clearBitsithrough0((int)Math.pow(4, 4), 3));
//        System.out.println(Integer.toBinaryString(insert(0b10000101001, 0b10011, 2, 6)));
//        System.out.println(Long.toBinaryString(Double.doubleToRawLongBits(1)));
//        System.out.println(Long.toBinaryString(Double.doubleToRawLongBits(2)));
//        System.out.println(getMaxSeq(0b1111));
//        task4(12);
//        task4(1);
        long sum = 0;
        long start = System.nanoTime();
        for (long i = 0; i < 100_000; i++) {
            for (long j = 0; j < 100_000; j++) {
                sum += i * j;
            }
        }
        System.out.println(sum);

        System.out.println((System.nanoTime() - start) / 1_000_000);
//        changeBits(8);
    }

    public long getMantissa(double d) {
        return (-1L >>> 12) & Double.doubleToRawLongBits(d);
    }

    public static void changeBits(int n) {
        int maskOd = 0b01010101_01010101_01010101_01010101;
        int maskEven = 0b10101010101010101010101010101010;
        System.out.println(Integer.toBinaryString(n));

        System.out.println(Integer.toBinaryString(((n >> 1) & maskOd) | ((n << 1) & maskEven)));
    }

    public static int insert(int N, int M, int i, int j) {
        return (N & (~(-1 >>> (31 - j)) | (-1 >>> (32 - i)))) | (M << i);
    }

//    public int getMaskForMaxNBit(int i) {
//        -1 >>> (31 - i);
//    }

    public static void task4(int n) {
        System.out.println(Integer.toBinaryString(n));
        int i = findFirstOneBit(n);
        if(i != 0) {
            System.out.println(Integer.toBinaryString(1 | n & ~(1 << i)));
        }

        int j = i;
        while((n & (1 << j)) != 0) {
            j++;
        }

        if(j != 31) {
            System.out.println(Integer.toBinaryString(n & ~(1 << i) | 1 << j));
        }
    }

    public static long multiply(int i, int j) {
        boolean isNegative = ((Integer.MIN_VALUE & i) ^ (Integer.MIN_VALUE & j)) == Integer.MIN_VALUE;
        i = i > 0 ? i : -i;
        j = j > 0 ? j : -j;
        if(i < j) {
            int temp = i;
            i = j;
            j = temp;
        }
        long res = 0;
        long sEl = i;
        while(j > 0) {
            res += (j & 1) == 1 ? sEl : 0;
            j >>= 1;
            sEl <<= 1;
        }

        return isNegative ? -res : res;
    }

    public static int findFirstOneBit(int n) {
        int i = 0;
        while(n != 0) {
            if((n & 1) == 1) {
                return i;
            }

            n >>>= 1;
            i++;
        }

        return -1;
    }

    public static int getBitNum(int n) {
        int res = 0;

        while(n != 0) {
            res += n & 1;
            n >>>= 1;
        }

        return res;
    }
    public static int getMaxSeq(int val) {
        int prev = 0;
        int cur = 0;
        int max = 0;

        int temp = val;
        boolean isBitSet = false;
        while(temp != 0 || !isBitSet) {
            int b = temp & 1;
            if(b == 0) {
                isBitSet = true;
                max = Math.max(prev, max);
                prev = cur + 1;
                cur = 0;
            } else {
                prev++;
                cur++;
            }

            temp >>>= 1;
        }

        return Math.max(max, prev);
    }

//    Пример:
//    Ввод: N = 10000000000, М = 10011, i = 2, j = 6
//    Вывод: N 10001001100
}
