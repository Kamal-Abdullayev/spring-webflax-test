package com.projectx.webflux.router;

import com.projectx.webflux.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class StudentHandler {

    private final StudentService studentService;

    public Mono<ServerResponse> handleFindAllStudentWithCourses(ServerRequest serverRequest) {
        return studentService.findAllWithCourses()
                .flatMap(studentListDto -> ServerResponse.ok().bodyValue(studentListDto))
                .switchIfEmpty(
                        Mono.defer(() -> Mono.error(new RuntimeException("No students found")))
                );

    }

}
