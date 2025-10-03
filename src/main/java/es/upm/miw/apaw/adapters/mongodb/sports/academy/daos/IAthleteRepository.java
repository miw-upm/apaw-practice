package es.upm.miw.apaw.adapters.mongodb.sports.academy.daos;

import es.upm.miw.apaw.adapters.mongodb.sports.academy.entities.AthleteEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface IAthleteRepository extends MongoRepository<AthleteEntity, UUID> {
}
