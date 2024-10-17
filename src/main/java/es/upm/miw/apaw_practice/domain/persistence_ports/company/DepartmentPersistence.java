package es.upm.miw.apaw_practice.domain.persistence_ports.company;

import es.upm.miw.apaw_practice.domain.models.company.Department;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentPersistence {

    Department updateEmployeeCount(String departmentname, Integer employeeCount);
}
