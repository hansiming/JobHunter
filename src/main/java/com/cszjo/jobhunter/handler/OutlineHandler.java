package com.cszjo.jobhunter.handler;

import com.cszjo.jobhunter.model.CallableTaskContainer;
import com.cszjo.jobhunter.model.ClawerStatus;
import com.cszjo.jobhunter.model.ClawerTask;
import com.cszjo.jobhunter.model.JobInfo;
import com.cszjo.jobhunter.service.ClawerService;
import com.cszjo.jobhunter.service.ClawerTaskService;
import com.cszjo.jobhunter.service.JobsService;
import com.google.common.collect.Queues;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Han on 2017/4/21.
 */
@Component
public class OutlineHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(OutlineHandler.class);
    private Queue<Future<List<JobInfo>>> queue = Queues.newLinkedBlockingQueue();
    private ExecutorService es;

    @Autowired
    private JobsService jobsService;

    @Autowired
    private ClawerTaskService clawerTaskService;

    @Async
    public void outlineHandler(CallableTaskContainer container) {

        if (container == null) {

            LOGGER.error("outline handler, callable task container is null");
        }

        int threadCount = container.getTask().getThreadCount();
        int jobCount = container.getTask().getJobCount();
        List<Callable<List<JobInfo>>> callables = container.getCallableTaskList();
        es = Executors.newFixedThreadPool(threadCount);
        ClawerTask task = container.getTask();

        try {

            //start thread to clawer
            for (Callable<List<JobInfo>> callable : callables) {
                queue.offer(es.submit(callable));
            }

            int nowCount = 0;
            int testCount = 0;

            while(true) {

                if (queue.size() > 0) {

                    Future<List<JobInfo>> future = queue.poll();
                    List<JobInfo> jobInfoList = future.get();

                    if(jobInfoList != null && jobInfoList.size() > 0) {

                        int insertCount = jobsService.insertJobs(jobInfoList, task);
                        nowCount += insertCount;
                    }

                    task.setNowCount(nowCount);

                    if(nowCount > jobCount) {
                        task.setStatu(ClawerStatus.SUCCESS.getStatus());
                        clawerTaskService.updateById(task);
                        break;
                    }

                    clawerTaskService.updateById(task);
                } else {

                    testCount++;
                    Thread.sleep(1000);
                }

                if(testCount > 10) {

                    task.setStatu(ClawerStatus.FAIL.getStatus());
                    clawerTaskService.updateById(task);
                    break;
                }
            }
        } catch (Exception e) {

            LOGGER.error("out line has a error, container = {}, e = {}", container, e.getMessage());
            e.printStackTrace();
        }
    }
}
