package es.upm.miw.apaw.domain.persistenceports.clothingstore;
import java.util.UUID;
import es.upm.miw.apaw.domain.models.clothingstore.Store;

public interface StorePersistence {
    void delete(UUID id);
    Store update(UUID id, Store partialStore);
    Store readById(UUID id);
}
