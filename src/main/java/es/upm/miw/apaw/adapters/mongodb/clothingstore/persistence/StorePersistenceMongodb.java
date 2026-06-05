package es.upm.miw.apaw.adapters.mongodb.clothingstore.persistence;

import es.upm.miw.apaw.adapters.mongodb.clothingstore.daos.StoreRepository;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.persistenceports.clothingstore.StorePersistence;
import org.springframework.stereotype.Repository;
import es.upm.miw.apaw.adapters.mongodb.clothingstore.entities.StoreEntity;
import es.upm.miw.apaw.domain.models.clothingstore.Store;

import java.util.UUID;

@Repository
public class StorePersistenceMongodb implements StorePersistence {

    private final StoreRepository storeRepository;

    public StorePersistenceMongodb(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public void delete(UUID id) {
        if (!this.storeRepository.existsById(id)) {
            throw new NotFoundException("Store not found: " + id);
        }
        this.storeRepository.deleteById(id);
    }
    @Override
    public Store readById(UUID id) {
        return storeRepository.findById(id)
                .map(StoreEntity::toStore)
                .orElseThrow(() -> new NotFoundException("Store not found: " + id));
    }

    @Override
    public Store update(UUID id, Store partial) {
        StoreEntity entity = storeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Store not found: " + id));

        if (partial.getName() != null) {
            entity.setName(partial.getName());
        }
        if (partial.getAddress() != null) {
            entity.setAddress(partial.getAddress());
        }

        return storeRepository.save(entity).toStore();
    }
}
