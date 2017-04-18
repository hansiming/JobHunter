package com.cszjo.jobhunter.controller;

import com.cszjo.jobhunter.model.JobInfo;
import com.cszjo.jobhunter.model.request.ClawerRequest;
import com.cszjo.jobhunter.service.ClawerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Han on 2017/4/18.
 */
@Controller
public class OnlineClawerController {

    @Autowired
    private ClawerService service;

    @RequestMapping("online")
    public String toOnline(Map<String,Object> model) {
        return "online";
    }

    @RequestMapping(value = "doClawer", method = RequestMethod.POST)
    @ResponseBody
    public List<JobInfo> doClawer(ClawerRequest request) {

        return service.clawer(request);
    }
}
