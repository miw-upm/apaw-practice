package es.upm.miw.apaw_practice.adapters.mongodb.department.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.department.daos.DepartmentRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.department.entities.DepartmentEntity;
import es.upm.miw.apaw_practice.domain.models.department.Department;
import es.upm.miw.apaw_practice.domain.persistence_ports.department.DepartmentPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("departmentPersistence")
public class DepartmentPersistenceMongodb implements DepartmentPersistence {

    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentPersistenceMongodb(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Stream<Department> readAll() {
        return this.departmentRepository
                .findAll().stream()
                .map(DepartmentEntity::toDepartment);
    }
}
