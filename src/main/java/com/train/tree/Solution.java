package com.train.tree;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        Tree t = new Tree(1,
                new Tree(1,
                        new Tree(1), new Tree(1)),
                new Tree(1,
                        new Tree(1), new Tree(1)));
        Tree t4 = new Tree(1,
                new Tree(1,
                        new Tree(2), new Tree(3)),
                new Tree(1));
        Tree subtreeT4 = new Tree(1,
                new Tree(3), new Tree(3));

        System.out.println(isIsomorfic(t4, subtreeT4));
//        System.out.println(findNumberOfPaths(t, 2));
//
//        Tree t2 = new Tree(1,
//                new Tree(2,
//                        new Tree(4), new Tree(5)),
//                new Tree(3,
//                        new Tree(6), new Tree(7)));
//        TreeWithStat t3 = new TreeWithStat(1, 7,
//                new TreeWithStat(2, 3,
//                        new TreeWithStat(4, 1), new TreeWithStat(5, 1)),
//                new TreeWithStat(3, 3,
//                        new TreeWithStat(6, 1), new TreeWithStat(7, 1)));


        int i = 0;
//        while(i < 100) {
//            System.out.println(getRandomNode(t3).getVal());
//            i++;
//        }
//        System.out.println(isIsomorfic(t, t2));
//
//        printTreePermutations(t2);
//        bfs(t);
//
//
//        System.out.println(findYoungestCommonAncestor(t, 4, 7).getEl());
//        System.out.println(findYoungestCommonAncestor(t, 0, 7) == null);
//        System.out.println(findYoungestCommonAncestor(t, 1, 2).getEl());
//        System.out.println(findYoungestCommonAncestor(t, 1, 4).getEl());
//        System.out.println(findYoungestCommonAncestor(t, 2, 4).getEl());
//        System.out.println(findYoungestCommonAncestor(t, 2, 5).getEl());
//        System.out.println(findYoungestCommonAncestor(t, 2, 6).getEl());
    }

    public static int findNumberOfPaths(Tree root, int sum) {
        Map<Integer, Integer> sums = new HashMap<>();
        sums.put(0, 1);

        return findNumberOfPathsR(root, sum, sums, 0);
    }

    public static int findNumberOfPathsR(Tree root, int sum, Map<Integer, Integer> sums, int currentVal) {
        if(root == null) {
            return 0;
        }

        currentVal += root.getVal();

        int res = 0;
        int diff = currentVal - sum;
        res += sums.getOrDefault(diff, 0);

        sums.put(currentVal, sums.getOrDefault(currentVal, 0) + 1);

        res += findNumberOfPathsR(root.getLeft(), sum, sums, currentVal);
        res += findNumberOfPathsR(root.getRight(), sum, sums, currentVal);

        if(sums.get(currentVal) == 1) {
            sums.remove(currentVal);
        } else {
            sums.put(currentVal, sums.get(currentVal) - 1);
        }

        return res;
    }

    public static TreeWithStat getRandomNode(TreeWithStat root) {
        if(root == null) {
            return null;
        }

        int rand = (int)(Math.random() * root.getStat() + 1);

        return getRandomNodeR(root, rand, 0);
    }

    public static TreeWithStat getRandomNodeR(TreeWithStat root, int rand, int delta) {
        int pos = delta + 1;
        if(root.getLeft() != null) {
            pos += root.getLeft().getStat();
        }

        if(rand == pos) {
            return root;
        } else if(rand < pos) {
            return getRandomNodeR(root.getLeft(), rand, delta);
        } else {
            return getRandomNodeR(root.getRight(), rand, pos);
        }
    }

    static void bfs(Tree root) {
        if(root == null) {
            return;
        }

        List<Tree> levelNodes = new ArrayList<>();
        levelNodes.add(root);

        while(!levelNodes.isEmpty()) {
            List<Tree> nextLevelNodes = new ArrayList<>();
            for (Tree node : levelNodes) {
                System.out.print(node.getVal() + " ");

                if(node.getLeft() != null) {
                    nextLevelNodes.add(node.getLeft());
                }

                if(node.getRight() != null) {
                    nextLevelNodes.add(node.getRight());
                }
            }
            levelNodes = nextLevelNodes;
            System.out.println();
        }
    }

    public static void printTreePermutations(Tree root) {
        if(root == null) {
            return;
        }

        printTreePermutationsR(root, "", new Tree[0]);
    }

    private static void printTreePermutationsR(Tree root, String s, Tree[] nodes) {
        s += " " + root.getVal();
        if(nodes.length == 0 && isLeaf(root)) {
            System.out.println(s);
            return;
        }

        if(root.getLeft() != null) {
            nodes = ArrayUtils.add(nodes, root.getLeft());
        }

        if(root.getRight() != null) {
            nodes = ArrayUtils.add(nodes, root.getRight());
        }

        for (int i = 0; i < nodes.length; i++) {
            Tree node = nodes[i];
            printTreePermutationsR(node, s, ArrayUtils.remove(nodes, i));
        }
    }

    public static boolean isLeaf(Tree node) {
        return node.getLeft() == null && node.getRight() == null;
    }

    public static boolean isIsomorfic(Tree tree, Tree subtree) {
        if(tree == null || subtree == null) {
            return false;
        }

        return isIsomorficR(tree, subtree)
                || isIsomorficR(tree.getLeft(), subtree)
                || isIsomorficR(tree.getRight(), subtree);
    }

    public static boolean isIsomorficR(Tree tree, Tree subtree) {
        if(subtree == null) {
            return true;
        }

        if(tree == null) {
            return false;
        }

        if(tree.getVal() != subtree.getVal()) {
            return false;
        }

        return (isIsomorficR(tree.getLeft(), subtree.getLeft()) && isIsomorficR(tree.getRight(), subtree.getRight()))
                || (isIsomorficR(tree.getRight(), subtree.getLeft()) && isIsomorficR(tree.getLeft(), subtree.getRight()));
    }

    public static Tree findYoungestCommonAncestor(Tree root, int val1, int val2) {
        if(root == null) {
            return null;
        }

        if(!containsNode(root, val1) || !containsNode(root, val2)) {
            return null;
        }

        if(!isLeftToRight(root, val1, val2)) {
            int temp = val1;
            val1 = val2;
            val2 = temp;
        }

        return findYoungestCommonAncestorR(root, val1, val2);
    }

    private static Tree findYoungestCommonAncestorR(Tree root, int val1, int val2) {
        if(root.getVal() == val1 || root.getVal() == val2) {
            return root;
        }

        if(root.getLeft() != null && containsNode(root.getLeft(), val2)) {
            return findYoungestCommonAncestorR(root.getLeft(), val1, val2);
        }

        if(root.getRight() != null && containsNode(root.getRight(), val1)) {
            return findYoungestCommonAncestorR(root.getRight(), val1, val2);
        }

        return root;
    }

    private static Boolean isLeftToRight(Tree root, int val1, int val2) {
        if(root == null) {
            return null;
        }

        Boolean b = isLeftToRight(root.getLeft(), val1, val2);

        if(b == null) {
            if(root.getVal() == val1) {
                return true;
            }

            if(root.getVal() == val2) {
                return false;
            }
        } else {
            return b;
        }

        return isLeftToRight(root.getRight(), val1, val2);
    }

    public static boolean containsNode(Tree root, int val) {
        if(root == null) {
           return false;
        }

        if(root.getVal() == val) {
            return true;
        }

        return containsNode(root.getLeft(), val) || containsNode(root.getRight(), val);
    }
}
