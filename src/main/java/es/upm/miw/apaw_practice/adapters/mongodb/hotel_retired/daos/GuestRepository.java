package es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.entities.GuestEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GuestRepository extends MongoRepository<GuestEntity, String> {
    Optional<GuestEntity> findByNif(String nif);

    void deleteByNif(String nif);
}
