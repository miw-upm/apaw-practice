package es.upm.miw.apaw.adapters.mongodb.warehouse.daos;

import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.InventoryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface InventoryRepository extends MongoRepository<InventoryEntity, UUID> {

    Optional<InventoryEntity> findByIdProductItem(UUID idProductItem);

}
