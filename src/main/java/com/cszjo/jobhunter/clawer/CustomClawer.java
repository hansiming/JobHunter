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

import java.util.List;
import java.util.concurrent.Callable;

/**
 * 自定义爬取
 * Created by Han on 2017/4/20.
 */
public class CustomClawer implements Callable<List<JobInfo>> {

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
        Elements jobInfoList = doc.select(this.template.getJobList());
        List<JobInfo> jobList = Lists.newArrayList();

        for(Element e : jobInfoList) {

            JobInfo jobInfo = new JobInfo();

            jobInfo.setJobName(ClawerUtils.clawerHtmlBySelectQuery(this.template.getJobName(), e));
            jobInfo.setAddressName(ClawerUtils.clawerHtmlBySelectQuery(this.template.getJobAddress(), e));
            jobInfo.setMaxMoney(ClawerUtils.clawerHtmlBySelectQuery(this.template.getMaxMoney(), e));
            jobInfo.setCreateDate(ClawerUtils.clawerHtmlBySelectQuery(this.template.getPlaceTime(), e));
            jobInfo.setUrl(ClawerUtils.clawerAttrBySelectQuery(this.template.getUrlQuery(), HREF, e));
            jobList.add(jobInfo);
        }

        return jobList;
    }

    private String getUrl() {
        return this.template.getUrl() + this.template.getKeyWordKey() + "=" + this.task.getKeyWord() +
                "&" + this.template.getCityKey() + "=" + this.task.getCity() + "&" +
                this.template.getExperienceKey() + "=" + this.task.getExperience() + "&" +
                this.template.getPageKey() + "=" + this.page;
    }
}
