package com.cszjo.jobhunter.service.impl;

import com.cszjo.jobhunter.dao.ClawerTaskDao;
import com.cszjo.jobhunter.model.ClawerTask;
import com.cszjo.jobhunter.model.response.BaseResponse;
import com.cszjo.jobhunter.model.response.ResponseInfo;
import com.cszjo.jobhunter.model.response.ResponseStatus;
import com.cszjo.jobhunter.service.ClawerTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Han on 2017/3/29.
 */
@Service
public class ClawerTaskServiceImpl implements ClawerTaskService {

    @Autowired
    private ClawerTaskDao dao;

    @Autowired
    @Qualifier("addTaskResponse")
    private BaseResponse baseResponse;

    @Override
    public List<ClawerTask> selectAll() {
        return dao.selectAll();
    }

    public BaseResponse addTask(ClawerTask task) {
        if(dao.addTask(task) == 0) {
            //add task fail
            baseResponse.setStatus(ResponseStatus.FAIL);
            baseResponse.setInfo(ResponseInfo.ADD_TASK_FAIL);
        } else {
            baseResponse.setStatus(ResponseStatus.SUCCESS);
            baseResponse.setInfo(ResponseInfo.ADD_TASK_SUCCESS);
        }
        return baseResponse;
    }
}
