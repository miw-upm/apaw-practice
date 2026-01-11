package es.upm.miw.apaw.adapters.mongodb.clothingstore.persistence;

import es.upm.miw.apaw.adapters.mongodb.clothingstore.daos.StoreRepository;
import es.upm.miw.apaw.adapters.mongodb.clothingstore.entities.GarmentEntity;
import es.upm.miw.apaw.domain.persistenceports.clothingstore.OrderPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

@Repository
public class OrderPersistenceMongodb implements OrderPersistence {

    private final StoreRepository storeRepository;

    @Autowired
    public OrderPersistenceMongodb(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public Stream<UUID> findDistinctGarmentIdsByInvoiceNumber(String invoiceNumber) {
        if (invoiceNumber == null || invoiceNumber.isBlank()) return Stream.empty();

        return this.storeRepository.findByOrdersInvoiceNumber(invoiceNumber).stream()
                .filter(store -> store.getOrders() != null)
                .flatMap(store -> store.getOrders().stream())
                .filter(order -> order.getInvoice() != null
                        && invoiceNumber.equals(order.getInvoice().getNumber())
                        && order.getGarments() != null)
                .flatMap(order -> order.getGarments().stream())
                .map(GarmentEntity::getId)
                .filter(Objects::nonNull)
                .distinct();
    }
}
