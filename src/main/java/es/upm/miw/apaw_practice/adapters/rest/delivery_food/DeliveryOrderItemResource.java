package es.upm.miw.apaw_practice.adapters.rest.delivery_food;

import es.upm.miw.apaw_practice.domain.models.delivery_food.DeliveryOrderItem;
import es.upm.miw.apaw_practice.domain.services.delivery_food.DeliveryOrderItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(DeliveryOrderItemResource.DELIVERY_ORDER_ITEM)
public class DeliveryOrderItemResource {

    public static final String DELIVERY_ORDER_ITEM = "/delivery_food/order_item";
    public static final String DELIVERY_ORDER_ITEM_ID = "/{id}";

    private final DeliveryOrderItemService deliveryOrderItemService;

    public DeliveryOrderItemResource(DeliveryOrderItemService deliveryOrderItemService) {
        this.deliveryOrderItemService = deliveryOrderItemService;
    }

    @PatchMapping(DELIVERY_ORDER_ITEM_ID)
    public DeliveryOrderItem updateQuantity(@PathVariable String id, @RequestParam Integer quantity){
        return deliveryOrderItemService.updateQuantity(id, quantity);
    }

    @GetMapping
    public List<DeliveryOrderItem> findAll() {
        return deliveryOrderItemService.findAll();
    }
}
