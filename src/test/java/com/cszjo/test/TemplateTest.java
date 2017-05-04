package com.cszjo.test;

import com.cszjo.jobhunter.JobHunterApplication;
import com.cszjo.jobhunter.model.Template;
import com.cszjo.jobhunter.service.TemplateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

/**
 * Created by Han on 2017/4/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JobHunterApplication.class)
@WebAppConfiguration
public class TemplateTest {

    @Autowired
    private TemplateService service;

    @Test
    public void addTemplateTest() {

        Template t = new Template();
        t.setTemplateName("1");
        t.setJobName("123");
        t.setCityKey("1");
        t.setMaxMoney("1");
        t.setCreateTime(new Date());
        t.setJobAddress("11");
        t.setPageKey("111");
        t.setPlaceTime("111");
        t.setKeyWordKey("111");
        t.setJobList("111");
        t.setUrl("111");
        t.setUrlQuery("111");
        t.setJobList("11");
        //爬取模板业务逻辑层，添加一个爬取模板
        service.addTemplate(t);
    }

    @Test
    public void selectTest() {

        System.out.println(service.selectAll());
    }
}
