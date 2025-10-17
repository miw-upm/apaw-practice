package es.upm.miw.apaw.adapters.mongodb.warehouse.persistence;

import es.upm.miw.apaw.adapters.mongodb.warehouse.daos.MovementOrderRepository;
import es.upm.miw.apaw.adapters.mongodb.warehouse.daos.ProductItemRepository;
import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.MovementOrderEntity;
import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.ProductItemEntity;
import es.upm.miw.apaw.domain.models.warehouse.MovementOrder;
import es.upm.miw.apaw.domain.persistenceports.warehouse.MovementOrderPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Repository("movementOrderPersistence")
public class MovementOrderPersistenceMongodb implements MovementOrderPersistence {

    private final MovementOrderRepository movementOrderRepository;
    private final ProductItemRepository productItemRepository;

    @Autowired
    public MovementOrderPersistenceMongodb(MovementOrderRepository movementOrderRepository,
                                           ProductItemRepository productItemRepository) {
        this.movementOrderRepository = movementOrderRepository;
        this.productItemRepository = productItemRepository;
    }

    @Override
    public Optional<MovementOrder> readById(UUID id) {
        return this.movementOrderRepository.findById(id)
                .map(MovementOrderEntity::toMovementOrder);
    }

    @Override
    public MovementOrder create(MovementOrder movementOrder) {
        MovementOrderEntity entity = MovementOrderEntity.fromMovementOrder(movementOrder);

        if (entity.getOrderDetailEntities() != null && !entity.getOrderDetailEntities().isEmpty()) {
            entity.getOrderDetailEntities().forEach(orderDetailEntity -> {
                if (orderDetailEntity.getProductItemEntity() != null) {
                    String barcode = orderDetailEntity.getProductItemEntity().getBarcode();
                    ProductItemEntity existing = this.productItemRepository.findByBarcode(barcode)
                            .orElseThrow(() -> new RuntimeException("ProductItem not found: " + barcode));
                    orderDetailEntity.setProductItemEntity(existing);
                }
            });
        }

        MovementOrderEntity saved = this.movementOrderRepository.save(entity);
        return saved.toMovementOrder();
    }

    @Override
    public Stream<MovementOrder> findAll() {
        return this.movementOrderRepository.findAll().stream()
                .map(MovementOrderEntity::toMovementOrder);
    }

}