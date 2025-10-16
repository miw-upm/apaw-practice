package es.upm.miw.apaw.adapters.resources.warehouse;

import es.upm.miw.apaw.domain.models.warehouse.MovementOrder;
import es.upm.miw.apaw.domain.services.warehouse.MovementOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(MovementOrderResource.MOVEMENT_ORDERS)
public class MovementOrderResource {

    public static final String MOVEMENT_ORDERS = "/warehouse/movement-orders";

    private final MovementOrderService movementOrderService;

    @Autowired
    public MovementOrderResource(MovementOrderService movementOrderService) {
        this.movementOrderService = movementOrderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public MovementOrder create(@RequestBody MovementOrder movementOrder) {
        return this.movementOrderService.create(movementOrder);
    }

}
