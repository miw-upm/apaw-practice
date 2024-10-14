package es.upm.miw.apaw_practice.domain.persistence_ports.veterinary_clinic;

import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeePersistence {

    Employee readByName(String name);

    void deleteByName(String name);
}