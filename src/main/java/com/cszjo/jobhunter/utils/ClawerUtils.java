package com.cszjo.jobhunter.utils;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Han on 2017/4/17.
 */
public class ClawerUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClawerUtils.class);

    private ClawerUtils() {
        //防止实例化
    }

    public static String clawerAttrBySelectQuery(String query, String attrKey, Element element) {

        if (element == null) {
            LOGGER.error("element is null, query = {}, element = {}, attrKey = {}", query, element, attrKey);
            return "";
        }

        if (StringUtils.isEmpty(query)) {
            LOGGER.error("query is empty, query = {}, element = {}, attrKey = {}", query, element, attrKey);
            return "";
        }

        if (StringUtils.isEmpty(attrKey)) {
            LOGGER.error("attrKey is empty, query = {}, element = {}, attrKey = {}", query, element, attrKey);
            return "";
        }

        Elements elements = element.select(query);
        for (Element ele : elements) {
            return ele.attr(attrKey);
        }

        LOGGER.error("can`t find elements from query and element, query = {}, element = {}, attrKey = {}", query, element, attrKey);

        return "";
    }

    public static String clawerHtmlBySelectQuery(String query, Element element) {

        if (element == null) {
            LOGGER.error("element is null, query = {}, element = {}", query, element);
            return "";
        }

        if (StringUtils.isEmpty(query)) {
            LOGGER.error("clawer html by select query error, query = {}, element = {}", query, element);
            return "";
        }

        Elements elements = element.select(query);

        for (Element ele : elements) {
            return ele.html();
        }

        LOGGER.error("can`t find elements from query and element, query = {}, element = {}", query, element);

        return "";
    }
}
