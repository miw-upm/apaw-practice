package es.upm.miw.apaw_practice.domain.persistence_ports.company;

import es.upm.miw.apaw_practice.domain.models.company.Company;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyPersistence {
    Company findByCompanyname(String companyname);

    void updateIndustry(String companyname, String newIndustry);
}
