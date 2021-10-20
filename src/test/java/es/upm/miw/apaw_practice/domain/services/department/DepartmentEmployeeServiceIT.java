package es.upm.miw.apaw_practice.domain.services.department;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.department.DepartmentEmployeeIsActiveUpdating;
import es.upm.miw.apaw_practice.domain.persistence_ports.department.DepartmentEmployeePersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class DepartmentEmployeeServiceIT {

    @Autowired
    private DepartmentEmployeeService departmentEmployeeService;

    @Autowired
    private DepartmentEmployeePersistence departmentEmployeePersistence;

    @Test
    void testUpdateIsActive() {
        List<DepartmentEmployeeIsActiveUpdating> departmentEmployeeIsActiveUpdatings = List.of(
                new DepartmentEmployeeIsActiveUpdating("07523421A", true),
                new DepartmentEmployeeIsActiveUpdating("30523921H", false)
        );
        this.departmentEmployeeService.updateIsActive(departmentEmployeeIsActiveUpdatings.stream());
        assertEquals(true, this.departmentEmployeePersistence.read("07523421A").getActive());
        assertEquals(false, this.departmentEmployeePersistence.read("30523921H").getActive());
        departmentEmployeeIsActiveUpdatings = List.of(
                new DepartmentEmployeeIsActiveUpdating("07523421A",false),
                new DepartmentEmployeeIsActiveUpdating("30523921H", true)
        );
        this.departmentEmployeeService.updateIsActive(departmentEmployeeIsActiveUpdatings.stream());
    }
}
