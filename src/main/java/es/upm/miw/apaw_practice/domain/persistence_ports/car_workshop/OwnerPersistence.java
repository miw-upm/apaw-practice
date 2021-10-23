package es.upm.miw.apaw_practice.domain.persistence_ports.car_workshop;

import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities.OwnerEntity;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Owner;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerPersistence {
    Owner readByDni(String dni);

    OwnerEntity findByName(String name);
}
