package com.projectx.webflux.service;

import com.projectx.webflux.dto.CourseDto;
import com.projectx.webflux.dto.StudentDto;
import com.projectx.webflux.dto.StudentListDto;
import com.projectx.webflux.model.Student;
import com.projectx.webflux.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseService courseService;

    public Flux<Student> findAll() {
        return studentRepository.findAll();
    }

    public Mono<StudentListDto> findAllWithCourses() {
        return studentRepository.findAll()
                .flatMap(
                        student -> {
                            List<Mono<CourseDto>> courseDtoList = student.getCourses()
                                    .stream()
                                    .map(courseId -> courseService.findById(UUID.fromString(courseId)))
                                    .toList();
                            return Flux.combineLatest(courseDtoList, objects -> {
                                List<CourseDto> coursees = Arrays.stream(objects)
                                        .map(o -> (CourseDto) o)
                                        .collect(Collectors.toList());
                                return new StudentDto(student.getName(), student.getEmail(), coursees);
                            });
                        }
                )
                .collectList()
                .map(StudentListDto::new);
    }

}
