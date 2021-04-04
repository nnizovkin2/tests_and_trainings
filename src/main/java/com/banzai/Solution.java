package com.banzai;

import java.util.IdentityHashMap;
import java.util.LinkedList;

public class Solution {
    private static void leftRightVisiting(Tree route) {
        if(route == null) {
            return;
        }

        LinkedList<Tree> stack = new LinkedList<>();
        stack.push(route);
        while(!stack.isEmpty()) {
            Tree el = stack.pop();
            System.out.print(el.val);
            if(el.right != null) {
                stack.push(el.right);
            }

            if(el.left != null) {
                stack.push(el.left);
            }
        }
    }

    public static void main(String[] args) {
        Tree route = new Tree(1,
                new Tree(2,
                        new Tree(3, null, null),
                        new Tree(4, null, null)),
                new Tree(5,
                        null,
                        new Tree(6,
                                new Tree(8, null, null),
                                new Tree(7, null, null))));
        route.validate();
        leftRightVisiting(route);

        Tree route2 = new Tree(1, null, null);
        route2.left = route2;
//        route2.validate();
    }

    private static class Tree {
        int val;
        Tree left;
        Tree right;

        public Tree(int val, Tree left, Tree right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public void validate() {
            IdentityHashMap<Tree, Object> nodes = new IdentityHashMap<>();
            if(!validateR(nodes, this)) {
                throw new IllegalArgumentException("Tree shouldn't nave circle dependencies");
            }
        }

        private boolean validateR(IdentityHashMap<Tree, Object> nodes, Tree route) {
            if(route == null) {
                return true;
            }

            if(nodes.containsKey(route)) {
                return false;
            }

            nodes.put(route, null);

            return validateR(nodes, route.left) && validateR(nodes, route.right);
        }
    }
}
