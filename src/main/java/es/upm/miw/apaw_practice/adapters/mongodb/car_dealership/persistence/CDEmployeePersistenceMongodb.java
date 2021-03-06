package es.upm.miw.apaw_practice.adapters.mongodb.car_dealership.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.car_dealership.daos.CDEmployeeRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car_dealership.entities.CDEmployeeEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.car_dealership.CDEmployee;
import es.upm.miw.apaw_practice.domain.persistence_ports.car_dealership.CDEmployeePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("cDEmployeePersistence")
public class CDEmployeePersistenceMongodb implements CDEmployeePersistence {

    private CDEmployeeRepository employeeRepository;

    @Autowired
    public CDEmployeePersistenceMongodb(CDEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void deleteById(String id) {
        this.employeeRepository.deleteById(id);
    }

    @Override
    public CDEmployee readById(String id) {
        return this.employeeRepository.readById(id)
                .orElseThrow(() -> new NotFoundException("Employee id: " + id))
                .toEmployee();
    }

    @Override
    public CDEmployee update(CDEmployee employee) {
        CDEmployeeEntity employeeEntity = this.employeeRepository
                .readById(employee.getId())
                .orElseThrow(() -> new NotFoundException("Employee id: " + employee.getId()));
        employeeEntity.fromEmployee(employee);
        return this.employeeRepository
                .save(employeeEntity)
                .toEmployee();
    }
}
