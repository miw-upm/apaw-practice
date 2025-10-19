package es.upm.miw.apaw.adapters.mongodb.warehouse.persistence;

import es.upm.miw.apaw.adapters.mongodb.warehouse.daos.LocationRepository;
import es.upm.miw.apaw.adapters.mongodb.warehouse.daos.MovementOrderRepository;
import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.LocationEntity;
import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.OrderDetailEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.persistenceports.warehouse.OrderDetailPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Repository("orderDetailPersistence")
public class OrderDetailPersistenceMongodb implements OrderDetailPersistence {

    private final LocationRepository locationRepository;
    private final MovementOrderRepository movementOrderRepository;

    @Autowired
    public OrderDetailPersistenceMongodb(LocationRepository locationRepository,
                                         MovementOrderRepository movementOrderRepository) {
        this.locationRepository = locationRepository;
        this.movementOrderRepository = movementOrderRepository;
    }

    @Override
    public BigDecimal sumUnitCostDistinctByPosition(String position) {
        LocationEntity location = this.locationRepository.findByPosition(position)
                .orElseThrow(() -> new NotFoundException("Position not found: " + position));

        Set<BigDecimal> distinctCosts = this.movementOrderRepository.findAll().stream()
                .flatMap(order -> order.getOrderDetailEntities().stream())
                .filter(detail -> location.getProductItemEntities().contains(detail.getProductItemEntity()))
                .map(OrderDetailEntity::getUnitCost)
                .collect(Collectors.toSet());

        return distinctCosts.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
