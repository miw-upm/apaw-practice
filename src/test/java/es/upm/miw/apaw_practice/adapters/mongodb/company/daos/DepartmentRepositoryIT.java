package es.upm.miw.apaw_practice.adapters.mongodb.company.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.company.entities.DepartmentEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class DepartmentRepositoryIT {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    void testFindByDepartmentName() {
        List<DepartmentEntity> departments = this.departmentRepository.findByDepartmentName("IT Support");
        assertFalse(departments.isEmpty());
        DepartmentEntity department = departments.get(0);
        assertEquals("IT Support", department.getDepartmentName());
        assertNotNull(department.getManagementEntity());
    }

    @Test
    void testFindAll() {
        List<DepartmentEntity> departments = this.departmentRepository.findAll();
        assertNotNull(departments);
        assertTrue(departments.size() > 0);
    }

    @Test
    void testSaveDepartment() {
        DepartmentEntity departmentEntity = new DepartmentEntity("New Department", new BigDecimal("80000.0"), 15, null);
        DepartmentEntity savedDepartment = this.departmentRepository.save(departmentEntity);
        assertNotNull(savedDepartment);
        assertEquals("New Department", savedDepartment.getDepartmentName());
        assertEquals(15, savedDepartment.getEmployeeCount());
    }
}

