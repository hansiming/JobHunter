package com.cszjo.jobhunter.model.analysis;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * Created by Han on 2017/4/23.
 */
public class AnalysisResult {

    private List<String> category = Lists.newArrayList("平均薪资", "最高薪资", "最低薪资");
    private List<String> taskNames = Lists.newArrayList();
    private List<ItemResult> results = Lists.newArrayList();

    public List<String> getCategory() {
        return category;
    }
    public List<String> getTaskNames() {
        return taskNames;
    }

    public List<ItemResult> getResults() {
        return results;
    }
}
