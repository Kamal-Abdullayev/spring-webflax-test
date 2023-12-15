package com.projectx.webflux.config.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectx.webflux.model.metadata.CourseMetadata;
import io.r2dbc.postgresql.codec.Json;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@RequiredArgsConstructor
@WritingConverter
@Slf4j
public class CourseMetadataToJsonConverter implements Converter<CourseMetadata, Json> {

    private final ObjectMapper objectMapper;

    @Override
    public Json convert(CourseMetadata courseMetadata) {
        try {
            return Json.of(objectMapper.writeValueAsBytes(courseMetadata));
        } catch (JsonProcessingException e) {
            log.error("Error while converting CourseMetadata to Json", e);
            throw new RuntimeException(e);
        }
    }
}
