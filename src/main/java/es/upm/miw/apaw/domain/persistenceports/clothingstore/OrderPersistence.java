package es.upm.miw.apaw.domain.persistenceports.clothingstore;

import es.upm.miw.apaw.domain.models.clothingstore.Order;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface OrderPersistence {

    Order create(Order order);

    Stream<Order> readAll();
}
