package es.upm.miw.apaw.domain.services.warehouse;

import es.upm.miw.apaw.domain.models.warehouse.MovementOrder;
import es.upm.miw.apaw.domain.persistenceports.warehouse.MovementOrderPersistence;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class MovementOrderService {

    private final MovementOrderPersistence movementOrderPersistence;
    private final UserRestClient userRestClient;

    @Autowired
    public MovementOrderService(MovementOrderPersistence movementOrderPersistence,
                                UserRestClient userRestClient) {
        this.movementOrderPersistence = movementOrderPersistence;
        this.userRestClient = userRestClient;
    }

    public MovementOrder create(MovementOrder movementOrder) {

        UUID userId = movementOrder.getUser().getId();
        this.userRestClient.readById(userId);

        movementOrder.setId(UUID.randomUUID());
        movementOrder.setRegistrationDate(LocalDateTime.now());

        return this.movementOrderPersistence.create(movementOrder);
    }

}