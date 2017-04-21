package com.cszjo.jobhunter.service.impl;

import com.cszjo.jobhunter.clawer.ChinaHrClawer;
import com.cszjo.jobhunter.clawer.Job51Clawer;
import com.cszjo.jobhunter.clawer.LagouClawer;
import com.cszjo.jobhunter.handler.OnlineHandler;
import com.cszjo.jobhunter.handler.OutlineHandler;
import com.cszjo.jobhunter.model.CallableTaskContainer;
import com.cszjo.jobhunter.model.ClawerTask;
import com.cszjo.jobhunter.model.ClawerTemplate;
import com.cszjo.jobhunter.model.city.CityKey51Job;
import com.cszjo.jobhunter.model.city.CityKeyChinaHr;
import com.cszjo.jobhunter.model.JobInfo;
import com.cszjo.jobhunter.model.experience.ExperienceKey;
import com.cszjo.jobhunter.model.request.ClawerRequest;
import com.cszjo.jobhunter.service.ClawerService;
import com.cszjo.jobhunter.service.TemplateService;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Han on 2017/4/18.
 */
@Service
public class ClawerServiceImpl implements ClawerService {

    private final Logger LOGGER = LoggerFactory.getLogger(ClawerServiceImpl.class);

    @Autowired
    private OnlineHandler onlineHandler;

    @Autowired
    private OutlineHandler outlineHandler;

    @Autowired
    private CallableTaskContainer callableTaskContainer;

    @Override
    public List<JobInfo> clawer(ClawerRequest request) {
        return onlineHandler.onlineClawer(request);
    }

    public void outlineClawer(ClawerTask task) {

        if (task == null) {

            LOGGER.error("out line clawer, task is null");
        }

        callableTaskContainer = callableTaskContainer.setTask(task).init();
        outlineHandler.outlineHandler(callableTaskContainer);
    }
}
