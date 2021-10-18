package es.upm.miw.apaw_practice.adapters.mongodb.department.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.department.entities.DepartmentEmployeeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class DepartmentEmployeeRepositoryIT {

    @Autowired
    private DepartmentEmployeeRepository departmentEmployeeRepository;

    @Test
    void testFindByDni() {
        assertTrue(this.departmentEmployeeRepository.findByDni("00523821F").isPresent());
        DepartmentEmployeeEntity departmentEmployeeEntity = this.departmentEmployeeRepository
                .findByDni("00523821F")
                .get();
        assertEquals(false, departmentEmployeeEntity.getActive());
        assertEquals(1984, departmentEmployeeEntity.getBirthday().getYear());
        assertEquals(8, departmentEmployeeEntity.getBirthday().getMonth());
        assertEquals(27, departmentEmployeeEntity.getBirthday().getDayOfMonth());
    }
}
