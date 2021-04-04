package com.oneaccount.test;

import com.oneaccount.test.exception.OneAccountException;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

public class JSLibLoader {
    private JSLibSearcher jsLibSearcher;
    private DocumentLoader documentLoader;

    public JSLibLoader(String jsLibResource) {
        jsLibSearcher = new JSLibSearcher(loadCurrentKnownJSLibs(jsLibResource));
        documentLoader = new DocumentLoader();
    }

    public Stream<String> loadJSLibs(String s) {
        Document doc;
        try {
            doc = documentLoader.load(s);
        } catch(Exception e) {
            System.out.println();
            return Stream.empty();
        }
        return doc
                .getElementsByTag("script")
                .parallelStream()
                .map(e -> e.attr("src"))
                .filter(Objects::nonNull)
                .map((sc) -> jsLibSearcher.getMatchedLib(sc))
                .filter(Objects::nonNull);
    }

    private Set<String> loadCurrentKnownJSLibs(String jsLibResource) {
        try {
            HashSet<String> res = new HashSet<>();
            InputStream is = getClass().getClassLoader().getResourceAsStream("jslibs.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            while (reader.ready()) {
                String line = reader.readLine();
                res.add(line);
            }

            return res;
        } catch (IOException e) {
            throw new OneAccountException(e);
        }
    }
}
