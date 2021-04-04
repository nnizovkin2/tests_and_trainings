package com.train.tree;

public class TreeWithStat {
    private int val;
    private TreeWithStat left;
    private TreeWithStat right;
    private int stat;

    public TreeWithStat(int val, int stat) {
        this(val, stat, null, null);
    }

    public TreeWithStat(int val, int stat, TreeWithStat left, TreeWithStat right) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.stat = stat;
    }

    public int getVal() {
        return val;
    }

    public TreeWithStat getLeft() {
        return left;
    }

    public TreeWithStat getRight() {
        return right;
    }

    public int getStat() {
        return stat;
    }
}
