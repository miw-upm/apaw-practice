package es.upm.miw.apaw_practice.domain.services.veterinary_clinic;

import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Employee;
import es.upm.miw.apaw_practice.domain.persistence_ports.veterinary_clinic.EmployeePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeePersistence employeePersistence;

    @Autowired
    public EmployeeService(EmployeePersistence employeePersistence) {
        this.employeePersistence = employeePersistence;
    }

    public Employee read(String name) {
        return this.employeePersistence.readByName(name);
    }

    public void delete(String name) {
        this.employeePersistence.deleteByName(name);
    }
}