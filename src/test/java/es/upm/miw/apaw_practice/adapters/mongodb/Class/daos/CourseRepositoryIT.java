package es.upm.miw.apaw_practice.adapters.mongodb.Class.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.Class.entities.CourseEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class CourseRepositoryIT {
    @Autowired
    private CourseRepository courseRepository;

    @Test
    void testCreateAndRead(){
       // List<CourseEntity> list = this.courseRepository.findAll();
        //assertTrue(list.stream()
          //      .anyMatch(myCourse -> "web".equals(myCourse.getName())));
    }

    @Test
    void testFindByName(){
        // assertTrue(this.courseRepository.findByName("web").isPresent());
    }

    @Test
    void testDeleteBynName(){
        //this.courseRepository.deleteByName("web");
        //assertFalse(this.courseRepository.findAll().stream()
          //      .anyMatch(myCourse ->
            //            "web".equals(myCourse.getName()))
        //);
    }
}
