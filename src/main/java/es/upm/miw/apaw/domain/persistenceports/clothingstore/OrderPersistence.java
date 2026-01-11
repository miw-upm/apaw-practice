package es.upm.miw.apaw.domain.persistenceports.clothingstore;

import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface OrderPersistence {
    Stream<UUID> findDistinctGarmentIdsByInvoiceNumber(String invoiceNumber);
}
