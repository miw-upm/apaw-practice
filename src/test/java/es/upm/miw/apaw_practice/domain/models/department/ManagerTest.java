package es.upm.miw.apaw_practice.domain.models.department;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class ManagerTest {

    private Manager manager;

    @BeforeEach
    void initializeManager() {
        DepartmentEmployee[] departmentEmployeeList = new DepartmentEmployee[]{
                new DepartmentEmployee("00523821F", LocalDate.of(1984, 8, 27), false)
        };

        manager = Manager.builder()
                .email("nombre@gmail.com")
                .phoneNumber("432123543")
                .experienceYears(6)
                .departmentEmployees(Arrays.asList(departmentEmployeeList))
                .build();
    }

    @Test
    void testToString() {
        assertEquals("Manager{" +
                "experienceYears=" + 6 +
                ", phoneNumber='" + "432123543" + '\'' +
                ", email='" + "nombre@gmail.com" + '\'' +
                ", departmentEmployees=" + "[DepartmentEmployee{dni='00523821F', birthday=1984-08-27, isActive=false}]" +
                '}', manager.toString());
    }
}
