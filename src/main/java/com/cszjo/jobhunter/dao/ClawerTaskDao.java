package com.cszjo.jobhunter.dao;

import com.cszjo.jobhunter.model.ClawerTask;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Han on 2017/3/29.
 */
@Repository
public interface ClawerTaskDao {

    List<ClawerTask> selectAll();

    int addTask(ClawerTask task);
}
