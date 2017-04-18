package com.cszjo.jobhunter.controller;

import com.cszjo.jobhunter.model.request.ClawerRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by Han on 2017/4/18.
 */
@Controller
public class OnlineClawerController {

    @RequestMapping("online")
    public String toOnline(Map<String,Object> model) {
        return "online";
    }

    @RequestMapping(value = "doClawer", method = RequestMethod.POST)
    @ResponseBody
    public String doClawer(ClawerRequest request) {


        return null;
    }
}
