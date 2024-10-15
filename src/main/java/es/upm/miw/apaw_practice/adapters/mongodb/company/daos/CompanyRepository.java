package es.upm.miw.apaw_practice.adapters.mongodb.company.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.company.entities.CompanyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface CompanyRepository extends MongoRepository<CompanyEntity, String> {

    Optional<CompanyEntity> findByCompanyname(String companyname);
}