package com.ruiling.cocoon.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruiling.cocoon.infrastructure.exception.AppException;
import com.ruiling.cocoon.infrastructure.exception.ExceptionCode;

import java.util.Optional;

public class CocoonObjectMapper extends ObjectMapper {
    @Override
    public String writeValueAsString(Object value) {
        try {
            return super.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new AppException(ExceptionCode.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @Override
    public <T> T readValue(String content, Class<T> valueType) {
        try {
            return super.readValue(content, valueType);
        } catch (Exception e) {
            throw new AppException(ExceptionCode.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    public <T> Optional<T> readOptionalValue(String content, Class<T> valueType) {
        return Optional.ofNullable(content).map(value -> readValue(value, valueType));
    }

    @Override
    public <T> T readValue(String content, TypeReference<T> valueTypeRef) {
        if (content == null) {
            return null;
        }
        try {
            return super.readValue(content, valueTypeRef);
        } catch (Exception e) {
            throw new AppException(ExceptionCode.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
