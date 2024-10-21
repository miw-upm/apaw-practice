package es.upm.miw.apaw_practice.domain.persistence_ports.company;

import es.upm.miw.apaw_practice.adapters.mongodb.company.entities.CompanyEntity;
import es.upm.miw.apaw_practice.domain.models.company.Company;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CompanyPersistence {
    Company findByCompanyname(String companyname);

    void updateIndustry(String companyname, String newIndustry);

    BigDecimal findHighestExpenseAmountByLocation(String location);

    List<String> findManagementNamesByIndustryAndDescription(String industry, String description);


}
