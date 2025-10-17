package es.upm.miw.apaw.domain.services.videogame;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.models.videogame.Company;
import es.upm.miw.apaw.domain.persistenceports.videogame.CompanyPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    private final CompanyPersistence companyPersistence;

    @Autowired
    public CompanyService(CompanyPersistence companyPersistence) {
        this.companyPersistence = companyPersistence;
    }

    public void assertDenominationNotExist(String name) {
        if (this.companyPersistence.existDenomination(name)) {
            throw new ConflictException("Name exist: " + name);
        }
    }

    public Company create(Company company) {
        this.assertDenominationNotExist(company.getDenomination());
        return this.companyPersistence.create(company);

    }
}
