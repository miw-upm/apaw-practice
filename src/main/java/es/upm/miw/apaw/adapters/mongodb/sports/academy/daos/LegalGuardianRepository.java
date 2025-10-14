package es.upm.miw.apaw.adapters.mongodb.sports.academy.daos;

import es.upm.miw.apaw.adapters.mongodb.sports.academy.entities.LegalGuardianEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public interface LegalGuardianRepository extends MongoRepository<LegalGuardianEntity, UUID> {
    Optional<LegalGuardianEntity> findByUserDtoId(UUID userDtoId);
    Stream<LegalGuardianEntity> findBySecondMobile(String secondMobile);
}
