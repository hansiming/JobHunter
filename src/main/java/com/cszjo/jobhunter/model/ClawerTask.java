package com.cszjo.jobhunter.model;

import java.util.Date;

/**
 * 爬取任务Model
 * Created by Han on 2017/3/12.
 */
public class ClawerTask {

    private int id;
    private String taskName;
    private int jobCount;
    private int statu;
    private Date createTime;
    private int threadCount;
    private int templateId;
    private String keyWord;
    private String experience;

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

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    //    public String getFullPath(int page) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(this.url);
//        sb.append("&" + this.keyWordKey + "=" + this.keyWordValue);
//        sb.append("&" + this.cityKey + "=" + this.cityValue);
//        sb.append("&" + this.pageKey + "=" + page);
//        return sb.toString();
//    }
}
