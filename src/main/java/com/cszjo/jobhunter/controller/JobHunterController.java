package com.cszjo.jobhunter.controller;

import com.cszjo.jobhunter.model.response.BaseResponse;
import com.cszjo.jobhunter.model.ClawerTask;
import com.cszjo.jobhunter.service.ClawerTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Han on 2017/3/5.
 */
@Controller
public class JobHunterController {

    @Autowired
    private ClawerTaskService clawerTaskService;

    private BaseResponse baseResponse;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping("/index")
    public String toIndex(Map<String,Object> model) {
        List<ClawerTask> taskList = clawerTaskService.selectAll();
        model.put("taskList", taskList);
        return "index";
    }

    @RequestMapping("/main")
    public String toMain() {
        return "main";
    }

    @RequestMapping("/addTask")
    public String toAddTast() {
        return "addTask";
    }

    /**
     * 添加爬取任务
     * @return
     */
    @RequestMapping(value = "/doAddTask", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse doAddTask(ClawerTask task) {
        return clawerTaskService.addTask(task);
    }
}
