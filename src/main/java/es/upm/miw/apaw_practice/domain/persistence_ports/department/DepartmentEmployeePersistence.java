package es.upm.miw.apaw_practice.domain.persistence_ports.department;

import es.upm.miw.apaw_practice.domain.models.department.DepartmentEmployee;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentEmployeePersistence {

    DepartmentEmployee create(DepartmentEmployee departmentEmployee);

    DepartmentEmployee read(String dni);

    boolean existDni(String dni);
}
