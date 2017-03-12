package com.cszjo.jobhunter.model;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by Han on 2017/3/12.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ClawerStatus {

    /**
     * IN_CLAWERING正在爬取
     */
    IN_CLAWERING(0), FAIL(1), SUCCESS(2);

    private final int status;

    private ClawerStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }
}
