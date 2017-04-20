package com.cszjo.test;

import com.cszjo.jobhunter.JobHunterApplication;
import com.cszjo.jobhunter.model.ClawerStatus;
import com.cszjo.jobhunter.model.ClawerTask;
import com.cszjo.jobhunter.service.ClawerTaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;
import java.util.List;

/**
 * Created by Han on 2017/3/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JobHunterApplication.class)
@WebAppConfiguration
public class ClawerTaskTest {

    @Autowired
    private ClawerTaskService service;

    @Test
    public void selectAll() {
        List<ClawerTask> tasks = service.selectAll();
        System.out.print(tasks);
    }

    @Test
    public void addTask() {
        ClawerTask task = new ClawerTask();
        task.setStatu(ClawerStatus.IN_CLAWERING.getStatus());
        task.setCreateTime(new Date());
        task.setExperience("11");
        task.setJobCount(11);
        task.setKeyWord("111");
        task.setTaskName("111");
        task.setTemplateId(1);
        task.setThreadCount(111);
        service.addTask(task);
    }
}
