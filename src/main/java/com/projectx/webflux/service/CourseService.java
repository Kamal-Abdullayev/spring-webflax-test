package com.projectx.webflux.service;

import com.projectx.webflux.dto.CourseDto;
import com.projectx.webflux.model.Course;
import com.projectx.webflux.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public Flux<Course> findAll() {
        return courseRepository.findAll();
    }

    public Mono<CourseDto> findById(UUID id) {
        return courseRepository.findById(id)
                .map(course -> new CourseDto(
                        course.getName(),
                        course.getDescription(),
                        course.getDuration(),
                        course.getTeacher(),
                        course.getCourseMetadata()));
    }

}
