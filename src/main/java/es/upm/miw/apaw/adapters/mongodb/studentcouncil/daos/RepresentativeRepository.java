package es.upm.miw.apaw.adapters.mongodb.studentcouncil.daos;

import es.upm.miw.apaw.adapters.mongodb.studentcouncil.entitites.RepresentativeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface RepresentativeRepository extends MongoRepository<RepresentativeEntity, UUID> {
}
