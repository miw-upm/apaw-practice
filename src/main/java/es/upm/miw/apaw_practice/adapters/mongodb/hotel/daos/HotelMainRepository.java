package es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelMainEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface HotelMainRepository extends MongoRepository<HotelMainEntity, String> {

    Optional<HotelMainEntity> findByName(String name);

    void deleteByName(String name);

}
