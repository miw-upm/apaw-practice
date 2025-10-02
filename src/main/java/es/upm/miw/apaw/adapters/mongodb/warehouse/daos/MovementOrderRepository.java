package es.upm.miw.apaw.adapters.mongodb.warehouse.daos;

import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.MovementOrderEntity;
import es.upm.miw.apaw.domain.models.warehouse.MovementOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface MovementOrderRepository extends MongoRepository<MovementOrderEntity, UUID> {

    //List<MovementOrderEntity> findByUserId(UUID id);
    List<MovementOrderEntity> findByTypeOrderAndIsCompleted(String typeOrder, Boolean isCompleted);

}
