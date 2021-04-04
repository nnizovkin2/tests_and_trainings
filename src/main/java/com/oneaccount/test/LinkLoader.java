package com.oneaccount.test;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;

import java.util.stream.Stream;

public class LinkLoader {
    private final DocumentLoader documentLoader = new DocumentLoader();
    public Stream<String> getLinks(String url) {
        Document doc = documentLoader.loadWithJSGeneration(url);
        return doc
                .select("a[href]")
                .stream()
                .filter((e) -> {
                    String text = e.text();
                    return !StringUtils.isNumeric(text);
                })
                .map((e) -> e.attr("abs:href"))
                .filter(StringUtils::isNotEmpty)
                .distinct();
    }
}
