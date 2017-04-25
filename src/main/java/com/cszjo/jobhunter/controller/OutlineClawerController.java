package com.cszjo.jobhunter.controller;

import com.alibaba.fastjson.JSONObject;
import com.cszjo.jobhunter.model.ClawerTask;
import com.cszjo.jobhunter.model.Template;
import com.cszjo.jobhunter.model.analysis.AnalysisResult;
import com.cszjo.jobhunter.model.response.BaseResponse;
import com.cszjo.jobhunter.service.AnalysisService;
import com.cszjo.jobhunter.service.ClawerTaskService;
import com.cszjo.jobhunter.service.JobsService;
import com.cszjo.jobhunter.service.TemplateService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Han on 2017/4/19.
 */
@Controller
public class OutlineClawerController {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private ClawerTaskService clawerTaskService;

    @Autowired
    private JobsService jobsService;

    @Autowired
    private AnalysisService analysisService;

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
    public String clawerTaskResult() {
        return "clawerTaskResult";
    }

    @RequestMapping(value = "/clawerResult", method = RequestMethod.POST)
    @ResponseBody
    public List<JSONObject> getClawerResult(int taskId) {
        return jobsService.getJobInfoList(taskId);
    }

    @RequestMapping(value = "/getAnalysisUUID", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> getAnalysisUUID(@RequestBody List<String> taskIds) {

        List<Integer> taskIdsInt = Lists.newArrayList();
        Map<String, String> maps = Maps.newHashMap();
        UUID uuid = UUID.randomUUID();

        for(String id : taskIds) {
            taskIdsInt.add(Integer.parseInt(id));
        }

        analysisService.startAnalysis(uuid, taskIdsInt);
        maps.put("uuid", uuid.toString());

        return maps;
    }

    @RequestMapping("/dataAnalysis")
    public String dataAnalysis() {

        return "dataAnalysis";
    }

    @RequestMapping("/getDataAnalysisResultByUUID")
    @ResponseBody
    public List<String> getDataAnalysisResultByUUID(String uuid) {

        return jobsService.getAnalysisResults(uuid);
    }
}
