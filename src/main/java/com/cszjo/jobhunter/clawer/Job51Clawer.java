package com.cszjo.jobhunter.clawer;

import com.cszjo.jobhunter.model.CityKey51Job;
import com.cszjo.jobhunter.model.JobInfo;
import com.cszjo.jobhunter.utils.ClawerUtils;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * 爬取51Job
 * Created by Han on 2017/4/16.
 */
public class Job51Clawer implements Callable<List<JobInfo>> {

    private final Logger LOGGER = LoggerFactory.getLogger(Job51Clawer.class);

    private final String url = "http://search.51job.com/list/%s,000000,0000,00,9,99,%s,2,%d.html";
    private int page;
    private String city;
    private String keyWord;
    private String experience;
    private CityKey51Job cityKey51Job;

    public Job51Clawer(int page, String city, String keyWord, String experience, CityKey51Job cityKey51Job) {
        this.page = page;
        this.city = city;
        this.keyWord = keyWord;
        this.experience = experience;
        this.cityKey51Job = cityKey51Job;
    }

    @Override
    public List<JobInfo> call() {

        try {
            Document doc = Jsoup.connect(this.getUrl()).get();
            Element resultList = doc.getElementById("resultList");
            Elements jobs = resultList.select(".el");
            List<JobInfo> jobInfos = Lists.newArrayList();
            boolean isFirst = false;

            for (Element job : jobs) {

                if (!isFirst) {
                    isFirst = true;
                    continue;
                }

                JobInfo jobInfo = new JobInfo();

                jobInfo.setJobName(ClawerUtils.clawerAttrBySelectQuery(".t1 span a","title", job));
                jobInfo.setUrl(ClawerUtils.clawerAttrBySelectQuery(".t1 span a","href", job));
                jobInfo.setCompanyName(ClawerUtils.clawerAttrBySelectQuery(".t2 a","title", job));
                jobInfo.setAddressName(ClawerUtils.clawerHtmlBySelectQuery(".t3", job));
                jobInfo.setMaxMoney(ClawerUtils.clawerHtmlBySelectQuery(".t4", job));
                jobInfo.setCreateDate(ClawerUtils.clawerHtmlBySelectQuery(".t5", job));
                jobInfo.setTaskId(1);

                LOGGER.info("get a job info from 51 job, job info = {}", jobInfo);
                jobInfos.add(jobInfo);
            }

            return jobInfos;
        } catch (IOException e) {

            LOGGER.error("get a job info form 51 job create a error, error = {}, url = {}", e.getMessage(), this.getUrl());
        }
        return null;
    }

    private String getUrl() {
        return String.format(this.url, this.cityKey51Job.getCityKey(this.city), this.keyWord, this.page);
    }
}
