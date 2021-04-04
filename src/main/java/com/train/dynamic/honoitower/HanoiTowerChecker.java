package com.train.dynamic.honoitower;

import com.train.TeConsumer;
import com.train.exception.WorkatoException;
import com.train.linked.LinkedStack;

public class HanoiTowerChecker implements TeConsumer<Integer, Integer, Boolean> {
    int callNumber = 0;
    LinkedStack<Integer>[] stacks;
    int n;

    public HanoiTowerChecker(int n) {
        this.n = n;
        callNumber = 0;
        stacks = new LinkedStack[3];
        stacks[0] = new LinkedStack<>();
        stacks[1] = new LinkedStack<>();
        stacks[2] = new LinkedStack<>();

        stacks[0].push(Integer.MAX_VALUE);
        stacks[1].push(Integer.MAX_VALUE);
        stacks[2].push(Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            stacks[0].push(n - i - 1);
        }
    }

    @Override
    public void accept(Integer moveFrom, Integer moveTo, Boolean moveToS) {
        callNumber++;
        if(moveToS) {
            System.out.println("moveDisk=" + moveFrom + ", " + "toDisk=" + moveTo);
        } else if(moveFrom == 0) {
            System.out.println("moveDisk=" + moveFrom + ", notToDisk=" + moveTo);
        } else {
            System.out.println("moveDisk=" + moveFrom + ", notToDisk=0");
        }

        System.out.println(Integer.toBinaryString(callNumber));

        int i0 = -1;
        int i1 = -1;
        for (int i = 0; i < stacks.length; i++) {
            Integer v = stacks[i].peek();
            if(moveFrom.equals(v)) {
                i0 = i;
            } else if(moveToS & moveTo.equals(v)) {
                i1 = i;
            } else if(!moveToS & moveFrom == 0 & !moveTo.equals(v)) {
                i1 = i;
            } else if(!moveToS & moveFrom != 0 & v != 0) {
                i1 = i;
            }
        }
        int moveDisk = stacks[i0].pop();

        if(stacks[i1].peek() > moveDisk) {
            stacks[i1].push(moveDisk);
        } else {
            throw new WorkatoException();
        }

        for (int i = 0; i < stacks.length; i++) {
            System.out.println(stacks[i]);
        }

        System.out.println();

        if(callNumber == (1 << n) - 1) {
            for (int i = 0; i < stacks.length; i++) {
                if(stacks[i].peek() != 0 && stacks[i].peek() != Integer.MAX_VALUE) {
                    throw new WorkatoException();
                }
            }
        }
    }
}
