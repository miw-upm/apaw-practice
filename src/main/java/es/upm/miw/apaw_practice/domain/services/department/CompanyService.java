package es.upm.miw.apaw_practice.domain.services.department;

import es.upm.miw.apaw_practice.domain.models.department.Company;
import es.upm.miw.apaw_practice.domain.persistence_ports.department.CompanyPersistence;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final CompanyPersistence companyPersistence;

    public CompanyService(CompanyPersistence companyPersistence) {
        this.companyPersistence = companyPersistence;
    }

    public Company update(String cif, Company company) {
        return this.companyPersistence.update(cif, company);
    }
}
