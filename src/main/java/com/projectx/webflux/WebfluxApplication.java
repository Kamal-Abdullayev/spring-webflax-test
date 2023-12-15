package com.projectx.webflux;

import com.projectx.webflux.model.Course;
import com.projectx.webflux.model.Student;
import com.projectx.webflux.model.metadata.CourseMetadata;
import com.projectx.webflux.model.metadata.SpringCourseMetadata;
import com.projectx.webflux.repository.CourseRepository;
import com.projectx.webflux.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@SpringBootApplication
public class WebfluxApplication implements CommandLineRunner {
	private final CourseRepository courseRepository;
	private final StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(WebfluxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Course course = Course.builder()
				.id(UUID.randomUUID())
				.name("Spring Boot 2")
				.description("Spring Boot")
				.duration(100)
				.teacher("Alex")
				.courseMetadata(SpringCourseMetadata.builder()
						.type("spring")
						.language("java")
						.github("https://github.com")
						.prerequisites(List.of("Java", "Spring"))
						.build())
				.isUpdated(false)
				.build();

		courseRepository.save(course).block();

		Student student = Student.builder()
				.id(UUID.randomUUID())
				.name("Alex")
				.email("a@a.com")
				.dateOfBirth(LocalDate.of(2000, 1, 1))
				.courses(Set.of(course.getId().toString()))
				.isUpdated(false)
				.build();

		studentRepository.save(student).block();
	}
}
