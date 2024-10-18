package es.upm.miw.apaw_practice.domain.services.company;

import es.upm.miw.apaw_practice.domain.models.company.Department;
import es.upm.miw.apaw_practice.domain.persistence_ports.company.DepartmentPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class DepartmentServiceIT {

    @Mock
    private DepartmentPersistence departmentPersistence;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateEmployeeCount() {
        String departmentName = "IT Support";
        int newEmployeeCount = 50;
        Department updatedDepartment = new Department(departmentName, null, newEmployeeCount, null);

        when(departmentPersistence.updateEmployeeCount(departmentName, newEmployeeCount)).thenReturn(updatedDepartment);

        Department result = departmentService.updateEmployeeCount(departmentName, newEmployeeCount);

        assertNotNull(result);
        assertEquals(departmentName, result.getDepartmentName());
        assertEquals(newEmployeeCount, result.getEmployeeCount());
        verify(departmentPersistence, times(1)).updateEmployeeCount(departmentName, newEmployeeCount);
    }
}
