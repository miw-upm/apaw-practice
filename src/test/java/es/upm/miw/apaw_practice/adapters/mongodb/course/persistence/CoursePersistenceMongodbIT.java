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
        Course course = this.coursePersistenceMongodb.read("Curso de Spring Framework");
        assertNotNull(course);
        assertEquals("Curso de Spring Framework", course.getTitle());
        assertEquals(false, course.getPaymentRequired());
        assertEquals(LocalDate.of(2023, 12, 30), course.getStartDate());
        assertEquals(LocalDate.of(2024, 06, 30), course.getEndDate());

        verifyTutoringSessions(course.getTutoringSessions());
        verifyUsers(course.getUsers());
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

    private void verifyTutoringSessions(List<TutoringSession> tutoringSessions) {
        assertEquals(this.expectedTutoringSessions.size(), tutoringSessions.size());
        for (int i = 0; i < this.expectedTutoringSessions.size(); i++) {
            assertEquals(this.expectedTutoringSessions.get(i).getTitle(), tutoringSessions.get(i).getTitle());
            assertEquals(this.expectedTutoringSessions.get(i).getDateTime(), tutoringSessions.get(i).getDateTime());
            assertEquals(this.expectedTutoringSessions.get(i).getPrice(), tutoringSessions.get(i).getPrice());
        }
    }

    private void verifyUsers(List<User> users) {
        assertEquals(expectedUsers.size(), users.size());
        for (int i = 0; i < expectedUsers.size(); i++) {
            assertEquals(expectedUsers.get(i).getFirstName(), users.get(i).getFirstName());
            assertEquals(expectedUsers.get(i).getEmail(), users.get(i).getEmail());
            assertEquals(expectedUsers.get(i).getRole(), users.get(i).getRole());
        }
    }

}
