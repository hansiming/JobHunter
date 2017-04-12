package com.cszjo.jobhunter.dao;

import com.cszjo.jobhunter.model.JobInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Han on 2017/4/6.
 */
@Repository
public interface JobsDao {

    int insertJobs(List<JobInfo> jobs);
}
