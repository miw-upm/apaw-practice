package es.upm.miw.apaw.adapters.mongodb.warehouse.persistence;

import es.upm.miw.apaw.adapters.mongodb.warehouse.daos.MovementOrderRepository;
import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.MovementOrderEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.warehouse.MovementOrder;
import es.upm.miw.apaw.domain.persistenceports.warehouse.MovementOrderPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.stream.Stream;

@Repository("movementOrderPersistence")
public class MovementOrderPersistenceMongodb implements MovementOrderPersistence {

    private final MovementOrderRepository movementOrderRepository;

    @Autowired
    public MovementOrderPersistenceMongodb(MovementOrderRepository movementOrderRepository) {
        this.movementOrderRepository = movementOrderRepository;
    }

    @Override
    public Stream<MovementOrder> readAll() {
        return this.movementOrderRepository.findAll().stream()
                .map(MovementOrderEntity::toMovementOrder);
    }

    @Override
    public MovementOrder read(UUID id) {
        return this.movementOrderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("MovementOrder id: " + id))
                .toMovementOrder();
    }

    @Override
    public MovementOrder create(MovementOrder movementOrder) {
        MovementOrderEntity entity = new MovementOrderEntity(movementOrder);
        this.movementOrderRepository.save(entity);
        return entity.toMovementOrder();
    }

    @Override
    public MovementOrder update(UUID id, MovementOrder movementOrder) {
        MovementOrderEntity entity = this.movementOrderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("MovementOrder id: " + id));
        entity.fromMovementOrder(movementOrder);
        this.movementOrderRepository.save(entity);
        return entity.toMovementOrder();
    }

    @Override
    public void delete(UUID id) {
        this.movementOrderRepository.deleteById(id);
    }

}
