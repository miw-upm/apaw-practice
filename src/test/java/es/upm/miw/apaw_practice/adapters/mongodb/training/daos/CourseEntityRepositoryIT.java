package es.upm.miw.apaw_practice.adapters.mongodb.training.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class CourseEntityRepositoryIT {
    @Autowired
    private CourseRepository courseRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(courseRepository.findAll().stream()
                .anyMatch(course ->
                        "A".equals(course.getCourseLevel()) &&
                                course.getId() != null &&
                                3 == course.getCourseYear() &&
                                LocalDate.of(2022,6,3).equals(course.getStartDate()) &&
                                LocalDate.of(2022,6,5).equals(course.getFinishDate()) &&
                                4 == course.getTrainingItemEntities().size() &&
                                "Basic Psychology".equals(course.getTrainingItemEntities().get(0).getName())&&
                                5 == course.getParticipantEntities().size() &&
                                "Luis".equals(course.getParticipantEntities().get(0).getName())
                ));
    }
}
