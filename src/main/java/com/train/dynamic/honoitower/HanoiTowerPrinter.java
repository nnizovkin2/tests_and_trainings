package com.train.dynamic.honoitower;

import com.train.TeConsumer;

public class HanoiTowerPrinter implements TeConsumer<Integer, Integer, Boolean> {
    @Override
    public void accept(Integer f, Integer s, Boolean t) {
        System.out.println("moveDisk=" + f + ", " + (t ? "to" : "notTo") + "Disk=" + s);
    }
}
