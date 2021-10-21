package es.upm.miw.apaw_practice.adapters.rest.Class;

import es.upm.miw.apaw_practice.adapters.mongodb.Class.daos.CourseRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.Class.entities.CourseEntity;
import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RestTestConfig
public class CourseRecourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void testDelete(){
        CourseEntity courseEntity = new CourseEntity("test",1,1);
        courseEntity.setId("idTest");
        this.courseRepository.save(courseEntity);

        assertTrue(this.courseRepository.existsById("idTest"));

        this.webTestClient
                .delete()
                .uri(CourseResource.theCourse + "/test")
                .exchange()
                .expectStatus()
                .isOk();
        assertFalse(this.courseRepository.existsById("idTest"));
    }
}
