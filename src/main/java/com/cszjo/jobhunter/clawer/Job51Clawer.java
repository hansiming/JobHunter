package com.cszjo.jobhunter.clawer;

/**
 * 爬取51Job
 * Created by Han on 2017/4/16.
 */
public class Job51Clawer implements Runnable {

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
    public void run() {


    }

    private String getUrl() {
        return String.format(this.url, this.city, this.keyWord, this.page);
    }
}
