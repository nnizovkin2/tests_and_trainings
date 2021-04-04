package com.train.dynamic;

import com.train.dynamic.honoitower.HanoiTowerGenerator;
import com.train.dynamic.honoitower.HanoiTowerChecker;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
//        double d = 0;
//        for (int i = 1; i < 100; i++) {
//            for (int j = 1; j < 100; j++) {
//                for (int k = 1; k < 100; k++) {
//                    for (int l = 1; l < 100; l++) {
////            d += 0.00000001;
//                        //0.5436890099970144
//                        d = (j - l * Math.sqrt(i)) / k;
//                        double res = Math.pow(d, 3) + Math.pow(d, 2) + d - 1;
//
//                        if (Math.abs(res) < 0.000001) {
//                            System.out.println(i + " " + l + " " + j + " " + k + " " + d);
//                        }
//                    }
//                }
//            }
//        }

//        int[] array = new int[250_000_000];
//        Random random = new Random();
//        for (int i = 0; i < array.length; i++) {
//            array[i] = random.nextInt();
//        }
//
//
//        long begin = System.nanoTime();
//
//        Writer writer = new BufferedWriter(new FileWriter("test.binary"));
//        for (int i = 0; i < array.length; i++) {
//            writer.write(array[i]);
//        }
//
//        writer.flush();
//        writer.close();
//        Arrays.sort(array);
//        qweenPermutations(12);
//        System.out.println((System.nanoTime() - begin) / 1_000_000);

//        System.out.println(findPath(new boolean[][] {
//                new boolean[]{true,  false, true,  true,  true},
//                new boolean[]{true,  false, true,  false, false},
//                new boolean[]{true,  true,  true,  true,  true},
//                new boolean[]{true,  true,  false, false, true},
//                new boolean[]{true,  true,  true,  false, true}}));
//        System.out.println(findMagicIndex(new int[]{-1, 0, 0, 4, 4}));

        HanoiTowerChecker checker = new HanoiTowerChecker(7);
        new HanoiTowerGenerator().generate(7, checker);
    }

    public static int findMagicIndex(int[] array) {
        if (array.length == 0) {
            return 0;
        }

        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                throw new IllegalArgumentException();
            }
        }

        return findMagicIndexR(array, 0, array.length - 1);
    }

    private static int findMagicIndexR(int[] array, int s, int e) {
        while (s <= e) {
            int m = (s + e) / 2;

            if (array[m] == m) {
                return m;
            }

            int i = m;
            if (array[m] > m) {
                while (i + 1 <= e && array[i] == array[i + 1]) {
                    i++;
                    if (array[i] == i) {
                        return i;
                    }
                }

                e = m - 1;
            } else {
                while (i - 1 >= s && array[i] == array[i - 1]) {
                    i--;
                    if (array[i] == i) {
                        return i;
                    }
                }

                s = m + 1;
            }
        }

        return -1;
    }

    public static void qweenPermutations(int size) {
        boolean[] vertical = new boolean[size];
        boolean[] leftDiag = new boolean[size * 2 - 1];
        boolean[] rightDiag = new boolean[size * 2 - 1];

        LinkedList<Pos> qweenForTest = new LinkedList<>();
        LinkedList<Pos> qweenOnTable = new LinkedList<>();

        for (int i = 0; i < size * 2 - 1; i++) {
            if (i < 8) {
                vertical[i] = false;
                qweenForTest.push(new Pos(i, 0));

            }

            leftDiag[i] = false;
            rightDiag[i] = false;
        }

        int res = 0;
        while (!qweenForTest.isEmpty()) {
            Pos p = qweenForTest.pop();
            while (!qweenOnTable.isEmpty() && p.y <= qweenOnTable.peek().y) {
                Pos p2 = qweenOnTable.pop();
                vertical[p2.x] = false;
                leftDiag[size - 1 - p2.y + p2.x] = false;
                rightDiag[p2.y + p2.x] = false;
            }

            if (!vertical[p.x] && !leftDiag[size - 1 - p.y + p.x] && !rightDiag[p.y + p.x]) {
                if (p.y == size - 1) {
                    res++;
//                    for (Iterator<Pos> it = qweenOnTable.descendingIterator(); it.hasNext();) {
//                        Pos pos = it.next();
//                        System.out.print("(" + pos.x + "," + pos.y + ") ");
//                    }
//
//                    System.out.print("(" + p.x + "," + p.y + ") ");
//                    System.out.println();
                } else {
                    for (int i = 0; i < size; i++) {
                        qweenForTest.push(new Pos(i, p.y + 1));
                    }
                }

                vertical[p.x] = true;
                leftDiag[size - 1 - p.y + p.x] = true;
                rightDiag[p.y + p.x] = true;
                qweenOnTable.push(p);
            }
        }

        System.out.println(res);
    }

    public boolean canStand(Pos pos, boolean[] horizontal, boolean[] leftDiag, boolean[] rightDiag) {
        return horizontal[pos.y] && leftDiag[7 - pos.y + pos.x] && rightDiag[pos.y + pos.x];

    }

    public static List<Index> findPath(boolean[][] matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException();
        }

        if (matrix.length == 0 || matrix[0].length == 0 || !matrix[0][0]) {
            return new LinkedList<>();
        }

        int iSize = matrix.length;
        int jSize = matrix[0].length;

        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i].length != jSize) {
                throw new IllegalArgumentException();
            }
        }

        LinkedList<Index> res = new LinkedList<>();

        res.add(new Index(0, 0));

        while (!res.isEmpty()) {
            Index index = res.peekLast();
            if (index.i + 1 == iSize && index.j + 1 == jSize) {
                return res;
            }

            if ((index.i + 1) < iSize && matrix[index.i + 1][index.j]) {
                res.add(new Index(index.i + 1, index.j));
                continue;
            }

            if ((index.j + 1) < jSize && matrix[index.i][index.j + 1]) {
                res.add(new Index(index.i, index.j + 1));
                continue;
            }

            matrix[index.i][index.j] = false;

            res.removeLast();
        }

        return new LinkedList<>();
    }

    private static class Index {
        int i;
        int j;

        public Index(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public String toString() {
            return "Index{" +
                    "i=" + i +
                    ", j=" + j +
                    '}';
        }
    }

    private static class Pos {
        int x;
        int y;

        public Pos(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
