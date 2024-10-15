package es.upm.miw.apaw_practice.adapters.mongodb.gun_store.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.gun_store.daos.AccesoryRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.gun_store.entities.AccesoryEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.gun_store.Accesory;
import es.upm.miw.apaw_practice.domain.persistence_ports.gun_store.AccesoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("accesoryPersistence")
public class AccesoryPersistenceMongodb implements AccesoryPersistence {

    private final AccesoryRepository accesoryRepository;

    @Autowired
    public AccesoryPersistenceMongodb(AccesoryRepository accesoryRepository) {
        this.accesoryRepository = accesoryRepository;
    }


    @Override
    public Accesory update(Integer accesoryId, Accesory accesory) {
        AccesoryEntity accesoryEntity = this.accesoryRepository
                .findById(accesory.getAccesoryId())
                .orElseThrow(() -> new NotFoundException("Accesory Id: " + accesory.getAccesoryId()));
        accesoryEntity.fromAccesory(accesory);
        return this.accesoryRepository.save(accesoryEntity).toAccesory();
    }

    @Override
    public Accesory read(Integer accesoryId) {
        return this.accesoryRepository
                .findById(accesoryId)
                .orElseThrow(() -> new NotFoundException("AccesoryId: " + accesoryId))
                .toAccesory();
    }

}
