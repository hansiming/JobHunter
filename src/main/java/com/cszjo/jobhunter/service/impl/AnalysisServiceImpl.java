package com.cszjo.jobhunter.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cszjo.jobhunter.handler.AnalysisHandler;
import com.cszjo.jobhunter.model.JobInfo;
import com.cszjo.jobhunter.model.analysis.AnalysisResult;
import com.cszjo.jobhunter.service.AnalysisService;
import com.cszjo.jobhunter.service.ClawerTaskService;
import com.cszjo.jobhunter.service.JobsService;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger LOGGER = LoggerFactory.getLogger(AnalysisServiceImpl.class);

    @Autowired
    private JobsService jobsService;

    @Autowired
    private ClawerTaskService clawerTaskService;

    @Autowired
    private AnalysisHandler handler;

    /**
     * 开始分析任务，异步执行
     * @param uuid
     * @param taskIds
     */
    @Override
    @Async
    public void startAnalysis(UUID uuid, List<Integer> taskIds) {

        if (uuid == null) {
            LOGGER.error("start analysis, uuid is null");
        }

        if (taskIds == null || taskIds.size() == 0) {
            LOGGER.error("start analysis, taskIds is null or empty");
        }

        AnalysisResult result = new AnalysisResult();

        for (int taskId : taskIds) {

            List<JobInfo> jobInfos = JSONObject.parseArray(JSON.toJSONString(jobsService.getJobInfoList(taskId)), JobInfo.class);
            handler.analysis(jobInfos, result, clawerTaskService.selectById(taskId));
        }

        jobsService.insertAnalysis(result, uuid);
    }
}
