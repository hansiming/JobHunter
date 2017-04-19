package com.cszjo.jobhunter.model.experience;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Han on 2017/4/19.
 */
@Component
public class ExperienceKey {

    private final Logger LOOGER = LoggerFactory.getLogger(ExperienceKey.class);

    private final String LAGOU_LOCATION = "experience/lagou.properties";
    private final String JOB_51_LOCATION = "experience/job51.properties";
    private final String CHINA_HR_LOCATION = "experience/china_hr.properties";

    private Map<String, String> lagouExperience;
    private Map<String, String> job51Experience;
    private Map<String, String> chinaHrExperience;

    @PostConstruct
    public void init() {

        lagouExperience = initMap(LAGOU_LOCATION);
        job51Experience = initMap(JOB_51_LOCATION);
        chinaHrExperience = initMap(CHINA_HR_LOCATION);
    }

    private Map<String, String> initMap(String location) {

        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource(location);
        File file = new File(url.getFile());
        Map<String, String> map = Maps.newHashMap();

        try {

            Properties properties = new Properties();
            properties.load(new FileInputStream(file));
            Enumeration names = properties.propertyNames();
            while (names.hasMoreElements()) {
                String name = (String) names.nextElement();
                String value = properties.getProperty(name);
                map.put(name, value);
            }

            LOOGER.info("init a experience map, map = {}, location = {}", map, location);
        } catch (IOException e) {

            LOOGER.info("init a experience map has error, map = {}, location = {}", map, location);
        }

        return map;
    }

    public String getLagouExperience(String key) {
        return lagouExperience.get(key);
    }

    public String getJob51Experience(String key) {
        return job51Experience.get(key);
    }

    public String getChinaHrExperience(String key) {
        return chinaHrExperience.get(key);
    }
}
