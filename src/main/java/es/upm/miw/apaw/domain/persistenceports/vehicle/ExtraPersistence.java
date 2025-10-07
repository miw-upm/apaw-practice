package es.upm.miw.apaw.domain.persistenceports.vehicle;

import es.upm.miw.apaw.domain.models.vehicle.Extra;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExtraPersistence {

    Extra readByCategoryAndDescription(String category, String description);

    void delete(UUID id);

}
