package com.cszjo.jobhunter.model;

/**
 * Created by Han on 2017/4/21.
 */
public class RedisPrefix {

    private static final String PRE_JOB_INFO_NAME = "redis_job_info_";
    private static final String ANALYSIS_RESULT_NAME= "analysys_result_";

    public static String getRedisJobInfoName(int taskId) {

        return PRE_JOB_INFO_NAME + taskId;
    }

    public static String getAnalysisResultName(String uuid) {
        return ANALYSIS_RESULT_NAME + uuid;
    }
}
