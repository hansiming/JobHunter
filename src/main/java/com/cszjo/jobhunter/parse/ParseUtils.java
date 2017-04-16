package com.cszjo.jobhunter.parse;

import com.alibaba.fastjson.JSONArray;
import com.cszjo.jobhunter.model.JobInfo;
import com.cszjo.jobhunter.model.LagouJobInfo;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by Han on 2017/4/16.
 */
public class ParseUtils {

    public static final String LAGOU_PRE_URL = "https://www.lagou.com/jobs/";
    public static final String LAGOU_NEXT_URL = ".html";

    private ParseUtils() {
        //防止实例化
    }

    public static List<LagouJobInfo> result2LagouJobInfoList(String boby) {
        return (List<LagouJobInfo>) JSONArray.parse(boby);
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
            parseMoney(jobInfo, lagouJobInfo);

            jobInfos.add(jobInfo);
        }
        return jobInfos;
    }

    private static JobInfo parseMoney(JobInfo jobInfo, LagouJobInfo lagouJobInfo) {

        String salary = lagouJobInfo.getSalary();
        String[] maxAndMinMoney = salary.split("-");
        String maxMoney = maxAndMinMoney[0];
        String minMoney = maxAndMinMoney[1];
        if(maxMoney.endsWith("k")) {
            jobInfo.setMaxMoney(maxMoney);
        }
        if(minMoney.endsWith("k")) {
            jobInfo.setMinMoney(minMoney);
        }
        return jobInfo;
    }
}
