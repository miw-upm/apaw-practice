package es.upm.miw.apaw.adapters.mongodb.clothingstore.persistence;

import es.upm.miw.apaw.adapters.mongodb.clothingstore.daos.StoreRepository;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.persistenceports.clothingstore.StorePersistence;
import org.springframework.stereotype.Repository;

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
}
