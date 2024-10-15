package es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.ConsoleCompanyrEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConsoleCompanyRepository extends MongoRepository<ConsoleCompanyrEntity, String> {
}
