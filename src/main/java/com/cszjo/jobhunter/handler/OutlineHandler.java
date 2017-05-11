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

    private volatile boolean isOver = false;

    @Async
    public void saveJobInfos2Redis (CallableTaskContainer container) throws Exception {

        int jobCount = container.getTask().getJobCount();
        ClawerTask task = container.getTask();

        //现在已经消费的数据数量
        int nowCount = 0;
        //尝试从缓冲区中没拿到数据的次数，当10次没拿到数据，则标记该次任务爬取失败
        int testCount = 0;

        while(!isOver) {

            if (queue.size() > 0) {

                //从缓冲区中拿出数据
                Future<List<JobInfo>> future = queue.poll();
                List<JobInfo> jobInfoList = future.get();

                if(jobInfoList != null && jobInfoList.size() > 0) {

                    //将数据存储进Redis，并更新nowCount
                    int insertCount = jobsService.insertJobs(jobInfoList, task);
                    nowCount += insertCount;
                }

                task.setNowCount(nowCount);

                if(nowCount > jobCount) {
                    //如果nowCount > jobCount则表示爬取成功
                    task.setStatu(ClawerStatus.SUCCESS.getStatus());
                    clawerTaskService.updateById(task);
                    break;
                }

                clawerTaskService.updateById(task);
            } else {

                //缓冲区中无数据，testCount+1,且线程睡眠1s
                testCount++;
                Thread.sleep(1000);
            }

            if(testCount > 10) {

                //爬取失败
                task.setStatu(ClawerStatus.FAIL.getStatus());
                clawerTaskService.updateById(task);
                LOGGER.info("out line task is fail, task name = {}", task.getTaskName());
                break;
            }
        }

        isOver = true;
    }

    @Async
    public void outlineHandler(CallableTaskContainer container) throws Exception {

        if (container == null) {

            LOGGER.error("outline handler, callable task container is null");
        }

        int threadCount = container.getTask().getThreadCount();
        //从Container容器中拿出Callable的List
        List<Callable<List<JobInfo>>> callables = container.getCallableTaskList();
        //根据threadCount决定线程池大小
        es = Executors.newFixedThreadPool(threadCount);

        //start thread to clawer
        for (Callable<List<JobInfo>> callable : callables) {
            //线程睡眠，防止快速爬取，导致IP被封禁
            Thread.sleep(500);
            //如果消费者消费数量足够，则停止向缓冲区放入数据
            if(isOver)
                break;
            //将结果放入队列之中
            queue.offer(es.submit(callable));
        }
        //Callable的List已经全部放入缓冲区之中
        isOver = true;
    }

    public void setOver(boolean over) {
        isOver = over;
    }
}
