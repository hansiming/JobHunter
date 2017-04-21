package com.cszjo.jobhunter.model;

/**
 * Created by Han on 2017/4/21.
 */
public class RedisJobInfo {

    private static final String PRE_JOB_INFO_NAME = "redis_job_info_";

    public static String getRedisJobInfoName(int taskId) {

        return PRE_JOB_INFO_NAME + taskId;
    }
}
