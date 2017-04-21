package com.cszjo.jobhunter.model;

/**
 * Created by Han on 2017/4/21.
 */
public enum ClawerTemplate {

    LAGOU(99), Job51(98), CHINA_HR(97);

    private int id;

    ClawerTemplate(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
