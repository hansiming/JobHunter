package com.cszjo.jobhunter.service.impl;

import com.cszjo.jobhunter.dao.TemplateDao;
import com.cszjo.jobhunter.model.Template;
import com.cszjo.jobhunter.model.response.BaseResponse;
import com.cszjo.jobhunter.model.response.ResponseInfo;
import com.cszjo.jobhunter.model.response.ResponseStatus;
import com.cszjo.jobhunter.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Han on 2017/4/19.
 */
@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    TemplateDao dao;

    @Autowired
    @Qualifier("addTemplateResponse")
    BaseResponse response;

    @Override
    public List<Template> selectAll() {
        return dao.selectAll();
    }

    @Override
    public BaseResponse addTemplate(Template template) {

        if (dao.addTemplate(template) == 1) {
            response.setStatus(ResponseStatus.SUCCESS);
            response.setInfo(ResponseInfo.ADD_TEMPLATE_SUCCESS);
            return response;
        }

        response.setStatus(ResponseStatus.FAIL);
        response.setInfo(ResponseInfo.ADD_TEMPLATE_FAIL);

        return response;
    }

    @Override
    public Template selectById(int id) {
        return dao.selectById(id);
    }

    @Override
    public int updateById(Template template) {
        return dao.updateById(template);
    }

    @Override
    public int deleteById(int id) {
        return dao.deleteById(id);
    }
}
