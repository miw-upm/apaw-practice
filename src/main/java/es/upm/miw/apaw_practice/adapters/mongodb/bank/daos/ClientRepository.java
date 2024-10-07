package es.upm.miw.apaw_practice.adapters.mongodb.bank.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.bank.entities.ClientEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ClientRepository extends MongoRepository<ClientEntity, String> {

    Optional<ClientEntity> findByDni(String dni);
}
