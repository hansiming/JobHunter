package com.cszjo.jobhunter.model;

import java.util.Date;

/**
 * Created by Han on 2017/4/6.
 */
public class JobInfo {

    private int id;
    private String jobName;
    private String addressName;
    private String createDate;
    private String maxMoney;
    private String minMoney;
    private String educationRequire;
    private String companyName;
    private String remark;
    private int taskId;
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getMaxMoney() {
        return maxMoney;
    }

    public void setMaxMoney(String maxMoney) {
        this.maxMoney = maxMoney;
    }

    public String getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(String minMoney) {
        this.minMoney = minMoney;
    }

    public String getEducationRequire() {
        return educationRequire;
    }

    public void setEducationRequire(String educationRequire) {
        this.educationRequire = educationRequire;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "JobInfo{" +
                "id=" + id +
                ", jobName='" + jobName + '\'' +
                ", addressName='" + addressName + '\'' +
                ", createDate='" + createDate + '\'' +
                ", maxMoney='" + maxMoney + '\'' +
                ", minMoney='" + minMoney + '\'' +
                ", educationRequire='" + educationRequire + '\'' +
                ", companyName='" + companyName + '\'' +
                ", remark='" + remark + '\'' +
                ", taskId=" + taskId +
                ", url='" + url + '\'' +
                '}';
    }
}
