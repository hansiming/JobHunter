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
 * 拉勾网有反爬取措施，只有Request Header一一对应，才能爬取,Cookie需要更新
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
                    header("Content-Length", "25").
                    header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8").
                    ignoreContentType(true).
                    header("Cookie", "LGUID=20170417155429-15f20b69-2343-11e7-8890-525400f775ce; user_trace_token=20170417155429-efbca5a3ab4b4b48ac345f364d21505e; JSESSIONID=36D702AE437655FDF081E9841E968C53; PRE_UTM=; PRE_HOST=; PRE_SITE=; PRE_LAND=https%3A%2F%2Fwww.lagou.com%2F; _gat=1; TG-TRACK-CODE=index_navigation; index_location_city=%E5%8C%97%E4%BA%AC; isCloseNotice=0; _ga=GA1.2.1712411254.1492415669; Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1492600979,1492693500,1492948309,1493186670; Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1493187242; LGSID=20170426140432-3774a532-2a46-11e7-801a-525400f775ce; LGRID=20170426141404-8c4a1f9a-2a47-11e7-b3a7-5254005c3644; SEARCH_ID=7ff092e41b3c4815b102e08cd5ced151").
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
