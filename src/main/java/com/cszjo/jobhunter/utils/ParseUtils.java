package com.cszjo.jobhunter.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cszjo.jobhunter.model.JobInfo;
import com.cszjo.jobhunter.model.LagouJobInfo;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Han on 2017/4/16.
 */
public class ParseUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParseUtils.class);

    public static final String LAGOU_PRE_URL = "https://www.lagou.com/jobs/";
    public static final String LAGOU_NEXT_URL = ".html";

    private ParseUtils() {
        //防止实例化
    }

    public static List<LagouJobInfo> result2LagouJobInfoList(String body) {
        JSONObject object = (JSONObject) JSON.parse(body);
        JSONObject content = (JSONObject) object.get("content");
        JSONObject positionResult = (JSONObject) content.get("positionResult");
        return JSON.parseArray( ((JSONArray)positionResult.get("result")).toJSONString(), LagouJobInfo.class);
    }

    public static List<JobInfo> lagouJobInfoList2JobInfoList(List<LagouJobInfo> lagouJobInfos) {

        List<JobInfo> jobInfos = Lists.newArrayList();

        for(LagouJobInfo lagouJobInfo : lagouJobInfos) {

            JobInfo jobInfo = new JobInfo();

            jobInfo.setCompanyName(lagouJobInfo.getCompanyFullName());
            jobInfo.setAddressName(lagouJobInfo.getDistrict());
            jobInfo.setEducationRequire(lagouJobInfo.getEducation());
            jobInfo.setCreateDate(lagouJobInfo.getCreateTime());
            jobInfo.setJobName(lagouJobInfo.getPositionName());
            jobInfo.setUrl(LAGOU_PRE_URL + lagouJobInfo.getPositionId() + LAGOU_NEXT_URL);
            jobInfo.setMaxMoney(lagouJobInfo.getSalary());

            LOGGER.info("get a job info from la gou, job info = {}", jobInfo);
            jobInfos.add(jobInfo);
        }
        return jobInfos;
    }
}
