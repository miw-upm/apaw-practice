package es.upm.miw.apaw_practice.adapters.mongodb.department.persistence;

import es.upm.miw.apaw_practice.domain.persistence_ports.department.DepartmentEmployeePersistence;
import org.springframework.beans.factory.annotation.Autowired;

public class DepartmentEmployeePersistenceMongodb implements DepartmentEmployeePersistence {

    private DepartmentEmployeePersistence departmentEmployeePersistence;

    @Autowired
    public DepartmentEmployeePersistenceMongodb(DepartmentEmployeePersistence departmentEmployeePersistence) {
        this.departmentEmployeePersistence = departmentEmployeePersistence;
    }
}
