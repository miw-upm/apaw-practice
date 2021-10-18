package es.upm.miw.apaw_practice.adapters.mongodb.department.persistence;

import es.upm.miw.apaw_practice.domain.persistence_ports.department.DepartmentPersistence;
import org.springframework.beans.factory.annotation.Autowired;

public class DepartmentPersistenceMongodb implements DepartmentPersistence {

    private DepartmentPersistence departmentPersistence;

    @Autowired
    public DepartmentPersistenceMongodb(DepartmentPersistence departmentPersistence) {
        this.departmentPersistence = departmentPersistence;
    }
}
