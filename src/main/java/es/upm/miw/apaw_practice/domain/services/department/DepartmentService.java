package es.upm.miw.apaw_practice.domain.services.department;

import es.upm.miw.apaw_practice.domain.models.department.Department;
import es.upm.miw.apaw_practice.domain.persistence_ports.department.DepartmentPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class DepartmentService {

    private final DepartmentPersistence departmentPersistence;

    @Autowired
    public DepartmentService(DepartmentPersistence departmentPersistence) {
        this.departmentPersistence = departmentPersistence;
    }

    public Stream<Department> readAll() {
        return this.departmentPersistence.readAll();
    }
}
