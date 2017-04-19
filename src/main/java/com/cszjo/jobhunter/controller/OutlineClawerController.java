package com.cszjo.jobhunter.controller;

import com.cszjo.jobhunter.model.Template;
import com.cszjo.jobhunter.model.response.BaseResponse;
import com.cszjo.jobhunter.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Han on 2017/4/19.
 */
@Controller
public class OutlineClawerController {

    @Autowired
    TemplateService service;

    @RequestMapping("addTemplate")
    public String addTemplate() {
        return "addTemplate";
    }

    @RequestMapping(value = "doAddTemplate", method = RequestMethod.POST)
    public BaseResponse doAddTemplate(Template template) {

        return service.addTemplate(template);
    }
}
