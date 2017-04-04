package com.cszjo.jobhunter.model.response;

import com.cszjo.jobhunter.model.response.ResponseInfo;
import com.cszjo.jobhunter.model.response.ResponseStatus;

/**
 * Created by Han on 2017/3/5.
 */
public class BaseResponse {

    private ResponseStatus status;
    private ResponseInfo info;

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public ResponseInfo getInfo() {
        return info;
    }

    public void setInfo(ResponseInfo info) {
        this.info = info;
    }
}
