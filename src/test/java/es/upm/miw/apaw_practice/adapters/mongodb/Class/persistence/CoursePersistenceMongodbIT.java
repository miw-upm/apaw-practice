package es.upm.miw.apaw_practice.adapters.mongodb.Class.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestConfig
public class CoursePersistenceMongodbIT {
    @Autowired
    private CoursePersistenceMongodb coursePersistenceMongodb;

    @Test
    void testDeleteByName(){
        this.coursePersistenceMongodb.delete("web");
        assertFalse(this.coursePersistenceMongodb.readAll()
                .anyMatch(myCourse ->
                        "web".equals(myCourse.getName())));
    }
}
