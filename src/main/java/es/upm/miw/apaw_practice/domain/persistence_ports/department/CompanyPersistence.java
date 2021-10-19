package es.upm.miw.apaw_practice.domain.persistence_ports.department;

import es.upm.miw.apaw_practice.domain.models.department.Company;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyPersistence {
    Company update(String cif, Company company);
    Company read(String cif);
    boolean existCif(String cif);
}
