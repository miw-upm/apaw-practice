package es.upm.miw.apaw_practice.adapters.mongodb.course.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.course.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class UserCoursePersistenceMongodbIT {

    @Autowired
    private UserCoursePersistenceMongodb userCoursePersistenceMongodb;

    private User user;

    @BeforeEach
    void setUp() {
        user = createTestUser();
    }

    @Test
    void testUserCourseCreate() {
        User createdUser = this.userCoursePersistenceMongodb.create(user);

        assertEquals("John", createdUser.getFirstName());
        assertEquals("john.doe@example.com", createdUser.getEmail());
        assertEquals(User.TypeUser.STUDENT, createdUser.getRole());
    }

    private User createTestUser() {
        User userTemp = new User();
        userTemp.setFirstName("John");
        userTemp.setEmail("john.doe@example.com");
        userTemp.setRole(User.TypeUser.STUDENT);
        return userTemp;
    }
    @Test
    void testEmailsOfTitleTutoringSession() {
        List<String> emails = this.userCoursePersistenceMongodb.emailsOfTitleTutoringSession("Spring Security");
        assertNotNull(emails);
        assertEquals(6, emails.size());
        assertTrue(emails.contains("sofia@gmail.com"));
        assertTrue(emails.contains("raul@gmail.com"));
        assertTrue(emails.contains("daniel@gmail.com"));
        assertTrue(emails.contains("lucia@gmail.com"));
        assertTrue(emails.contains("andrea@gmail.com"));
        assertTrue(emails.contains("pablo@gmail.com"));
    }

}
