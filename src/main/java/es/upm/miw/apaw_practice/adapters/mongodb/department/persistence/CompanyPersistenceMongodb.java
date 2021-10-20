package es.upm.miw.apaw_practice.adapters.mongodb.department.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.department.daos.CompanyRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.department.entities.CompanyEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.department.Company;
import es.upm.miw.apaw_practice.domain.persistence_ports.department.CompanyPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("companyPersistence")
public class CompanyPersistenceMongodb implements CompanyPersistence {

    private CompanyRepository companyRepository;

    @Autowired
    public CompanyPersistenceMongodb(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company update(String cif, Company company) {
        CompanyEntity companyEntity = this.companyRepository
                .findByCif(cif)
                .orElseThrow(() -> new NotFoundException("Company cif: " + cif));
        companyEntity.fromCompany(company);
        return this.companyRepository
                .save(companyEntity)
                .toCompany();
    }

    @Override
    public Company read(String cif) {
        return this.companyRepository
                .findByCif(cif)
                .orElseThrow(() -> new NotFoundException("Company cif: " + cif))
                .toCompany();
    }

    @Override
    public boolean existCif(String cif) {
        return this.companyRepository
                .findByCif(cif)
                .isPresent();
    }
}
