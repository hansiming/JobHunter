package com.cszjo.jobhunter.parse;

import com.cszjo.jobhunter.model.JobInfo;
import com.cszjo.jobhunter.model.LagouJobInfo;
import org.springframework.stereotype.Component;

/**
 * Created by Han on 2017/4/12.
 */
@Component("lagou2JobInfo")
public class ParseLagou2JobInfo {

    final String PRE_URL = "https://www.lagou.com/jobs/";
    final String NEXT_URL = ".html";

    public JobInfo parse(LagouJobInfo lagouJobInfo) {

        JobInfo jobInfo = new JobInfo();

        jobInfo.setCompanyName(lagouJobInfo.getCompanyFullName());
        jobInfo.setAddressName(lagouJobInfo.getDistrict());
        jobInfo.setEducationRequire(lagouJobInfo.getEducation());
        jobInfo.setCreateDate(lagouJobInfo.getCreateTime());
        jobInfo.setJobName(lagouJobInfo.getPositionName());
        jobInfo.setUrl(PRE_URL + lagouJobInfo.getPositionId() + NEXT_URL);
        this.parseMoney(jobInfo, lagouJobInfo);

        return jobInfo;
    }

    private JobInfo parseMoney(JobInfo jobInfo, LagouJobInfo lagouJobInfo) {

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
