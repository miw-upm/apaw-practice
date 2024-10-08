package es.upm.miw.apaw_practice.adapters.mongodb.night_life.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.night_life.daos.OwnerNightLifeRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.night_life.entities.OwnerEntity;
import es.upm.miw.apaw_practice.domain.models.night_life.Owner;
import es.upm.miw.apaw_practice.domain.persistence_ports.night_life.OwnerNightLifePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("ownerNightLifePersistence")
public class OwnerNightLifePersistenceMongodb implements OwnerNightLifePersistence {
    private final OwnerNightLifeRepository ownerNightLifeRepository;

    @Autowired
    public OwnerNightLifePersistenceMongodb(OwnerNightLifeRepository ownerNightLifeRepository) {
        this.ownerNightLifeRepository = ownerNightLifeRepository;
    }

    @Override
    public Owner create(Owner owner) {
        return this.ownerNightLifeRepository
                .save(new OwnerEntity(owner))
                .toOwner();
    }
}
