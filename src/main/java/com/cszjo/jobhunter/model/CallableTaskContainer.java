package com.cszjo.jobhunter.model;

import com.cszjo.jobhunter.clawer.ChinaHrClawer;
import com.cszjo.jobhunter.clawer.CustomClawer;
import com.cszjo.jobhunter.clawer.Job51Clawer;
import com.cszjo.jobhunter.clawer.LagouClawer;
import com.cszjo.jobhunter.model.city.CityKey51Job;
import com.cszjo.jobhunter.model.city.CityKeyChinaHr;
import com.cszjo.jobhunter.model.experience.ExperienceKey;
import com.cszjo.jobhunter.service.TemplateService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by Han on 2017/4/21.
 */
@Component
@Scope("prototype")
public class CallableTaskContainer {

    private ClawerTask task;
    private List<Callable<List<JobInfo>>> callableTaskList;

    @Autowired
    private CityKey51Job cityKey51Job;

    @Autowired
    private CityKeyChinaHr cityKeyChinaHr;

    @Autowired
    private ExperienceKey experienceKey;

    @Autowired
    private TemplateService templateService;

    public CallableTaskContainer init() {

        callableTaskList = Lists.newArrayList();

        if (task.getTemplateId() == ClawerTemplate.LAGOU.getId()) {

            for (int i = 0; i < 100; i++) {

                callableTaskList.add(new LagouClawer(i, task.getCity(), task.getKeyWord(), task.getExperience(), this.experienceKey));
            }

            return this;
        }

        if (task.getTemplateId() == ClawerTemplate.Job51.getId()) {

            for (int i = 0; i < 100; i++) {

                callableTaskList.add(new Job51Clawer(i, task.getCity(), task.getKeyWord(), task.getExperience(), this.cityKey51Job, this.experienceKey));
            }

            return this;
        }

        if (task.getTemplateId() == ClawerTemplate.CHINA_HR.getId()) {

            for (int i = 0; i < 100; i++) {

                callableTaskList.add(new ChinaHrClawer(i, task.getCity(), task.getKeyWord(), task.getExperience(), this.cityKeyChinaHr, this.experienceKey));
            }

            return this;
        }

        Template template = templateService.selectById(task.getTemplateId());

        for (int i = 0; i < 100; i++) {

            callableTaskList.add(new CustomClawer(template, task, i));
        }

        return this;
    }

    public CallableTaskContainer setTask(ClawerTask task) {

        this.task = task;
        return this;
    }

    public List<Callable<List<JobInfo>>> getCallableTaskList() {
        return callableTaskList;
    }

    public ClawerTask getTask() {
        return task;
    }
}
