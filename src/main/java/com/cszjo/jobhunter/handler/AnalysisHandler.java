package com.cszjo.jobhunter.handler;

import com.cszjo.jobhunter.model.JobInfo;
import com.cszjo.jobhunter.model.analysis.AnalysisResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by Han on 2017/4/23.
 */
public class AnalysisHandler implements Callable<AnalysisResult> {

    private final Logger LOGGER = LoggerFactory.getLogger(AnalysisHandler.class);

    //待分析的数据
    private List<JobInfo> lists;

    public AnalysisHandler(List<JobInfo> lists) {
        this.lists = lists;
    }

    @Override
    public AnalysisResult call() throws Exception {

        if (lists == null || lists.size() == 0) {
            LOGGER.error("analysis job info has a error, job info is null or empty");
            return null;
        }

        AnalysisResult result = new AnalysisResult();
        double size = 0;
        long sumMoney = 0;
        double maxMoney = 0;
        double minMoney = 0;

        for (JobInfo jobInfo : lists) {

            int money = getMoneyFormMaxAndMinMoney(jobInfo.getMaxMoney());
            if (money != 0) {

                size++;
                sumMoney += money;
                if (maxMoney < money) {
                    result.setMaxMoneyJobInfo(jobInfo);
                    result.setMaxMoney(money);
                }

                if (money < minMoney) {
                    result.setMinMoneyJobInfo(jobInfo);
                    result.setMinMoney(money);
                }
            }

            LOGGER.info("analysis a job info, job name = {}, money = {}", jobInfo.getJobName(), money);
        }

        //设置平均数
        result.setAverageMoney(sumMoney / size);

        return result;
    }

    private int getMoneyFormMaxAndMinMoney(String money) {

        int maxMoney = 0;
        int minMoney = 0;

        //18k-30k la gou template
        if (money.endsWith("k")) {

            String[] maxAndMinMoney = money.split("-");
            String maxMoneyStr = maxAndMinMoney[0];
            String minMoneyStr = maxAndMinMoney[1];

            if (maxMoneyStr.endsWith("k")) {
                maxMoney = Integer.parseInt(maxMoneyStr.substring(0, maxMoneyStr.length() - 1));
            }

            if (minMoneyStr.endsWith("k")) {
                minMoney = Integer.parseInt(minMoneyStr.substring(0, minMoneyStr.length() - 1));
            }

            return (maxMoney + minMoney) / 2;
        }

        //6000-8000 zhao ping template
        if (money.endsWith("0")) {

            String[] maxAndMinMoney = money.split("-");

            if (maxAndMinMoney.length == 2) {
                maxMoney = Integer.parseInt(maxAndMinMoney[0]);
                minMoney = Integer.parseInt(maxAndMinMoney[1]);

                return (maxMoney + minMoney) / 2;
            }
        }

        return 0;
    }
}
