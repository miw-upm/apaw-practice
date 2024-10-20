package es.upm.miw.apaw_practice.adapters.rest.delivery_food;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.delivery_food.DeliveryOrder;
import es.upm.miw.apaw_practice.domain.services.delivery_food.DeliveryOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(DeliveryOrderResource.DELIVERY_ORDER)
public class DeliveryOrderResource {

    public static final String DELIVERY_ORDER = "/delivery_food/order";
    public static final String SEARCH = "/search";

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

    @GetMapping(SEARCH)
    public ResponseEntity<Double> calculateTotalRating(@RequestParam String q) {
        LexicalAnalyzer analyzer = new LexicalAnalyzer();
        String customerName = analyzer.extractWithAssure(q, "customerName");
        String menuDescription = analyzer.extractWithAssure(q, "description");
        Double totalRating = this.deliveryOrderService.calculateTotalRating(customerName, menuDescription);
        return new ResponseEntity<>(totalRating, HttpStatus.OK);
    }
}
