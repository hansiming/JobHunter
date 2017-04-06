package com.cszjo.jobhunter.clawer;

import com.cszjo.jobhunter.model.ClawerTask;
import org.jsoup.Jsoup;

import java.io.IOException;

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
        try {
            Jsoup.connect(task.getFullPath(this.page)).get();
        } catch (IOException e) {

        }
    }
}
