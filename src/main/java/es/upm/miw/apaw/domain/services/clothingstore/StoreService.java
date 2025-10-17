package es.upm.miw.apaw.domain.services.clothingstore;

import es.upm.miw.apaw.domain.persistenceports.clothingstore.StorePersistence;
import org.springframework.stereotype.Service;
import es.upm.miw.apaw.domain.models.clothingstore.Store;
import java.util.UUID;


@Service
public class StoreService {

    private final StorePersistence storePersistence;

    public StoreService(StorePersistence storePersistence) {
        this.storePersistence = storePersistence;
    }

    public void delete(UUID id) {
        this.storePersistence.delete(id);
    }

    public Store patch(UUID id, Store partialStore){
        return this.storePersistence.update(id, partialStore);
    }
}
