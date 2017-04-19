package com.cszjo.jobhunter.clawer;

import com.cszjo.jobhunter.model.city.CityKeyChinaHr;
import com.cszjo.jobhunter.model.JobInfo;
import com.cszjo.jobhunter.model.experience.ExperienceKey;
import com.cszjo.jobhunter.utils.ClawerUtils;
import com.google.common.collect.Lists;
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
 * Created by Han on 2017/4/17.
 */
public class ChinaHrClawer implements Callable<List<JobInfo>> {

    private final Logger LOGGER = LoggerFactory.getLogger(ChinaHrClawer.class);

    private final String url = "http://www.chinahr.com/sou/?orderField=relate&keyword=%s&city=%s&page=%d&workAge=%s";
    private int page;
    private String city;
    private String keyWord;
    private String experience;
    private CityKeyChinaHr cityKeyChinaHr;
    private ExperienceKey experienceKey;

    public ChinaHrClawer(int page, String city, String keyWord, String experience, CityKeyChinaHr cityKeyChinaHr, ExperienceKey experienceKey) {
        this.page = page;
        this.city = city;
        this.keyWord = keyWord;
        this.experience = experience;
        this.cityKeyChinaHr = cityKeyChinaHr;
        this.experienceKey = experienceKey;
    }

    @Override
    public List<JobInfo> call() {

        try {

            Document doc = Jsoup.connect(this.getUrl()).get();
            Elements jobs = doc.select(".jobList");
            List<JobInfo> jobInfos = Lists.newArrayList();

            for (Element job : jobs) {

                JobInfo jobInfo = new JobInfo();

                jobInfo.setUrl(job.attr("data-url"));
                jobInfo.setJobName(ClawerUtils.clawerHtmlBySelectQuery(".l1 .e1 a", job));
                jobInfo.setCreateDate(ClawerUtils.clawerHtmlBySelectQuery(".l1 .e2", job));
                jobInfo.setCompanyName(ClawerUtils.clawerHtmlBySelectQuery(".l1 .e3 a", job));
                jobInfo.setMaxMoney(ClawerUtils.clawerHtmlBySelectQuery(".l2 .e2", job));
                jobInfo.setAddressName(this.city);
                jobInfo.setTaskId(2);

                LOGGER.info("get a job info from china hr, job info = {}", jobInfo);
                jobInfos.add(jobInfo);
            }

            return jobInfos;
        } catch (IOException e) {
            LOGGER.error("get a job info form china hr create a error, error = {}, url = {}", e.getMessage(), this.getUrl());
        }
        return null;
    }

    private String getUrl() {
        return String.format(this.url, this.keyWord, this.cityKeyChinaHr.getCityKey(this.city), this.page, this.experienceKey.getChinaHrExperience(this.experience));
    }
}
