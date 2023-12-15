package com.projectx.webflux.router;

import com.projectx.webflux.dto.StudentListDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@RequiredArgsConstructor
@Configuration
public class StudentRouter {
    public static final String STUDENT_ROOT = "/v1/students";
    public static final String STUDENT_COURSES_ROOT = "/courses";

    private final StudentHandler studentHandler;

    @Bean
    @RouterOperations(
            @RouterOperation(
                    path = STUDENT_COURSES_ROOT,
                    method = RequestMethod.GET,
                    operation = @Operation(
                            operationId = "findAllStudentWithCourses",
                            summary = "Find all students with courses",
                            description = "Find all students with courses",
                            responses = @ApiResponse(
                                    responseCode = "200",
                                    description = "All students with courses",
                                    content = @Content(
                                            schema = @Schema(implementation = StudentListDto.class))
                            )
                    )
            )
    )
    public RouterFunction<ServerResponse> findAllStudentWithCourses() {
        return RouterFunctions.nest(
                path(STUDENT_ROOT),
                RouterFunctions.route(
                        GET(STUDENT_COURSES_ROOT).and(accept(MediaType.APPLICATION_JSON)),
                                        studentHandler::handleFindAllStudentWithCourses)
        );
    }

}
