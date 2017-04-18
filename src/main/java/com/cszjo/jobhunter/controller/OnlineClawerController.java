package com.cszjo.jobhunter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
