package com.cszjo.test;

import com.cszjo.jobhunter.JobHunterApplication;
import com.cszjo.jobhunter.clawer.CustomClawer;
import com.cszjo.jobhunter.model.ClawerTask;
import com.cszjo.jobhunter.model.JobInfo;
import com.cszjo.jobhunter.model.Template;
import com.cszjo.jobhunter.service.ClawerTaskService;
import com.cszjo.jobhunter.service.TemplateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Han on 2017/4/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JobHunterApplication.class)
@WebAppConfiguration
public class CustomClawerTest {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private ClawerTaskService clawerTaskService;

    @Test
    public void customClawerTest() throws Exception {

        Template template = templateService.selectById(2);
        ClawerTask clawerTask = clawerTaskService.selectById(36);

        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<List<JobInfo>> f = es.submit(new CustomClawer(template, clawerTask, 1));
        System.out.println(f.get());


    }
}
