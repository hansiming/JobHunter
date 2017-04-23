package com.cszjo.test;

import com.cszjo.jobhunter.JobHunterApplication;
import com.cszjo.jobhunter.service.AnalysisService;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.UUID;

/**
 * Created by Han on 2017/4/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JobHunterApplication.class)
@WebAppConfiguration
public class AnalysisTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnalysisTest.class);

    @Autowired
    private AnalysisService service;

    @Test
    public void analysisTest() {

        List<Integer> taskIds = Lists.newArrayList();
        taskIds.add(17);

        UUID uuid = UUID.randomUUID();
        LOGGER.info("create a uuid for analysis result, uuid = {}", uuid);
        service.startAnalysis(uuid, taskIds);
    }
}
