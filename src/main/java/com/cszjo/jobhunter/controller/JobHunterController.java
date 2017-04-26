package com.cszjo.jobhunter.controller;

import com.cszjo.jobhunter.model.Template;
import com.cszjo.jobhunter.model.response.BaseResponse;
import com.cszjo.jobhunter.model.ClawerTask;
import com.cszjo.jobhunter.service.ClawerTaskService;
import com.cszjo.jobhunter.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Han on 2017/3/5.
 */
@Controller
public class JobHunterController {

    @Autowired
    private ClawerTaskService clawerTaskService;

    @Autowired
    private TemplateService templateService;

    private BaseResponse baseResponse;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping("/index")
    public String toIndex(Map<String,Object> model) {
        return "index";
    }

    @RequestMapping("/main")
    public String toMain(Map<String, Object> model) {
        List<ClawerTask> taskList = clawerTaskService.selectAll();
        model.put("taskList", taskList);
        return "main";
    }

    @RequestMapping("/addTask")
    public String toAddTast(Map<String, Object> model) {

        List<Template> templates = templateService.selectAll();
        model.put("templates", templates);
        return "addTask";
    }

    @RequestMapping("/editTask")
    public String taskEdit(int id, Map<String,Object> model) {
        ClawerTask task = clawerTaskService.selectById(id);
        model.put("task", task);
        return "editTask";
    }
    @RequestMapping(value = "/doEditTask", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse doEditTask(@RequestBody ClawerTask task) {
        return clawerTaskService.updateById(task);
    }
}
