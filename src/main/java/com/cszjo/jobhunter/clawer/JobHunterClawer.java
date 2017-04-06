package com.cszjo.jobhunter.clawer;

import com.cszjo.jobhunter.model.ClawerTask;

/**
 * Created by Han on 2017/4/5.
 */
public class JobHunterClawer implements Runnable {

    private ClawerTask task;
    private int page;

    public JobHunterClawer(ClawerTask task, int page) {
        this.task = task;
        this.page = page;
    }

    @Override
    public void run() {

    }
}
