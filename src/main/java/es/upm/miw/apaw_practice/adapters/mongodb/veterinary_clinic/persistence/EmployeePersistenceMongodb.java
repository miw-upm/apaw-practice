package es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.daos.EmployeeRepository;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Employee;
import es.upm.miw.apaw_practice.domain.persistence_ports.veterinary_clinic.EmployeePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("employeePersistence")
public class EmployeePersistenceMongodb implements EmployeePersistence {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeePersistenceMongodb(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee readByName(String name) {
        return this.employeeRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Employee name: " + name))
                .toEmployee();
    }

    @Override
    public void deleteByName(String name) {
        this.employeeRepository.deleteByName(name);
    }
}