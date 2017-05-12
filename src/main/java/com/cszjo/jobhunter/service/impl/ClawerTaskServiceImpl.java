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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Han on 2017/3/29.
 */
@Service
public class ClawerTaskServiceImpl implements ClawerTaskService {

    private final Logger LOGGER = LoggerFactory.getLogger(ClawerTaskServiceImpl.class);

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

        LOGGER.info("add task, task = {}", task);

        if (dao.addTask(task) == 1) {

            baseResponse.setStatus(ResponseStatus.SUCCESS);
            baseResponse.setInfo(ResponseInfo.ADD_TASK_SUCCESS);
            //初始化Task容器
            callableTaskContainer.setTask(task).init();
            outlineHandler.setOver(false);

            try {

                //生产者
                outlineHandler.outlineHandler(callableTaskContainer);
                //消费者
                outlineHandler.saveJobInfos2Redis(callableTaskContainer);
            } catch (Exception e) {

                LOGGER.error("out line has a error, container = {}, e = {}", callableTaskContainer, e.getMessage());
            }
        } else {

            baseResponse.setStatus(ResponseStatus.FAIL);
            baseResponse.setInfo(ResponseInfo.ADD_TASK_FAIL);
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
        LOGGER.info("delete clawer task, id = {}", id);
        jobsService.del(id);
        return dao.deleteById(id);
    }

    @Override
    public ClawerTask selectById(int id) {
        return dao.selectById(id);
    }
}
