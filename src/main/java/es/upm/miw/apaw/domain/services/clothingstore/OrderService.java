package es.upm.miw.apaw.domain.services.clothingstore;

import es.upm.miw.apaw.domain.persistenceports.clothingstore.OrderPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderPersistence orderPersistence;

    @Autowired
    public OrderService(OrderPersistence orderPersistence) {
        this.orderPersistence = orderPersistence;
    }

    public List<UUID> findDistinctGarmentIdsByInvoiceNumber(String invoiceNumber) {
        return this.orderPersistence.findDistinctGarmentIdsByInvoiceNumber(invoiceNumber).toList();
    }
}
