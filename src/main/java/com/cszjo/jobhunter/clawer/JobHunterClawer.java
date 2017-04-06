package com.cszjo.jobhunter.clawer;

import com.cszjo.jobhunter.model.ClawerTask;
import com.cszjo.jobhunter.model.Jobs;
import com.cszjo.jobhunter.service.JobsService;
import com.google.common.collect.Lists;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

/**
 * Created by Han on 2017/4/5.
 */
public class JobHunterClawer implements Runnable {

    private JobsService service;
    private ClawerTask task;
    private int page;

    public JobHunterClawer(ClawerTask task, int page, JobsService service) {
        this.task = task;
        this.page = page;
        this.service = service;
    }

    @Override
    public void run() {
        try {
            List<Jobs> jobs = Lists.newArrayList();
            Document doc = Jsoup.connect(task.getFullPath(this.page)).get();
            Elements jobList = doc.select(task.getJobList());
            for(Element e : jobList) {
                Jobs job = new Jobs();
                job.setJobName(e.select(task.getJobName()).html());
                job.setAddressName(e.select(task.getJobAddress()).html());
                job.setCompanyName(e.select(task.getJobName()).html());
                job.setCreateDate(e.select(task.getPlaceTime()).html());
                job.setEducationRequire(e.select(task.getEducationRequire()).html());
                job.setMaxMoney(e.select(task.getMaxMoney()).html());
                job.setMinMoney(e.select(task.getMinMoney()).html());
                job.setTaskId(task.getId());
            }
            service.insertJobs(jobs);
        } catch (IOException e) {

        }
    }
}
