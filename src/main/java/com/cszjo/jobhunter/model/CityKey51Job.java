package com.cszjo.jobhunter.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

/**
 * Created by Han on 2017/4/12.
 */
@Component
public class CityKey51Job {

    private Map<String, String> cityKeyMap;
    private final String JOB_CITY = "city/51jobcity.html";

    @PostConstruct
    public void init() {

        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource(JOB_CITY);
        File file = new File(url.getFile());

        try {

            Document doc = Jsoup.parse(file, "utf-8");
            Elements elements = doc.select(".cityKey");
            for (Element e : elements) {
                String key = e.text();
                String value = e.attr("data-value");
                cityKeyMap.put(key, value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCityKey(String cityName) {
        return cityKeyMap.get(cityName);
    }
}
