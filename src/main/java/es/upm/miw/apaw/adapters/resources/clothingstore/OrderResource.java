package es.upm.miw.apaw.adapters.resources.clothingstore;

import es.upm.miw.apaw.domain.models.clothingstore.Order;
import es.upm.miw.apaw.domain.services.clothingstore.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping(OrderResource.ORDERS)
public class OrderResource {

    public static final String ORDERS = "/clothingstore/orders";

    private final OrderService orderService;

    @Autowired
    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order create(@RequestBody Order order) {
        return this.orderService.create(order);
    }

    @GetMapping
    public Stream<Order> readAll() {
        return this.orderService.readAll();
    }
}
