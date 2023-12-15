package com.projectx.webflux.controller;

import com.projectx.webflux.model.Student;
import com.projectx.webflux.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentRepository studentRepository;

    @GetMapping
    public Flux<Student> findAll() {
        return studentRepository.findAll();
    }





}
