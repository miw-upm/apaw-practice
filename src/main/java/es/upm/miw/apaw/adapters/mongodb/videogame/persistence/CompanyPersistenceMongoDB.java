package es.upm.miw.apaw.adapters.mongodb.videogame.persistence;

import es.upm.miw.apaw.adapters.mongodb.shop.entities.ArticleEntity;
import es.upm.miw.apaw.adapters.mongodb.videogame.daos.CompanyRepository;
import es.upm.miw.apaw.adapters.mongodb.videogame.entities.CompanyEntity;
import es.upm.miw.apaw.domain.models.shop.Article;
import es.upm.miw.apaw.domain.models.videogame.Company;
import es.upm.miw.apaw.domain.models.videogame.Videogame;
import es.upm.miw.apaw.domain.persistenceports.videogame.CompanyPersistence;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("CompanyPersistence")
public class CompanyPersistenceMongoDB implements CompanyPersistence {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyPersistenceMongoDB(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company create(Company company) {
        return this.companyRepository
                .save(new CompanyEntity(company))
                .toCompany();
    }

    @Override
    public boolean existDenomination(String denomination) {
        return this.companyRepository
                .findByDenomination(denomination)
                .isPresent();
    }


    @Override
    public Stream<Company> readAll() {
        return this.companyRepository
                .findAll().stream()
                .map(CompanyEntity::toCompany);
    }


}
