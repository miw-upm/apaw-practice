package es.upm.miw.apaw_practice.adapters.mongodb.course.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.course.Course;
import es.upm.miw.apaw_practice.domain.models.course.TutoringSession;
import es.upm.miw.apaw_practice.domain.models.course.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestConfig
class CoursePersistenceMongodbIT {

    @Autowired
    private CoursePersistenceMongodb coursePersistenceMongodb;

    private List<TutoringSession> expectedTutoringSessions;
    private List<User> expectedUsers;

    @BeforeEach
    void setUp() {
        this.expectedTutoringSessions = getTutoringSessions();
        this.expectedUsers = getUsers();
    }

    @Test
    void testCourseEquals() {
        Course actualCourse = this.coursePersistenceMongodb.read("Curso de Spring Framework");
        assertNotNull(actualCourse);
        assertEquals("Curso de Spring Framework", actualCourse.getTitle());
        assertEquals(false, actualCourse.getPaymentRequired());
        assertEquals(LocalDate.of(2023, 12, 30), actualCourse.getStartDate());
        assertEquals(LocalDate.of(2024, 06, 30), actualCourse.getEndDate());

        System.out.println(actualCourse.toString());
        verifyTutoringSessions(actualCourse.getTutoringSessions());
        verifyUsers(actualCourse.getUsers());
    }

    private List<TutoringSession> getTutoringSessions() {
        return new ArrayList<>(Arrays.asList(
                new TutoringSession("Spring Framework Básico", LocalDateTime.of(2024, 12, 15, 18, 0), BigDecimal.valueOf(40.00)),
                new TutoringSession("Spring Boot Avanzado", LocalDateTime.of(2024, 12, 17, 18, 0), BigDecimal.valueOf(60.00)),
                new TutoringSession("Spring Security", LocalDateTime.of(2024, 12, 20, 18, 0), BigDecimal.valueOf(50.00)),
                new TutoringSession("Spring Cloud", LocalDateTime.of(2024, 12, 22, 18, 0), BigDecimal.valueOf(50.00))
        ));
    }

    private List<User> getUsers() {
        return new ArrayList<>(Arrays.asList(
                new User("Sofia", "sofia@gmail.com", User.TypeUser.STUDENT),
                new User("Raul", "raul@gmail.com", User.TypeUser.STUDENT),
                new User("Daniel", "daniel@gmail.com", User.TypeUser.STUDENT),
                new User("Lucia", "lucia@gmail.com", User.TypeUser.STUDENT),
                new User("Andrea", "andrea@gmail.com", User.TypeUser.STUDENT_TUTOR),
                new User("Pablo", "pablo@gmail.com", User.TypeUser.STUDENT_TUTOR)
        ));
    }

    // Métodos privados que verifican las listas

    private void verifyTutoringSessions(List<TutoringSession> actualTutoringSessions) {
        assertEquals(this.expectedTutoringSessions.size(), actualTutoringSessions.size());
        for (int i = 0; i < this.expectedTutoringSessions.size(); i++) {
            assertEquals(this.expectedTutoringSessions.get(i).getTitle(), actualTutoringSessions.get(i).getTitle());
            assertEquals(this.expectedTutoringSessions.get(i).getDateTime(), actualTutoringSessions.get(i).getDateTime());
            assertEquals(this.expectedTutoringSessions.get(i).getPrice(), actualTutoringSessions.get(i).getPrice());
        }
    }

    private void verifyUsers(List<User> actualUsers) {
        assertEquals(expectedUsers.size(), actualUsers.size());
        for (int i = 0; i < expectedUsers.size(); i++) {
            assertEquals(expectedUsers.get(i).getFirstName(), actualUsers.get(i).getFirstName());
            assertEquals(expectedUsers.get(i).getEmail(), actualUsers.get(i).getEmail());
            assertEquals(expectedUsers.get(i).getRole(), actualUsers.get(i).getRole());
        }
    }

}
