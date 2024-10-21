package es.upm.miw.apaw_practice.domain.services.company;

import es.upm.miw.apaw_practice.domain.models.company.Company;
import es.upm.miw.apaw_practice.domain.persistence_ports.company.CompanyPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CompanyService {

    private final CompanyPersistence companyPersistence;

    @Autowired
    public CompanyService(CompanyPersistence companyPersistence) {
        this.companyPersistence = companyPersistence;
    }

    public Company findByCompanyname(String companyname) {
        return this.companyPersistence.findByCompanyname(companyname);
    }

    public void updateIndustry(String companyname, String newIndustry) {
        this.companyPersistence.updateIndustry(companyname, newIndustry);
    }
    public BigDecimal findHighestExpenseAmountByLocation(String location) {
        return this.companyPersistence.findHighestExpenseAmountByLocation(location);
    }
    }

