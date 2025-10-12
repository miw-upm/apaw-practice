package es.upm.miw.apaw.adapters.mongodb.warehouse.persistence;

import es.upm.miw.apaw.adapters.mongodb.warehouse.daos.OrderDetailRepository;
import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.OrderDetailEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.warehouse.OrderDetail;
import es.upm.miw.apaw.domain.persistenceports.warehouse.OrderDetailPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.stream.Stream;

@Repository("orderDetailPersistence")
public class OrderDetailPersistenceMongodb implements OrderDetailPersistence {

    private final OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderDetailPersistenceMongodb(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public Stream<OrderDetail> readAll() {
        return this.orderDetailRepository.findAll().stream()
                .map(OrderDetailEntity::toOrderDetail);
    }

    @Override
    public OrderDetail read(UUID id) {
        return this.orderDetailRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("OrderDetail id: " + id))
                .toOrderDetail();
    }

}
