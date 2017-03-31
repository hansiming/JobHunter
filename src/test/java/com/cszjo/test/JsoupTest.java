package com.cszjo.test;

import com.google.common.collect.Maps;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Han on 2017/3/5.
 */
public class JsoupTest {

    @Test
    public void jsoupTest() throws Exception {
        String html = "http://www.chinahr.com/sou/?city=27,312&page=2";
        Document doc = Jsoup.connect(html).get();
        print(doc.outerHtml());
        Elements jobList = doc.select(".jobList");
        for(Element e : jobList) {
            print(e.select("span.e1").attr("title"));
        }

        Map<String, List<String>> map = Maps.newHashMap();

    }

    public void print(String s) {
        System.out.println(s);
    }
}
