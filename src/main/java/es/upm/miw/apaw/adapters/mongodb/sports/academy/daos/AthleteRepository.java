package es.upm.miw.apaw.adapters.mongodb.sports.academy.daos;

import es.upm.miw.apaw.adapters.mongodb.sports.academy.entities.AthleteEntity;
import es.upm.miw.apaw.adapters.mongodb.sports.academy.entities.LegalGuardianEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AthleteRepository extends MongoRepository<AthleteEntity, UUID> {
    Optional<AthleteEntity> findByUserDtoId(UUID userDtoId);
    List<AthleteEntity> findByLegalGuardiansIn(List<LegalGuardianEntity> legalGuardians);
}
