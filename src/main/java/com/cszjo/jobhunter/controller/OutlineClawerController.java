package com.cszjo.jobhunter.controller;

import com.alibaba.fastjson.JSONObject;
import com.cszjo.jobhunter.model.ClawerTask;
import com.cszjo.jobhunter.model.Template;
import com.cszjo.jobhunter.model.response.BaseResponse;
import com.cszjo.jobhunter.service.ClawerTaskService;
import com.cszjo.jobhunter.service.JobsService;
import com.cszjo.jobhunter.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Han on 2017/4/19.
 */
@Controller
public class OutlineClawerController {

    @Autowired
    TemplateService templateService;

    @Autowired
    ClawerTaskService clawerTaskService;

    @Autowired
    JobsService jobsService;

    @RequestMapping("addTemplate")
    public String addTemplate() {
        return "addTemplate";
    }

    @RequestMapping(value = "doAddTemplate", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse doAddTemplate(Template template) {

        return templateService.addTemplate(template);
    }

    /**
     * 添加爬取任务
     * @return
     */
    @RequestMapping(value = "/doAddTask", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse doAddTask(@RequestBody ClawerTask task) {

        task.setExperience("0");
        return clawerTaskService.addTask(task);
    }

    @RequestMapping("/doDeleteTask")
    public String doDeleteTask(int id, Map<String,Object> model) {

        clawerTaskService.deleteById(id);
        List<ClawerTask> taskList = clawerTaskService.selectAll();
        model.put("taskList", taskList);
        return "main";
    }

    @RequestMapping("/clawerTaskResult")
    public String clawerTaskResult(int taskId) {
        return "clawerTaskResult";
    }

    @RequestMapping(value = "/clawerResult", method = RequestMethod.POST)
    @ResponseBody
    public List<JSONObject> getClawerResult(@RequestBody int taskId) {
        return jobsService.getJobInfoList(taskId);
    }
}
