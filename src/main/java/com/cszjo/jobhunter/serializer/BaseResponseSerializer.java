package com.cszjo.jobhunter.serializer;

import com.cszjo.jobhunter.model.response.BaseResponse;
import com.cszjo.jobhunter.model.response.LoginResponse;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Created by Han on 2017/3/7.
 * 帮助实现LoginResponse中enum序列化实现
 */
public class BaseResponseSerializer extends JsonSerializer<BaseResponse> {

    @Override
    public void serialize(BaseResponse baseResponse, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("status");
        jsonGenerator.writeNumber(baseResponse.getStatus().getValue());
        jsonGenerator.writeFieldName("info");
        jsonGenerator.writeString(baseResponse.getInfo().getInfo());
        jsonGenerator.writeEndObject();
    }
}
