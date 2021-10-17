package es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.CompanyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepository extends MongoRepository<CompanyEntity, String> {
}
