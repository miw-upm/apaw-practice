package es.upm.miw.apaw_practice.adapters.mongodb.training.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.training.entities.CourseEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class CourseRepositoryIT {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void testFindByIdentity() {
        assertTrue(this.courseRepository.findByIdentity("62003").isPresent());
        CourseEntity course = this.courseRepository.findByIdentity("62003").get();
        assertEquals(3, course.getLevel());
        assertEquals(0, new BigDecimal("426.32").compareTo(course.getPrice()));
    }
}
