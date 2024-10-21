package es.upm.miw.apaw_practice.adapters.rest.course;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.course.Course;
import es.upm.miw.apaw_practice.domain.models.course.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestTestConfig
class CourseResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testReadCourseFound() {
        String title = "Curso de POO en Python";

        Course expectedCourse = createExpectedCourse();

        this.webTestClient
                .get()
                .uri(CourseResource.COURSES + CourseResource.TITTLE, title)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Course.class)
                .isEqualTo(expectedCourse);
    }

    @Test
    void testReadCourseNotFound() {
        String title = "Curso Inexistente";

        this.webTestClient
                .get()
                .uri(CourseResource.COURSES + CourseResource.TITTLE, title)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody()
                .jsonPath("$.message")
                .isEqualTo("Not Found Exception (404). Course title: " + title);
    }

    private Course createExpectedCourse() {

        List<User> users = new ArrayList<>();
        users.add(new User("Luis", "luis@gmail.com", User.TypeUser.STUDENT));
        users.add(new User("Maria", "maria@gmail.com", User.TypeUser.STUDENT));

        Course course = new Course("Curso de POO en Python", false, LocalDate.of(2023, 12, 15), LocalDate.of(2024, 06, 15));
        course.setUsers(users);

        return course;
    }
}
