package com.cszjo.jobhunter.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by Han on 2017/3/5.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ResponseInfo {

    SUCCESS("鉴权成功！"), USER_NAME_NOT_EXISTS("用户名不存在！"), PASSWORD_ERROR("密码错误！");

    private final String info;

    private ResponseInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return this.info;
    }

    @Override
    public String toString() {
        return this.info;
    }
}
