package com.cszjo.jobhunter.service;

import com.cszjo.jobhunter.model.Jobs;

import java.util.List;

/**
 * Created by Han on 2017/4/6.
 */
public interface JobsService {

    int insertJobs(List<Jobs> jobs);
}
