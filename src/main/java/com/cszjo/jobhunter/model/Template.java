package com.cszjo.jobhunter.model;

import java.util.Date;

/**
 * Created by Han on 2017/4/19.
 */
public class Template {

    private int id;
    private String templateName;
    private String url;
    private Date createTime;
    private String cityKey;
    private String keyWordKey;
    private String experienceKey;
    private String pageKey;
    private String jobList;
    private String jobName;
    private String urlQuery;
    private String placeTime;
    private String jobAddress;
    private String maxMoney;
    private String companyName;
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCityKey() {
        return cityKey;
    }

    public void setCityKey(String cityKey) {
        this.cityKey = cityKey;
    }

    public String getExperienceKey() {
        return experienceKey;
    }

    public void setExperienceKey(String experienceKey) {
        this.experienceKey = experienceKey;
    }

    public String getKeyWordKey() {
        return keyWordKey;
    }

    public void setKeyWordKey(String keyWordKey) {
        this.keyWordKey = keyWordKey;
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

    public String getUrlQuery() {
        return urlQuery;
    }

    public void setUrlQuery(String urlQuery) {
        this.urlQuery = urlQuery;
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

    @Override
    public String toString() {
        return "Template{" +
                "id=" + id +
                ", templateName='" + templateName + '\'' +
                ", url='" + url + '\'' +
                ", createTime=" + createTime +
                ", cityKey='" + cityKey + '\'' +
                ", keyWordKey='" + keyWordKey + '\'' +
                ", experienceKey='" + experienceKey + '\'' +
                ", pageKey='" + pageKey + '\'' +
                ", jobList='" + jobList + '\'' +
                ", jobName='" + jobName + '\'' +
                ", urlQuery='" + urlQuery + '\'' +
                ", placeTime='" + placeTime + '\'' +
                ", jobAddress='" + jobAddress + '\'' +
                ", maxMoney='" + maxMoney + '\'' +
                ", companyName='" + companyName + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
