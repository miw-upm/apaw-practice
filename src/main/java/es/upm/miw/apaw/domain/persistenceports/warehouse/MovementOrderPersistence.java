package es.upm.miw.apaw.domain.persistenceports.warehouse;

import es.upm.miw.apaw.domain.models.warehouse.MovementOrder;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface MovementOrderPersistence {

    Stream<MovementOrder> readAll();
    MovementOrder read(UUID id);
    MovementOrder create(MovementOrder movementOrder);
    MovementOrder update(UUID id, MovementOrder movementOrder);
    void delete(UUID id);

}
