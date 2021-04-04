package com.oneaccount.test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.oneaccount.test.exception.OneAccountException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class DocumentLoader {
    public Document loadWithJSGeneration(String s) {
        try {
            // capture rendered page
            WebClient webClient = new WebClient();
            HtmlPage myPage = webClient.getPage(s);

            // convert page to generated HTML and convert to document
            return Jsoup.parse(myPage.asXml());
        } catch (IOException e) {
            throw new OneAccountException(e);
        }
    }

    public Document load(String s) {
        try {
            return Jsoup.connect(s).get();
        } catch (IOException e) {
            throw new OneAccountException(e);
        }
    }
}
