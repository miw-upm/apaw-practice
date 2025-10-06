package es.upm.miw.apaw.adapters.mongodb.videogame.daos;

import es.upm.miw.apaw.adapters.mongodb.videogame.entities.CompanyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface CompanyRepository extends MongoRepository<CompanyEntity, UUID> {
}
