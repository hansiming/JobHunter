package com.cszjo.jobhunter.clawer;

import com.cszjo.jobhunter.model.ClawerTask;
import com.cszjo.jobhunter.model.JobInfo;
import com.cszjo.jobhunter.model.Template;
import com.cszjo.jobhunter.utils.ClawerUtils;
import com.google.common.collect.Lists;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * 自定义爬取
 * Created by Han on 2017/4/20.
 */
public class CustomClawer implements Callable<List<JobInfo>> {

    private final Logger LOGGER = LoggerFactory.getLogger(CustomClawer.class);

    private final String HREF = "href";
    private Template template;
    private ClawerTask task;
    private int page;

    public CustomClawer(Template template, ClawerTask task, int page) {

        this.template = template;
        this.task = task;
        this.page = page;
    }

    @Override
    public List<JobInfo> call() throws Exception {


        Document doc = Jsoup.connect(this.getUrl()).get();
        LOGGER.info("custom clawer start, template = {}, task = {}, page = {}, url = {}", template, task, page, this.getUrl());
        Elements jobInfoList = doc.select(this.template.getJobList());
        List<JobInfo> jobList = Lists.newArrayList();
        int count = 0;

        for(Element e : jobInfoList) {

            if(count == 0) {
                count++;
                continue;
            }

            JobInfo jobInfo = new JobInfo();

            jobInfo.setJobName(ClawerUtils.clawerHtmlBySelectQuery(this.template.getJobName(), e));
            jobInfo.setAddressName(ClawerUtils.clawerHtmlBySelectQuery(this.template.getJobAddress(), e));
            jobInfo.setMaxMoney(ClawerUtils.clawerHtmlBySelectQuery(this.template.getMaxMoney(), e));
            jobInfo.setCreateDate(ClawerUtils.clawerHtmlBySelectQuery(this.template.getPlaceTime(), e));
            jobInfo.setUrl(ClawerUtils.clawerAttrBySelectQuery(this.template.getUrlQuery(), HREF, e));
            jobList.add(jobInfo);
            LOGGER.info("custom clawer add a job info, job info = {}", jobInfo);
        }

        return jobList;
    }

    private String getUrl() {
        return this.template.getUrl() + "?" + this.template.getKeyWordKey() + "=" + this.task.getKeyWord() +
                "&" + this.template.getCityKey() + "=" + this.task.getCity() + "&" +
                this.template.getExperienceKey() + "=" + this.task.getExperience() + "&" +
                this.template.getPageKey() + "=" + this.page;
    }
}
