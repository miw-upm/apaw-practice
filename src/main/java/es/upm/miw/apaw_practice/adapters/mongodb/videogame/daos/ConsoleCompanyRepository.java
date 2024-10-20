package es.upm.miw.apaw_practice.adapters.mongodb.videogame.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.videogame.entities.ConsoleCompanyrEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ConsoleCompanyRepository extends MongoRepository<ConsoleCompanyrEntity, String> {
    Optional<ConsoleCompanyrEntity> deleteByCompanyInformation(String companyInformation);
    Optional<ConsoleCompanyrEntity> findByCompanyInformation(String companyInformation);
}