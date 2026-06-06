package es.upm.miw.apaw.domain.persistenceports.clothingstore;

import es.upm.miw.apaw.domain.models.clothingstore.Store;

import java.util.UUID;

public interface StorePersistence {

    void delete(UUID id);

    Store update(UUID id, Store store);

    Store patch(UUID id, Store partialStore);

    Store readById(UUID id);
}
