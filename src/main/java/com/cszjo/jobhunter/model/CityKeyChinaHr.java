package com.cszjo.jobhunter.model;

import com.google.common.collect.Maps;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

/**
 * Created by Han on 2017/4/17.
 */
@Component
public class CityKeyChinaHr {

    private final Logger LOGGER = LoggerFactory.getLogger(CityKeyChinaHr.class);

    private Map<String, String> cityKeyMap;
    private final String JOB_CITY = "city/chinahr_city.html";

    @PostConstruct
    public void init() {

        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource(JOB_CITY);
        File file = new File(url.getFile());
        cityKeyMap = Maps.newHashMap();

        try {

            Document doc = Jsoup.parse(file, "utf-8");
            Elements elements = doc.select("li");
            for (Element e : elements) {
                String key = e.text();
                String value = e.attr("data-id");
                cityKeyMap.put(key, value);
            }
            LOGGER.info("load city key from china hr, maps = {}", cityKeyMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCityKey(String city) {
        return cityKeyMap.get(city);
    }
}
