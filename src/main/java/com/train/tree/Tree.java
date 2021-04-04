package com.train.tree;

public class Tree {
    private int val;
    private Tree left;
    private Tree right;

    public Tree(int val) {
        this(val, null, null);
    }

    public Tree(int val, Tree left, Tree right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public int getVal() {
        return val;
    }

    public Tree getLeft() {
        return left;
    }

    public Tree getRight() {
        return right;
    }
}

