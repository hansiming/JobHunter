package com.cszjo.jobhunter.service.impl;

//import com.cszjo.jobhunter.clawer.JobHunterClawer;
import com.cszjo.jobhunter.dao.JobsDao;
import com.cszjo.jobhunter.model.ClawerTask;
import com.cszjo.jobhunter.model.JobInfo;
import com.cszjo.jobhunter.service.JobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Han on 2017/4/6.
 */
@Service
public class JobsServiceImpl implements JobsService {

    @Autowired
    private JobsDao dao;

    private ExecutorService executorService;

    @Override
    public int insertJobs(List<JobInfo> jobs) {
        return dao.insertJobs(jobs);
    }

    @Override
    public void startClawer(ClawerTask task) {
        executorService = Executors.newFixedThreadPool(task.getThreadCount());
//        executorService.execute(new JobHunterClawer(task, 2, this));
    }
}
