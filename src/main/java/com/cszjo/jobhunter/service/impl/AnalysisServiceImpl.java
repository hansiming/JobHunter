package com.cszjo.jobhunter.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cszjo.jobhunter.handler.AnalysisHandler;
import com.cszjo.jobhunter.model.JobInfo;
import com.cszjo.jobhunter.model.analysis.AnalysisResult;
import com.cszjo.jobhunter.service.AnalysisService;
import com.cszjo.jobhunter.service.JobsService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Han on 2017/4/23.
 */
@Service
public class AnalysisServiceImpl implements AnalysisService {

    @Autowired
    private JobsService jobsService;

    private ExecutorService es;

    @Override
    @Async
    public void startAnalysis(UUID uuid, List<Integer> taskIds) {

        List<Future<AnalysisResult>> futures = Lists.newArrayList();
        List<AnalysisResult> results = Lists.newArrayList();
        es = Executors.newFixedThreadPool(taskIds.size());

        for (int taskId : taskIds) {

            List<JobInfo> jobInfos = JSONObject.parseArray(JSON.toJSONString(jobsService.getJobInfoList(taskId)), JobInfo.class);
            futures.add(es.submit(new AnalysisHandler(jobInfos)));
        }

        try {

            for (Future<AnalysisResult> future : futures) {
                results.add(future.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        jobsService.insertAnalysis(results, uuid);
    }
}
