package es.upm.miw.apaw_practice.adapters.mongodb.company.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.company.entities.CompanyEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.company.entities.ManagementEntity;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ManagementRepository extends MongoRepository<ManagementEntity, String> {
    Optional<ManagementEntity> findByName(String name);



}

