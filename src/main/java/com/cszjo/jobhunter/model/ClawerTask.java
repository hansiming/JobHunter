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
    private int userId;
    private int threadCount;
    private String cityKey;
    private String cityValue;
    private String keyWordKey;
    private String keyWordValue;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
}
