package es.upm.miw.apaw_practice.adapters.mongodb.bank.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.bank.entities.BranchOfficeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BranchOfficeRepository extends MongoRepository<BranchOfficeEntity, String> {

    Optional<BranchOfficeEntity> findByBuildingName(String buildingName);
}
