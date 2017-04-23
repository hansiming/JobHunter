package com.cszjo.jobhunter.service;

import java.util.List;
import java.util.UUID;

/**
 * Created by Han on 2017/4/23.
 */
public interface AnalysisService {

    void startAnalysis(UUID uuid, List<Integer> taskIds);
}
