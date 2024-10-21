package es.upm.miw.apaw_practice.domain.models.company;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class DepartmentTest {

    @Test
    void testBuilder() {
        Department department = Department.builder()
                .departmentName("Research and Development")
                .annualBudget(new BigDecimal("100000"))
                .employeeCount(50)
                .management(new Management(UUID.randomUUID().toString(),"John Doe", true))
                .build();

        assertNotNull(department);
        assertEquals("Research and Development", department.getDepartmentName());
        assertEquals(new BigDecimal("100000"), department.getAnnualBudget());
        assertEquals(50, department.getEmployeeCount());
        assertNotNull(department.getManagement());
        assertEquals("John Doe", department.getManagement().getName());
        assertTrue(department.getManagement().isActivated());
    }

    @Test
    void testBuilderWithOptionals() {
        Department department = Department.builder()
                .departmentName("IT Support")
                .annualBudget(new BigDecimal("75000"))
                .employeeCount(30)
                .management(new Management(UUID.randomUUID().toString(),"Jane Smith", true))
                .build();

        assertNotNull(department);
        assertEquals("IT Support", department.getDepartmentName());
        assertEquals(new BigDecimal("75000"), department.getAnnualBudget());
        assertEquals(30, department.getEmployeeCount());
        assertNotNull(department.getManagement());
        assertEquals("Jane Smith", department.getManagement().getName());
        assertTrue(department.getManagement().isActivated());
    }
}
