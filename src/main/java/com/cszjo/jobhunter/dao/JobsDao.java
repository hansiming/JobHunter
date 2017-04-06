package com.cszjo.jobhunter.dao;

import com.cszjo.jobhunter.model.Jobs;

import java.util.List;

/**
 * Created by Han on 2017/4/6.
 */
public interface JobsDao {

    public int insertJobs(List<Jobs> jobs);
}
