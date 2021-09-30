package es.upm.miw.apaw_practice.adapters.mongodb.university.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.DegreeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DegreeRepository extends MongoRepository<DegreeEntity, String> {
    Optional<DegreeEntity> findByCode(Integer code);
}
