package com.cszjo.test;

import com.cszjo.jobhunter.clawer.Job51Clawer;
import com.cszjo.jobhunter.clawer.LagouClawer;
import com.cszjo.jobhunter.model.JobInfo;
import com.google.common.collect.Maps;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.FutureTask;

/**
 * Created by Han on 2017/4/11.
 */
public class LagouTest {

    @Test
    public void lagouTest() throws Exception {

        Map<String, String> data = Maps.newHashMap();
        data.put("first", "true");
        data.put("pn", "1");
        data.put("kd", "java");

        final String url = "https://www.lagou.com/jobs/positionAjax.json?gj=应届毕业生&px=default&city=%E6%88%90%E9%83%BD&needAddtionalResult=false";
        Connection.Response res = Jsoup.connect(url).
                header("Accept", "application/json, text/javascript, */*; q=0.01").
                header("Accept-Encoding", "gzip, deflate, br").
                header("Accept-Language", "zh-CN,zh;q=0.8").
                header("Connection", "keep-alive").
                header("Content-Length", "22").
                header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8").
                ignoreContentType(true).
                header("Cookie", "user_trace_token=20160821185106-85f17b62695544629f059c2855aca300; LGUID=20160821185106-29797195-678d-11e6-ac36-525400f775ce; JSESSIONID=21DC10B903BCA12CA259E64927DA8B98; TG-TRACK-CODE=search_code; SEARCH_ID=b2a85ebc9f8b4e2ca335aa2c15bd4f74").
                header("Host", "www.lagou.com").
                header("Origin", "https://www.lagou.com").
                header("Referer", "https://www.lagou.com/jobs/list_php?city=%E6%88%90%E9%83%BD&cl=false&fromSearch=true&labelWords=&suginput=").
                header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36").
                header("X-Anit-Forge-Code", "0").
                header("X-Anit-Forge-Token", "None").
                header("X-Requested-With", "XMLHttpRequest").
                data(data).method(Connection.Method.POST).execute();
        System.out.println(res.body());
    }

    @Test
    public void lagouJobInfoTest() throws Exception {

        FutureTask<List<JobInfo>> future = new FutureTask<List<JobInfo>>(new LagouClawer( 1,"成都", "Java", ""));
        new Thread(future).start();
        System.out.println(future.get());
        Thread.sleep(3000);
    }
}
