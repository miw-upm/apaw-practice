package es.upm.miw.apaw.adapters.mongodb.sports.academy.daos;

import es.upm.miw.apaw.adapters.mongodb.sports.academy.entities.ProfessorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface IProfessorRepository extends MongoRepository<ProfessorEntity, UUID> {
    Optional<ProfessorEntity> findByUserDtoId(UUID userDtoId);
}
