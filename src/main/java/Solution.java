//Problem 1:. Given an array of integers,  please arrange them in wave format
//        For example
//        Input Arr= { 5, 11, 43, 1, 2, 13, 7, 24, 18 }
//
//        Output integers should be arranged to represent the following pattern
//
//        a1>a2<a3>a4<a5>a6<a7>a8<a9
//
//Problem 2
//        Please codify the input string ( ahabcjahbdcjbjj )  as   (a3b3c2dh2j4 )
//
//        Problem 3
//
//        Print the mirror of a Binary Tree –
//
//        input-
//        5
//        /  \
//        4    6
//        /     / \
//        3     2   7

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class Solution {
//    public static int[] waveFormat(int[] array) {
//        if (array == null) {
//            return null;
//        }
//
//        int[] res = new int[array.length];
//        Arrays.sort(array);
//
//        for (int i = 0; i < array.length / 2; i++) {
//            res[2 * i] = array[2 * i + 1];
//            res[2 * i + 1] = array[2 * i];
//        }
//
//        if (array.length % 2 == 1) {
//            res[array.length - 1] = array[array.length - 1];
//        }
//
//        return res;
//    }
//
//    public static String codifyString(String str) {
//        if (str == null) {
//            return null;
//        }
//
//        StringBuilder res = new StringBuilder();
//        char[] array = str.toCharArray();
//        Map<Character, Integer> resMap = new TreeMap<>();
//
//        for (char c : array) {
//            if (resMap.containsKey(c)) {
//                resMap.put(c, resMap.get(c) + 1);
//            } else {
//                resMap.put(c, 1);
//            }
//        }
//
//        for (Map.Entry<Character, Integer> e : resMap.entrySet()) {
//            res.append(e.getKey());
//            res.append(e.getValue());
//        }
//
//        return res.toString();
//    }
//

    public static boolean isDistanceLessOrEqualsOne(String s1, String s2) {
        if(s1 == null || s2 == null) {
            return false;
        }

        char[] a1 = s1.toCharArray();
        char[] a2 = s2.toCharArray();

        if(a1.length - 1 > a2.length || a1.length + 1 < a2.length) {
            return false;
        }

        boolean hasError = false;
        int delta = 0;
        for (int i = 0; i < a1.length; i++) {
            if(a1[i + delta] != a2[i]) {
                if(hasError) {
                    return false;
                }

                hasError = true;

                if(a1.length < a2.length) {
                    delta = -1;
                }

                if(a1.length > a2.length) {
                    delta = 1;
                    i--;
                }
            }
        }

        return true;
    }

    public static boolean isPolyndromPermutation(String s) {
        if(s == null) {
            return false;
        }

        Set<Character> set = new HashSet<>();

        for (char c : s.toCharArray()) {
            if(c == ' ') {
                continue;
            }

            if(set.contains(c)) {
                set.remove(c);
            } else {
                set.add(c);
            }
        }

        return set.size() < 2;
    }

    static void solveDiaff() {
        Map<Integer, List<Pair<Integer, Integer>>> map = new HashMap<>();
        for (int i = 0; i <= 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                int cube = i * i * i + j * j * j;
                if(map.containsKey(cube)) {
                    List<Pair<Integer, Integer>> res = map.get(cube);
                    for (Pair<Integer, Integer> re : res) {
                        System.out.println(i + " " + j + " " + re.getLeft() + " " + re.getRight());
                    }
                    res.add(Pair.of(i, j));
                } else {
                    map.put(cube, new ArrayList<>(List.of(Pair.of(i, j))));
                }
            }

        }
    }

    public static <T> String reverseTree(Tree<T> t) {
        StringBuilder res = new StringBuilder();
        recReverseTree(t, res);
        return res.toString();
    }

    private static <T> void recReverseTree(Tree<T> t, StringBuilder sb) {
        if(t == null) {
            sb.append("null");
            return;
        }

        sb.append(t.val);
        sb.append('(');
        recReverseTree(t.right, sb);
        sb.append(' ');
        recReverseTree(t.left, sb);
        sb.append(')');
    }

    public static void main(String[] args) {
        System.out.println(isDistanceLessOrEqualsOne("pale", "pales"));
        System.out.println(isDistanceLessOrEqualsOne("pale", "pale"));
        System.out.println(isDistanceLessOrEqualsOne("pale", "bale"));
        System.out.println(isDistanceLessOrEqualsOne("ple", "pale"));
        System.out.println(isDistanceLessOrEqualsOne("pale", "bake"));
        System.out.println("-------------");
        System.out.println(isPolyndromPermutation("atco cta"));

//        solveDiaff();
//        int[] res = waveFormat(new int[]{5, 11, 43, 1, 2, 13, 7, 24, 18});
//
//        for (int re : res) {
//            System.out.println(re + " ");
//        }
//
//        System.out.println(codifyString("ahabcjahbdcjbjj"));
//
//        System.out.println(reverseTree(new Tree<>(5, new Tree<>(4, new Tree<>(3, null, null), null),
//                new Tree<>(6, new Tree<>(2, null, null), new Tree<>(7, null, null)))));
    }

    private static class Tree<T> {
        T val;
        Tree<T> left;
        Tree<T> right;

        public Tree(T val, Tree<T> left, Tree<T> right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
