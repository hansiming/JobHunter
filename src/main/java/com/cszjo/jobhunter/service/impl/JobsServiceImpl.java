package com.cszjo.jobhunter.service.impl;

import com.cszjo.jobhunter.dao.JobsDao;
import com.cszjo.jobhunter.model.Jobs;
import com.cszjo.jobhunter.service.JobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Han on 2017/4/6.
 */
@Service
public class JobsServiceImpl implements JobsService {

    @Autowired
    private JobsDao dao;

    @Override
    public int insertJobs(List<Jobs> jobs) {
        return dao.insertJobs(jobs);
    }
}
