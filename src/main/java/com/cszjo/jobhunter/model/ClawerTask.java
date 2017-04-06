package com.cszjo.jobhunter.model;

import java.util.Date;

/**
 * 爬取任务Model
 * Created by Han on 2017/3/12.
 */
public class ClawerTask {

    private int id;
    private String taskName;
    private String url;
    private int jobCount;
    private int statu;
    private Date createTime;
    private int threadCount;
    private String cityKey;
    private String cityValue;
    private String keyWordKey;
    private String keyWordValue;
    private String pageKey;
    private String jobList;
    private String jobName;
    private String placeTime;
    private String jobAddress;
    private String maxMoney;
    private String minMoney;
    private String educationRequire;
    private String companyName;
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getJobCount() {
        return jobCount;
    }

    public void setJobCount(int jobCount) {
        this.jobCount = jobCount;
    }

    public int getStatu() {
        return statu;
    }

    public void setStatu(int statu) {
        this.statu = statu;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }

    public String getCityKey() {
        return cityKey;
    }

    public void setCityKey(String cityKey) {
        this.cityKey = cityKey;
    }

    public String getCityValue() {
        return cityValue;
    }

    public void setCityValue(String cityValue) {
        this.cityValue = cityValue;
    }

    public String getKeyWordKey() {
        return keyWordKey;
    }

    public void setKeyWordKey(String keyWordKey) {
        this.keyWordKey = keyWordKey;
    }

    public String getKeyWordValue() {
        return keyWordValue;
    }

    public void setKeyWordValue(String keyWordValue) {
        this.keyWordValue = keyWordValue;
    }

    public String getPageKey() {
        return pageKey;
    }

    public void setPageKey(String pageKey) {
        this.pageKey = pageKey;
    }

    public String getJobList() {
        return jobList;
    }

    public void setJobList(String jobList) {
        this.jobList = jobList;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getPlaceTime() {
        return placeTime;
    }

    public void setPlaceTime(String placeTime) {
        this.placeTime = placeTime;
    }

    public String getJobAddress() {
        return jobAddress;
    }

    public void setJobAddress(String jobAddress) {
        this.jobAddress = jobAddress;
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

    public String getFullPath(int page) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.url);
        sb.append("&" + this.keyWordKey + "=" + this.keyWordValue);
        sb.append("&" + this.cityKey + "=" + this.cityValue);
        sb.append("&" + this.pageKey + "=" + page);
        return sb.toString();
    }
}
