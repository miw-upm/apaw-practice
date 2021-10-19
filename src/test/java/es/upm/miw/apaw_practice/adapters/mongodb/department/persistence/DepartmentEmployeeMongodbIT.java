package es.upm.miw.apaw_practice.adapters.mongodb.department.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.department.DepartmentEmployee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class DepartmentEmployeeMongodbIT {

    @Autowired
    private DepartmentEmployeePersistenceMongodb departmentEmployeePersistenceMongodb;

    @Test
    void testReadNotFound() {
        assertThrows(NotFoundException.class, () -> this.departmentEmployeePersistenceMongodb.read("0"));
    }

    @Test
    void testDniNotExist() {
        assertFalse(this.departmentEmployeePersistenceMongodb.existDni("0"));
    }

    @Test
    void testDniExist() {
        assertTrue(this.departmentEmployeePersistenceMongodb.existDni("00523821F"));
    }

    @Test
    void testCreateAndRead() {
        DepartmentEmployee departmentEmployee =
                new DepartmentEmployee("08553821F", LocalDate.of(1980,4,11), true);
        this.departmentEmployeePersistenceMongodb.create(departmentEmployee);
        DepartmentEmployee departmentEmployeeBD = this.departmentEmployeePersistenceMongodb.read("08553821F");
        assertEquals(true, departmentEmployeeBD.getActive());
        assertEquals(1980, departmentEmployeeBD.getBirthday().getYear());
        assertEquals(4, departmentEmployeeBD.getBirthday().getMonthValue());
        assertEquals(11, departmentEmployeeBD.getBirthday().getDayOfMonth());
    }
}
