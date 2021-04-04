package com.train.dynamic.cartesian;

import java.util.function.BiConsumer;

public class CartesianPrinter implements BiConsumer<Integer, Integer> {
    static String[] pref = init();

    static String[] init() {
        StringBuilder builder = new StringBuilder();

        String[] pref = new String[32];
        for (int i = 0; i < pref.length; i++) {
            pref[i] = builder.toString();
            builder.append(' ');
        }

        return pref;
    }

    @Override
    public void accept(Integer n, Integer val) {
        String s = Integer.toBinaryString(val);
        System.out.println(pref[n - s.length()] + s);
    }
}
