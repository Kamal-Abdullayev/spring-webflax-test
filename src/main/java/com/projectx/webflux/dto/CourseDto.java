package com.projectx.webflux.dto;

import com.projectx.webflux.model.metadata.CourseMetadata;

public record CourseDto(
    String name,
    String description,
    Integer duration,
    String teacher,
    CourseMetadata courseMetadata
) {
}
