package com.cszjo.jobhunter.service;

import com.cszjo.jobhunter.model.ClawerTask;
import com.cszjo.jobhunter.model.response.BaseResponse;

import java.util.List;

/**
 * Created by Han on 2017/3/29.
 */
public interface ClawerTaskService {

    List<ClawerTask> selectAll();

    BaseResponse addTask(ClawerTask task);

    ClawerTask selectById(int id);

    BaseResponse updateById(ClawerTask task);

    int deleteById(int id);
}
