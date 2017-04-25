package com.cszjo.jobhunter.model.analysis;

import java.util.List;

/**
 * Created by Han on 2017/4/25.
 */
public class ItemResult {

    private String name;
    private final String type = "bar";
    private List<Double> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public List<Double> getData() {
        return data;
    }

    public void setData(List<Double> data) {
        this.data = data;
    }
}
