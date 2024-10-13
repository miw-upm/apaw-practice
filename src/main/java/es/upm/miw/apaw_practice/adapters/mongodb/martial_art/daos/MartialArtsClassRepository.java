package es.upm.miw.apaw_practice.adapters.mongodb.martial_art.daos;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.entities.HotelEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.entities.MartialArtsClassEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MartialArtsClassRepository extends MongoRepository<MartialArtsClassEntity, String>{

    Optional<MartialArtsClassEntity> findByName(String name);

    void deleteByname(String name);
}
