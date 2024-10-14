package es.upm.miw.apaw_practice.adapters.mongodb.course.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.course.entities.CourseEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class CourseRepositoryIT {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void findByTitle(){
        assertTrue(this.courseRepository.findByTitle("Curso de Introducción a Python").isPresent());
        CourseEntity courseEntity = this.courseRepository.findByTitle("Curso de Introducción a Python").get();
        assertEquals("Curso de Introducción a Python", courseEntity.getTitle());
        assertEquals(false, courseEntity.getPaymentRequired());
        assertEquals(LocalDate.of(2023, 12, 10), courseEntity.getStartDate());
        assertEquals(LocalDate.of(2024, 06, 10), courseEntity.getEndDate());
        assertEquals("Jose", courseEntity.getUsers().get(0).getFirstName());
        assertNull(courseEntity.getTutoringSessions());
        assertEquals("Introducción a Python", courseEntity.getVideos().get(0).getName());
        assertFalse(courseEntity.toString().isEmpty());
    }
}
