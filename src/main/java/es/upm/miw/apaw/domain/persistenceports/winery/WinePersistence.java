package es.upm.miw.apaw.domain.persistenceports.winery;

import es.upm.miw.apaw.domain.models.winery.Wine;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WinePersistence {

    Wine readById(UUID id);

    void delete(UUID id);

}
