package com.cszjo.jobhunter.service.impl;

import com.cszjo.jobhunter.dao.ClawerTaskDao;
import com.cszjo.jobhunter.handler.OutlineHandler;
import com.cszjo.jobhunter.model.CallableTaskContainer;
import com.cszjo.jobhunter.model.ClawerStatus;
import com.cszjo.jobhunter.model.ClawerTask;
import com.cszjo.jobhunter.model.response.BaseResponse;
import com.cszjo.jobhunter.model.response.ResponseInfo;
import com.cszjo.jobhunter.model.response.ResponseStatus;
import com.cszjo.jobhunter.service.ClawerTaskService;
import com.cszjo.jobhunter.service.JobsService;
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
    private JobsService jobsService;

    @Autowired
    @Qualifier("addTaskResponse")
    private BaseResponse baseResponse;

    @Autowired
    private OutlineHandler outlineHandler;

    @Autowired
    private CallableTaskContainer callableTaskContainer;

    @Override
    public List<ClawerTask> selectAll() {
        return dao.selectAll();
    }

    public BaseResponse addTask(ClawerTask task) {

        if (dao.addTask(task) == 1) {

            baseResponse.setStatus(ResponseStatus.SUCCESS);
            callableTaskContainer.setTask(task).init();
            outlineHandler.outlineHandler(callableTaskContainer);
        } else {

            baseResponse.setStatus(ResponseStatus.FAIL);
        }
        return baseResponse;
    }

    @Override
    public BaseResponse updateById(ClawerTask task) {
        if (dao.updateById(task) == 1) {
            baseResponse.setStatus(ResponseStatus.SUCCESS);
        }
        baseResponse.setStatus(ResponseStatus.FAIL);
        return baseResponse;
    }

    @Override
    public int deleteById(int id) {
        return dao.deleteById(id);
    }

    @Override
    public ClawerTask selectById(int id) {
        return dao.selectById(id);
    }
}
