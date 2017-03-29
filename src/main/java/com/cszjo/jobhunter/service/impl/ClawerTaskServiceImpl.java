package com.cszjo.jobhunter.service.impl;

import com.cszjo.jobhunter.dao.ClawerTaskDao;
import com.cszjo.jobhunter.model.ClawerTask;
import com.cszjo.jobhunter.service.ClawerTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Han on 2017/3/29.
 */
@Service
public class ClawerTaskServiceImpl implements ClawerTaskService {

    @Autowired
    private ClawerTaskDao dao;

    @Override
    public List<ClawerTask> selectAll() {
        return dao.selectAll();
    }
}
