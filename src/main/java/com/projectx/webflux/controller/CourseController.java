package com.projectx.webflux.controller;

import com.projectx.webflux.model.Course;
import com.projectx.webflux.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/")
    public Flux<Course> getCourses() {
        return courseService.findAll();
    }


}
