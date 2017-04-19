package com.cszjo.jobhunter.service;

import com.cszjo.jobhunter.model.Template;
import com.cszjo.jobhunter.model.response.BaseResponse;

import java.util.List;

/**
 * Created by Han on 2017/4/19.
 */
public interface TemplateService {

    List<Template> selectAll();

    BaseResponse addTemplate(Template template);

    Template selectById(int id);

    int updateById(Template template);

    int deleteById(int id);
}
