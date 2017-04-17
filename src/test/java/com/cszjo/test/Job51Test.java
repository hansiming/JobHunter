package com.cszjo.test;

import com.cszjo.jobhunter.clawer.Job51Clawer;
import com.cszjo.jobhunter.model.JobInfo;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.FutureTask;

/**
 * Created by Administrator on 2017/4/17.
 */
public class Job51Test {

    @Test
    public void job51Test() throws Exception {

        FutureTask<List<JobInfo>> future = new FutureTask<List<JobInfo>>(new Job51Clawer( 1,"020000", "Java", ""));
        new Thread(future).start();
        System.out.println(future.get());
        Thread.sleep(3000);
    }
}
