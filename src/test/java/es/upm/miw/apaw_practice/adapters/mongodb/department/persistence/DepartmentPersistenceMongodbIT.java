package es.upm.miw.apaw_practice.adapters.mongodb.department.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@TestConfig
public class DepartmentPersistenceMongodbIT {
    @Autowired
    private DepartmentPersistenceMongodb departmentPersistenceMongodb;

    @Test
    void testReadAll() {
        long departmentCount = this.departmentPersistenceMongodb.readAll().count();
        assertEquals(4, departmentCount);
        assertNotEquals(9, departmentCount);
    }
}
