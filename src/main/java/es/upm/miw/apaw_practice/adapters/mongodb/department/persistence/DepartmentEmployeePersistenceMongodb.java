package es.upm.miw.apaw_practice.adapters.mongodb.department.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.department.daos.DepartmentEmployeeRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.department.entities.DepartmentEmployeeEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.department.DepartmentEmployee;
import es.upm.miw.apaw_practice.domain.persistence_ports.department.DepartmentEmployeePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("departmentEmployeePersistence")
public class DepartmentEmployeePersistenceMongodb implements DepartmentEmployeePersistence {

    private DepartmentEmployeeRepository departmentEmployeeRepository;

    @Autowired
    public DepartmentEmployeePersistenceMongodb(DepartmentEmployeeRepository departmentEmployeeRepository) {
        this.departmentEmployeeRepository = departmentEmployeeRepository;
    }

    @Override
    public DepartmentEmployee create(DepartmentEmployee departmentEmployee) {
        return this.departmentEmployeeRepository
                .save(new DepartmentEmployeeEntity(departmentEmployee))
                .toDepartmentEmployee();
    }

    @Override
    public DepartmentEmployee read(String dni) {
        return this.departmentEmployeeRepository
                .findByDni(dni)
                .orElseThrow(() -> new NotFoundException("Department Employee DNI: " + dni))
                .toDepartmentEmployee();
    }

    @Override
    public boolean existDni(String dni) {
        return this.departmentEmployeeRepository
                .findByDni(dni)
                .isPresent();
    }

    @Override
    public DepartmentEmployee update(String dni, DepartmentEmployee departmentEmployee) {
        DepartmentEmployeeEntity departmentEmployeeEntity = this.departmentEmployeeRepository
                .findByDni(dni)
                .orElseThrow(() -> new NotFoundException("Employee dni: " + dni));
        departmentEmployeeEntity.fromDepartmentEmployee(departmentEmployee);
        return this.departmentEmployeeRepository
                .save(departmentEmployeeEntity)
                .toDepartmentEmployee();
    }

}
