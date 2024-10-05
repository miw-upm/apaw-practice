package es.upm.miw.apaw_practice.adapters.mongodb.university.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.UniversityEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UniversityRepository extends MongoRepository<UniversityEntity, String> {
    Optional<UniversityEntity> findByTopDomain(String topDomain);
}
