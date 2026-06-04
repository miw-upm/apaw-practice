package es.upm.miw.apaw.domain.services.clothingstore;

import es.upm.miw.apaw.domain.models.clothingstore.Order;
import es.upm.miw.apaw.domain.persistenceports.clothingstore.OrderPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class OrderService {

    private final OrderPersistence orderPersistence;

    @Autowired
    public OrderService(OrderPersistence orderPersistence) {
        this.orderPersistence = orderPersistence;
    }

    public Order create(Order order) {
        return this.orderPersistence.create(order);
    }

    public Stream<Order> readAll() {
        return this.orderPersistence.readAll();
    }
}
