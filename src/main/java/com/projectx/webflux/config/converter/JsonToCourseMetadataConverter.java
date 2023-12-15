package com.projectx.webflux.config.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectx.webflux.model.metadata.CourseMetadata;
import io.r2dbc.postgresql.codec.Json;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.io.IOError;
import java.io.IOException;

@RequiredArgsConstructor
@ReadingConverter
@Slf4j
public class JsonToCourseMetadataConverter implements Converter<Json, CourseMetadata> {

    private final ObjectMapper objectMapper;
    @Override
    public CourseMetadata convert(Json source) {
        try {
            return objectMapper.readValue(source.asString(), CourseMetadata.class);
        } catch (IOException e) {
            log.error("Error while converting Json to CourseMetadata", e);
            throw new RuntimeException(e);
        }
    }
}
