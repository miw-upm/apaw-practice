package es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.daos.OwnerRepository;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Owner;
import es.upm.miw.apaw_practice.domain.persistence_ports.car_workshop.OwnerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("ownerPersistence")
public class OwnerPersistenceMongodb implements OwnerPersistence {

    private final OwnerRepository ownerRepository;

    @Autowired
    OwnerPersistenceMongodb(OwnerRepository ownerRepository){
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Owner readByDni(String dni){
        return this.ownerRepository.findByDni(dni)
                .orElseThrow(() -> new NotFoundException("Owner dni: " + dni))
                .toOwner();
    };
}
