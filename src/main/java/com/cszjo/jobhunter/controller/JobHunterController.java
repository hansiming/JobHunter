package com.cszjo.jobhunter.controller;

import com.cszjo.jobhunter.model.BaseResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Han on 2017/3/5.
 */
@Controller
public class JobHunterController {

    @RequestMapping("/index")
    public String toIndex() {
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
    @RequestMapping("/doAddTask")
    @ResponseBody
    public BaseResponse doAddTask() {

        return null;
    }
}
