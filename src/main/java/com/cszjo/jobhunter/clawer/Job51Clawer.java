package com.cszjo.jobhunter.clawer;

import com.cszjo.jobhunter.model.JobInfo;
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
            Element resultList = doc.getElementById("resultList");
            Elements jobs = resultList.select(".el");
            List<JobInfo> jobInfos = Lists.newArrayList();

            for (Element job : jobs) {

                JobInfo jobInfo = new JobInfo();
                //get job name
                Elements jobNameElements = job.select(".t1 span a");
                for (Element jobNameElement : jobNameElements) {
                    jobInfo.setJobName(jobNameElement.attr("title"));
                    jobInfo.setUrl(jobNameElement.attr("href"));
                }
                //get company name
                Elements companyNameElements = job.select(".t2 a");
                for (Element companyNameElement : companyNameElements) {
                    jobInfo.setCompanyName(companyNameElement.attr("title"));
                }
                //get address
                Elements addressElements = job.select(".t3");
                for (Element addressElement : addressElements) {
                    jobInfo.setAddressName(addressElement.html());
                }
                //get money
                Elements moneyElements = job.select(".t4");
                for (Element moneyElement : moneyElements) {
                    jobInfo.setMaxMoney(moneyElement.html());
                }
                //get createTime
                Elements createTimeElements = job.select(".t5");
                for (Element createTimeElement : createTimeElements) {
                    jobInfo.setCreateDate(createTimeElement.html());
                }
                LOGGER.info("get a job info from 51 job, job info = {}", jobInfo);
            }

            return jobInfos;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getUrl() {
        return String.format(this.url, this.city, this.keyWord, this.page);
    }
}
