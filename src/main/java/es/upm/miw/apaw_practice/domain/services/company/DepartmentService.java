package es.upm.miw.apaw_practice.domain.services.company;

import es.upm.miw.apaw_practice.domain.models.company.Department;
import es.upm.miw.apaw_practice.domain.persistence_ports.company.DepartmentPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    private final DepartmentPersistence departmentPersistence;

    @Autowired
    public DepartmentService(DepartmentPersistence departmentPersistence) {
        this.departmentPersistence = departmentPersistence;
    }

    public Department updateEmployeeCount(String departmentname, Integer employeeCount) {
        return this.departmentPersistence.updateEmployeeCount(departmentname, employeeCount);
    }
}
