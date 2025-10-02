package es.upm.miw.apaw.adapters.mongodb.warehouse.daos;

import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.OrderDetailEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface OrderDetailRepository extends MongoRepository<OrderDetailEntity, UUID> {

    List<OrderDetailEntity> findByIdMovementOrder(UUID idMovementOrder);

    List<OrderDetailEntity> findByIdProductItem(UUID idProductItem);

}
