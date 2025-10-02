package es.upm.miw.apaw.adapters.mongodb.warehouse.daos;

import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.InventoryEntity;
import es.upm.miw.apaw.domain.models.warehouse.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InventoryRepository extends MongoRepository<InventoryEntity, UUID> {

    Optional<InventoryEntity> findByIdProductItem(UUID idProductItem);

}
