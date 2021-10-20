package es.upm.miw.apaw_practice.adapters.mongodb.department.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.department.entities.CompanyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CompanyRepository extends MongoRepository<CompanyEntity, String> {
    Optional<CompanyEntity> findByCif(String cif);
}
