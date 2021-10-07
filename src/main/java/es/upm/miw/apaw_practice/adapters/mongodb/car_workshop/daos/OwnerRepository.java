package es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities.OwnerEntity;

import java.util.Optional;

public interface OwnerRepository {
    Optional<OwnerEntity> findByDni(String dni);
}
