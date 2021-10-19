package es.upm.miw.apaw_practice.domain.services.department;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.department.DepartmentEmployee;
import es.upm.miw.apaw_practice.domain.persistence_ports.department.DepartmentEmployeePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentEmployeeService {

    private final DepartmentEmployeePersistence departmentEmployeePersistence;

    @Autowired
    public DepartmentEmployeeService(DepartmentEmployeePersistence departmentEmployeePersistence) {
        this.departmentEmployeePersistence = departmentEmployeePersistence;
    }

    public DepartmentEmployee create(DepartmentEmployee departmentEmployee) {
        this.assertDniNotExist(departmentEmployee.getDni());
        return this.departmentEmployeePersistence.create(departmentEmployee);
    }

    public void assertDniNotExist(String dni) {
        if (this.departmentEmployeePersistence.existDni(dni)) {
            throw new ConflictException("Dni exist: " + dni);
        }
    }
}
