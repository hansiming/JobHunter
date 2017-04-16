package com.cszjo.jobhunter.clawer;

import com.cszjo.jobhunter.model.JobInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * 爬取51Job
 * Created by Han on 2017/4/16.
 */
public class Job51Clawer implements Callable<List<JobInfo>> {

    private final String url = "http://search.51job.com/list/%s,000000,0000,00,9,99,%s,2,%d.html";
    private int page;
    private String city;
    private String keyWord;
    private String experience;

    public Job51Clawer(int page, String city, String keyWord, String experience) {
        this.page = page;
        this.city = city;
        this.keyWord = keyWord;
        this.experience = experience;
    }

    @Override
    public List<JobInfo> call() {

        try {
            Document doc = Jsoup.connect(this.getUrl()).get();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getUrl() {
        return String.format(this.url, this.city, this.keyWord, this.page);
    }
}
