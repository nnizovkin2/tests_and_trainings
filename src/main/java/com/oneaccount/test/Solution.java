package com.oneaccount.test;

//        1) Get a Bing result page for the search term
//        2) Extract main result links from the page
//        3) Download the respective pages and extract the names of javascript libraries used in them
//        4) Print top 5 most used libraries to standard output

import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Solution {
    private LinkLoader linkLoader = new LinkLoader();
    private JSLibLoader jsLibLoader = new JSLibLoader("jslibs.txt");

    private void solveTask() {
        Scanner in;
        in = new Scanner(System.in);
        String input = in.nextLine();
        long start = System.currentTimeMillis();
        linkLoader
                .getLinks("https://www.bing.com/search?q=" + input)
                .parallel()
                .flatMap(s -> jsLibLoader.loadJSLibs(s))
                .collect(groupingBy(Function.identity(), counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(5)
                .forEach(e -> System.out.println(e.toString()));
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void main(String[] args) {
        new Solution().solveTask();
    }
}
