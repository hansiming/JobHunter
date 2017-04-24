package com.cszjo.jobhunter.model.analysis;

import com.cszjo.jobhunter.model.ClawerTask;
import com.cszjo.jobhunter.model.JobInfo;

/**
 * Created by Han on 2017/4/23.
 */
public class AnalysisResult {

    private ClawerTask task;
    private double maxMoney;
    private double minMoney;
    private double averageMoney;
    //中位数
    private int medianMoney;
    private JobInfo maxMoneyJobInfo;
    private JobInfo minMoneyJobInfo;

    public ClawerTask getTask() {
        return task;
    }

    public void setTask(ClawerTask task) {
        this.task = task;
    }

    public double getMaxMoney() {
        return maxMoney;
    }

    public void setMaxMoney(double maxMoney) {
        this.maxMoney = maxMoney;
    }

    public double getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(double minMoney) {
        this.minMoney = minMoney;
    }

    public double getAverageMoney() {
        return averageMoney;
    }

    public void setAverageMoney(double averageMoney) {
        this.averageMoney = averageMoney;
    }

    public int getMedianMoney() {
        return medianMoney;
    }

    public void setMedianMoney(int medianMoney) {
        this.medianMoney = medianMoney;
    }

    public JobInfo getMaxMoneyJobInfo() {
        return maxMoneyJobInfo;
    }

    public void setMaxMoneyJobInfo(JobInfo maxMoneyJobInfo) {
        this.maxMoneyJobInfo = maxMoneyJobInfo;
    }

    public JobInfo getMinMoneyJobInfo() {
        return minMoneyJobInfo;
    }

    public void setMinMoneyJobInfo(JobInfo minMoneyJobInfo) {
        this.minMoneyJobInfo = minMoneyJobInfo;
    }
}
