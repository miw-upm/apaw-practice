package es.upm.miw.apaw.adapters.mongodb.sports.academy.daos;

import es.upm.miw.apaw.adapters.mongodb.sports.academy.entities.LegalGuardianEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ILegalGuardianRepository extends MongoRepository<LegalGuardianEntity, UUID> {
}
