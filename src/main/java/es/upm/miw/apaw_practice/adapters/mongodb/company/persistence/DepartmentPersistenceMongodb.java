package es.upm.miw.apaw_practice.adapters.mongodb.company.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.company.daos.DepartmentRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.company.entities.DepartmentEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.company.Department;
import es.upm.miw.apaw_practice.domain.persistence_ports.company.DepartmentPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("DepartmentPersistence")
public class DepartmentPersistenceMongodb implements DepartmentPersistence {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentPersistenceMongodb(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department updateEmployeeCount(String departmentName, Integer employeeCount) {
        List<DepartmentEntity> departments = this.departmentRepository.findByDepartmentName(departmentName);
        if (departments.isEmpty()) {
            throw new NotFoundException("Department name: " + departmentName);
        }

        DepartmentEntity departmentEntity = departments.get(0);
        departmentEntity.setEmployeeCount(employeeCount);
        return this.departmentRepository.save(departmentEntity).toDepartment();
    }


}



