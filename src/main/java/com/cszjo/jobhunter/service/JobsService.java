package com.cszjo.jobhunter.service;

import com.cszjo.jobhunter.model.ClawerTask;
import com.cszjo.jobhunter.model.JobInfo;

import java.util.List;

/**
 * Created by Han on 2017/4/6.
 */
public interface JobsService {

    int insertJobs(List<JobInfo> jobs);

    void startClawer(ClawerTask task);
}
