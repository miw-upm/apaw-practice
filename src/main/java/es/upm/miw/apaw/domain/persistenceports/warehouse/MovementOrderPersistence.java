package es.upm.miw.apaw.domain.persistenceports.warehouse;

import es.upm.miw.apaw.domain.models.warehouse.MovementOrder;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MovementOrderPersistence {

    Optional<MovementOrder> readById(UUID id);
    MovementOrder create(MovementOrder movementOrder);

}