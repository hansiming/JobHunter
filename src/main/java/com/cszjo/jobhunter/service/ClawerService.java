package com.cszjo.jobhunter.service;

import com.cszjo.jobhunter.model.JobInfo;
import com.cszjo.jobhunter.model.request.ClawerRequest;

import java.util.List;

/**
 * Created by Han on 2017/3/12.
 */
public interface ClawerService {

    List<JobInfo> clawer(ClawerRequest request);
}
