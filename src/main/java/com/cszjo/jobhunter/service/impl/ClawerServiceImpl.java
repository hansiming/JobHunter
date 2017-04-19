package com.cszjo.jobhunter.service.impl;

import com.cszjo.jobhunter.clawer.ChinaHrClawer;
import com.cszjo.jobhunter.clawer.Job51Clawer;
import com.cszjo.jobhunter.clawer.LagouClawer;
import com.cszjo.jobhunter.model.city.CityKey51Job;
import com.cszjo.jobhunter.model.city.CityKeyChinaHr;
import com.cszjo.jobhunter.model.JobInfo;
import com.cszjo.jobhunter.model.experience.ExperienceKey;
import com.cszjo.jobhunter.model.request.ClawerRequest;
import com.cszjo.jobhunter.service.ClawerService;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Han on 2017/4/18.
 */
@Service
public class ClawerServiceImpl implements ClawerService {

    private final Logger LOGGER = LoggerFactory.getLogger(ClawerServiceImpl.class);

    private List<JobInfo> jobInfos;
    private ExecutorService es;
    private Future<List<JobInfo>> lagouResult;
    private Future<List<JobInfo>> job51Result;
    private Future<List<JobInfo>> chinaHrResult;

    @Autowired
    private CityKey51Job cityKey51Job;

    @Autowired
    private CityKeyChinaHr cityKeyChinaHr;

    @Autowired
    private ExperienceKey experienceKey;

    @Override
    public List<JobInfo> clawer(ClawerRequest request) {

        es = Executors.newFixedThreadPool(16);
        jobInfos = Lists.newArrayList();

        lagouResult = request.isLagou() ?
                es.submit(new LagouClawer(request.getPage(), request.getArea(), request.getKeyWord(), request.getExperience(), this.experienceKey)) : null;

        job51Result = request.isJob51() ?
                es.submit(new Job51Clawer(request.getPage(), request.getArea(), request.getKeyWord(), request.getExperience(), cityKey51Job, this.experienceKey)) : null;

        chinaHrResult = request.isChinahr() ?
                es.submit(new ChinaHrClawer(request.getPage(), request.getArea(), request.getKeyWord(), request.getExperience(), cityKeyChinaHr, this.experienceKey)) : null;

        try {

            appendJobinfoFormFuture(lagouResult);
            appendJobinfoFormFuture(job51Result);
            appendJobinfoFormFuture(chinaHrResult);

        } catch (Exception e) {

            LOGGER.error("clawer job info has a error, request = {}, error = {}", request, e.getMessage());
        }

        return jobInfos;
    }

    private void appendJobinfoFormFuture(Future<List<JobInfo>> future) throws Exception {

        if(future != null) {
            List<JobInfo> lagouJobs = future.get();
            for (JobInfo jobInfo : lagouJobs) {
                jobInfos.add(jobInfo);
            }
        }
    }
}
