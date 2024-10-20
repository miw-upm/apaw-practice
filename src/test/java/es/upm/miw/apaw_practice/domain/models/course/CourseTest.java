package es.upm.miw.apaw_practice.domain.models.course;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestConfig
class CourseTest {

    @Test
    void testBuilder() {
        User user = User.build()
                .email("sofia@gmail.com")
                .firstName("Sofia")
                .role(User.TypeUser.STUDENT_TUTOR)
                .build();

        assertEquals("Sofia", user.getFirstName());
        assertEquals("sofia@gmail.com", user.getEmail());
        assertEquals(User.TypeUser.STUDENT_TUTOR, user.getRole());
        assertNotNull(user.toString());
    }

}
