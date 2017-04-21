package com.cszjo.test;

import com.cszjo.jobhunter.JobHunterApplication;
import com.cszjo.jobhunter.handler.OutlineHandler;
import com.cszjo.jobhunter.model.CallableTaskContainer;
import com.cszjo.jobhunter.model.ClawerStatus;
import com.cszjo.jobhunter.model.ClawerTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by Han on 2017/4/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JobHunterApplication.class)
@WebAppConfiguration
public class OutLineClawerTest {

    @Autowired
    private CallableTaskContainer callableTaskContainer;

    @Autowired
    private OutlineHandler outlineHandler;

    @Test
    public void outLineLagouTest() {

        ClawerTask task = new ClawerTask();
        task.setStatu(ClawerStatus.IN_CLAWERING.getStatus());
        task.setTemplateId(99);
        task.setThreadCount(5);
        task.setTaskName("abc");
        task.setCity("北京");
        task.setExperience("0");
        task.setJobCount(1000);
        task.setKeyWord("Java");
        task.setNowCount(0);
        task.setId(0);

        callableTaskContainer.setTask(task).init();
        outlineHandler.outlineHandler(callableTaskContainer);
    }
}
