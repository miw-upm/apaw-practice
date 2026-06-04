package es.upm.miw.apaw.adapters.mongodb.clothingstore.persistence;

import es.upm.miw.apaw.adapters.mongodb.clothingstore.daos.OrderRepository;
import es.upm.miw.apaw.adapters.mongodb.clothingstore.entities.OrderEntity;
import es.upm.miw.apaw.domain.models.clothingstore.Order;
import es.upm.miw.apaw.domain.persistenceports.clothingstore.OrderPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.stream.Stream;

@Repository
public class OrderPersistenceMongodb implements OrderPersistence {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderPersistenceMongodb(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order create(Order order) {
        OrderEntity orderEntity = new OrderEntity(order);
        orderEntity.setId(UUID.randomUUID());
        return this.orderRepository.save(orderEntity).toOrder();
    }

    @Override
    public Stream<Order> readAll() {
        return this.orderRepository.findAll()
                .stream()
                .map(OrderEntity::toOrder);
    }
}
