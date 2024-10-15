package es.upm.miw.apaw_practice.adapters.mongodb.course.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.course.entities.UserCourseEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class UserCourseRepositoryIT {

    @Autowired
    private UserCourseRepository userCourseRepository;

    @Test
    void findByEmail(){
        assertTrue(this.userCourseRepository.findByEmail("jose@gmail.com").isPresent());
        UserCourseEntity userEntity = this.userCourseRepository.findByEmail("jose@gmail.com").get();
        assertEquals("Jose", userEntity.getFirstName());
        assertEquals("jose@gmail.com", userEntity.getEmail());
        assertEquals(UserCourseEntity.TypeUser.STUDENT, userEntity.getRole());
        assertFalse(userEntity.toString().isEmpty());
    }
}
