package es.upm.miw.apaw.adapters.mongodb.warehouse.daos;


import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.MovementOrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface MovementOrderRepository extends MongoRepository<MovementOrderEntity, UUID> {

    List<MovementOrderEntity> findByTypeOrder(String typeOrder);

    List<MovementOrderEntity> findByCompletedOrderTrue();

    List<MovementOrderEntity> findByUserId(UUID userId);

}
