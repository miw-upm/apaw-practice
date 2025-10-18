package es.upm.miw.apaw.domain.services.videogame;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.models.videogame.Company;
import es.upm.miw.apaw.domain.models.videogame.LikeList;
import es.upm.miw.apaw.domain.persistenceports.videogame.CompanyPersistence;
import es.upm.miw.apaw.domain.persistenceports.videogame.LikeListPersistence;
import es.upm.miw.apaw.domain.persistenceports.videogame.VideogamePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private final CompanyPersistence companyPersistence;
    private final LikeListPersistence LikeListPersistence;

    @Autowired
    public CompanyService(CompanyPersistence companyPersistence, LikeListPersistence likeListPersistence) {
        this.companyPersistence = companyPersistence;
        this.LikeListPersistence = likeListPersistence;
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
