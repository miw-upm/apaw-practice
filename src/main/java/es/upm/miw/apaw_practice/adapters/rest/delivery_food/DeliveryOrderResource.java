package es.upm.miw.apaw_practice.adapters.rest.delivery_food;

import es.upm.miw.apaw_practice.domain.models.delivery_food.DeliveryOrder;
import es.upm.miw.apaw_practice.domain.services.delivery_food.DeliveryOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(DeliveryOrderResource.DELIVERY_ORDER)
public class DeliveryOrderResource {

    public static final String DELIVERY_ORDER = "/delivery_food/order";

    @Autowired
    private final DeliveryOrderService deliveryOrderService;

    public DeliveryOrderResource(DeliveryOrderService deliveryOrderService) {
        this.deliveryOrderService = deliveryOrderService;
    }

    @PostMapping
    public ResponseEntity<DeliveryOrder> create(@RequestBody DeliveryOrder deliveryOrder){
        DeliveryOrder deliveryOrderSave = deliveryOrderService.create(deliveryOrder);
        return new ResponseEntity<>(deliveryOrderSave, HttpStatus.CREATED);
    }
}
