package com.oneaccount.test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JSLibSearcher {
    List<String> libs;

    public JSLibSearcher(Set<String> libs) {
        this.libs = libs.stream()
                .map(String::toLowerCase)
                .sorted(Comparator.comparingInt(String::length).reversed())
                .collect(Collectors.toList());
    }

    public String getMatchedLib(String scriptSrc) {
        String scriptSrcLC = scriptSrc.toLowerCase();
        for (String lib : libs) {
            if(scriptSrcLC.contains(lib)) {
                System.out.println(scriptSrc);
                return lib;
            }
        }

        return null;
    }
}
