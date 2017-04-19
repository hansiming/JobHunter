package com.cszjo.jobhunter.clawer;

import com.cszjo.jobhunter.model.JobInfo;
import com.cszjo.jobhunter.model.LagouJobInfo;
import com.cszjo.jobhunter.model.experience.ExperienceKey;
import com.cszjo.jobhunter.utils.ParseUtils;
import com.google.common.collect.Maps;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * 拉勾网有反爬取措施，只有Request Header一一对应，才能爬取
 * Created by Han on 2017/4/11.
 */
public class LagouClawer implements Callable<List<JobInfo>> {

    private final String URL = "https://www.lagou.com/jobs/positionAjax.json?gj=%s&px=default&city=%s&needAddtionalResult=false";
    private int page;
    private String city;
    private String keyWord;
    private String experience;
    private ExperienceKey experienceKey;

    public LagouClawer(int page, String city, String keyWord, String experience, ExperienceKey experienceKey) {
        this.page = page;
        this.city = city;
        this.keyWord = keyWord;
        this.experience = experience;
        this.experienceKey = experienceKey;
    }

    @Override
    public List<JobInfo> call() {

        Map<String, String> data = Maps.newHashMap();
        data.put("first", "true");
        data.put("pn", String.valueOf(this.page));
        data.put("kd", this.keyWord);

        try {
            final String url = this.getUrl();
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

            //parse to LagouJobInfos
            List<LagouJobInfo> lagouJobInfos = ParseUtils.result2LagouJobInfoList(res.body());
            //parse to jobinfo
            return ParseUtils.lagouJobInfoList2JobInfoList(lagouJobInfos);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String getUrl() {
        return String.format(this.URL, this.experienceKey.getLagouExperience(this.experience), this.city);
    }
}
