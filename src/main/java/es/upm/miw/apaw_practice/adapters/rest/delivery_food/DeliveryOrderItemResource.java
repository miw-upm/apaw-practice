package es.upm.miw.apaw_practice.adapters.rest.delivery_food;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.delivery_food.DeliveryOrderItem;
import es.upm.miw.apaw_practice.domain.services.delivery_food.DeliveryOrderItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(DeliveryOrderItemResource.DELIVERY_ORDER_ITEM)
public class DeliveryOrderItemResource {

    public static final String DELIVERY_ORDER_ITEM = "/delivery_food/order_item";
    public static final String DELIVERY_ORDER_ITEM_ID = "/{id}";
    public static final String SEARCH = "/search";

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

    @GetMapping(SEARCH)
    public ResponseEntity<List<String>> findDescriptionsMenuGreaterThanQuantity(@RequestParam String q) {
        LexicalAnalyzer analyzer = new LexicalAnalyzer();
        Integer quantity = Integer.valueOf(analyzer.extractWithAssure(q, "quantity"));
        List<String> descriptionsMenuGreaterThanQuantity = deliveryOrderItemService.findDescriptionsMenuGreaterThanQuantity(quantity);
        return new ResponseEntity<>(descriptionsMenuGreaterThanQuantity, HttpStatus.OK);
    }
}
