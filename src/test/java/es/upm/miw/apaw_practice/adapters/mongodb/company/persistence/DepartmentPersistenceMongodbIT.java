package es.upm.miw.apaw_practice.adapters.mongodb.company.persistence;

import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.company.Department;
import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class DepartmentPersistenceMongodbIT {

    @Autowired
    private DepartmentPersistenceMongodb departmentPersistenceMongodb;



    @Test
    void testUpdateEmployeeCountNotFound() {
        assertThrows(NotFoundException.class, () -> this.departmentPersistenceMongodb.updateEmployeeCount("NonExistentDepartment", 50));
    }


    @Test
    void testUpdateEmployeeCount() {
        Department updatedDepartment = this.departmentPersistenceMongodb.updateEmployeeCount("IT Support", 50);
        assertNotNull(updatedDepartment);
        assertEquals("IT Support", updatedDepartment.getDepartmentName());
        assertEquals(50, updatedDepartment.getEmployeeCount());
    }
}
