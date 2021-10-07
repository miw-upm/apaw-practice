package es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.daos;

import es.upm.miw.apaw_practice.domain.models.car_workshop.Owner;

public interface OwnerRepository {
    Owner findByDni(String dni);
}
