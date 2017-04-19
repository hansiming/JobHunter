package com.cszjo.jobhunter.model.response;

import com.cszjo.jobhunter.serializer.BaseResponseSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Han on 2017/4/19.
 */
@Component(value = "addTaskResponse")
@Scope("prototype")
@JsonSerialize(using = BaseResponseSerializer.class)
public class AddTaskResponse extends BaseResponse {
}
