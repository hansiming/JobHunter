package com.cszjo.jobhunter.serializer;

import com.cszjo.jobhunter.model.LoginResponse;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Created by Han on 2017/3/7.
 * 帮助实现LoginResponse中enum序列化实现
 */
public class LoginResponseSerializer extends JsonSerializer<LoginResponse> {

    @Override
    public void serialize(LoginResponse loginResponse, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("status");
        jsonGenerator.writeNumber(loginResponse.getStatus().getValue());
        jsonGenerator.writeFieldName("info");
        jsonGenerator.writeString(loginResponse.getInfo().getInfo());
        jsonGenerator.writeEndObject();
    }
}
