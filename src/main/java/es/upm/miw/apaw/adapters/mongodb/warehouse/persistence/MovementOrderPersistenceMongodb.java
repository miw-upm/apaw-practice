package es.upm.miw.apaw.adapters.mongodb.warehouse.persistence;

import es.upm.miw.apaw.adapters.mongodb.shop.entities.ArticleItemEntity;
import es.upm.miw.apaw.adapters.mongodb.shop.entities.ShoppingCartEntity;
import es.upm.miw.apaw.adapters.mongodb.warehouse.daos.ProductItemRepository;
import es.upm.miw.apaw.adapters.mongodb.warehouse.daos.MovementOrderRepository;
import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.OrderDetailEntity;
import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.MovementOrderEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.shop.ShoppingCart;
import es.upm.miw.apaw.domain.models.warehouse.MovementOrder;
import es.upm.miw.apaw.domain.persistenceports.warehouse.MovementOrderPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Repository("movementOrderPersistence")
public class MovementOrderPersistenceMongodb implements MovementOrderPersistence {

    private final MovementOrderRepository movementOrderRepository;
    private final ProductItemRepository productItemRepository;

    @Autowired
    public MovementOrderPersistenceMongodb(MovementOrderRepository movementOrderRepository, ProductItemRepository productItemRepository) {
        this.movementOrderRepository = movementOrderRepository;
        this.productItemRepository = productItemRepository;
    }

    @Override
    public Stream<MovementOrder> readAll() {
        return this.movementOrderRepository
                .findAll().stream()
                .map(MovementOrderEntity::toMovementOrder);
    }

    @Override
    public MovementOrder read(UUID id) {
        return this.movementOrderRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("MovementOrder id: " + id))
                .toMovementOrder();
    }

    @Override
    public MovementOrder create(MovementOrder movementOrder) {
        MovementOrderEntity movementOrderEntity = new MovementOrderEntity(movementOrder);
        movementOrderEntity.setOrderDetailEntities(
                movementOrder.getOrderDetails().stream()
                        .map(orderDetail -> {
                            OrderDetailEntity orderDetailEntity = new OrderDetailEntity(orderDetail);
                            orderDetailEntity.setProductItemEntity(
                                    this.productItemRepository
                                            .findByBarcode(orderDetail.getProductItem().getBarcode())
                                            .orElseThrow(() -> new NotFoundException(
                                                    "ProductItem barcode: " + orderDetail.getProductItem().getBarcode()))
                            );
                            return orderDetailEntity;
                        })
                        .toList()
        );
        return this.movementOrderRepository.save(movementOrderEntity).toMovementOrder();
    }

    @Override
    public MovementOrder update(MovementOrder movementOrder) {
        MovementOrderEntity movementOrderEntity = this.movementOrderRepository
                .findById(movementOrder.getId())
                .orElseThrow(() -> new NotFoundException("MovementOrder id: " + movementOrder.getId()));
        List<OrderDetailEntity> orderDetailEntities = movementOrder.getOrderDetails().stream()
                .map(orderDetail -> new OrderDetailEntity(
                        this.productItemRepository
                                .findByBarcode(orderDetail.getProductItem().getBarcode())
                                .orElseThrow(() -> new NotFoundException("ProductItem barcode: "
                                    + orderDetail.getProductItem().getBarcode())),
                        orderDetail.getQtyRequested(),
                        orderDetail.getQtyMoved(),
                        orderDetail.getUnitCost())
                ).toList();
        movementOrderEntity.setOrderDetailEntities(orderDetailEntities);
        return this.movementOrderRepository.save(movementOrderEntity).toMovementOrder();
    }

}
