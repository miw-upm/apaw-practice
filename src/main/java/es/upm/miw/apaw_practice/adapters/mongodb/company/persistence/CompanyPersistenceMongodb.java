package es.upm.miw.apaw_practice.adapters.mongodb.company.persistence;


import es.upm.miw.apaw_practice.adapters.mongodb.company.daos.CompanyRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.company.entities.CompanyEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.company.Company;
import es.upm.miw.apaw_practice.domain.persistence_ports.company.CompanyPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("CompanyPersistence")
public class CompanyPersistenceMongodb implements CompanyPersistence{

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyPersistenceMongodb(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company findByCompanyname(String companyname) {
        return this.companyRepository.findByCompanyname(companyname)
                .orElseThrow(() -> new NotFoundException("Company name: " + companyname))
                .toCompany();
    }

    @Override
    public void updateIndustry(String companyname, String newIndustry) {
        CompanyEntity companyEntity = this.companyRepository.findByCompanyname(companyname)
                .orElseThrow(() -> new NotFoundException("Company name: " + companyname));
        companyEntity.setIndustry(newIndustry);
        this.companyRepository.save(companyEntity);
    }


}
