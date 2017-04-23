package com.cszjo.jobhunter.model.analysis;

/**
 * Created by Han on 2017/4/23.
 */
public class AnalysisResult {

    private int maxMoney;
    private int minMoney;
    private int averageMoney;
    //中位数
    private int medianMoney;

    public int getMaxMoney() {
        return maxMoney;
    }

    public void setMaxMoney(int maxMoney) {
        this.maxMoney = maxMoney;
    }

    public int getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(int minMoney) {
        this.minMoney = minMoney;
    }

    public int getAverageMoney() {
        return averageMoney;
    }

    public void setAverageMoney(int averageMoney) {
        this.averageMoney = averageMoney;
    }

    public int getMedianMoney() {
        return medianMoney;
    }

    public void setMedianMoney(int medianMoney) {
        this.medianMoney = medianMoney;
    }
}
