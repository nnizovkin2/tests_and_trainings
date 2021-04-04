package com.train.linked;

public class Solution {
    public static void main(String[] args) {
//        Stack<Integer> s = new SortedStack<>();
//        s.push(1);
//        s.push(3);
//        s.push(0);
//        s.push(2);
//        s.push(4);
//        s.push(5);
//
//        while(!s.isEmpty()) {
//            System.out.println(s.pop());
//        }
//        System.out.println("-----------------");
//
        Queue<Integer> q = new QueueOnStackImpl<>();
        q.push(1);
        q.push(2);
        q.push(3);
        System.out.println(q.peek());
        System.out.println(q.рор());
        System.out.println(q.рор());
        System.out.println(q.рор());
        q.push(4);
        q.push(5);
        q.push(6);
        q.push(7);
        q.push(8);
        System.out.println(q.рор());
        System.out.println(q.рор());
        System.out.println(q.рор());
        System.out.println(q.рор());
        System.out.println(q.рор());
    }
}
