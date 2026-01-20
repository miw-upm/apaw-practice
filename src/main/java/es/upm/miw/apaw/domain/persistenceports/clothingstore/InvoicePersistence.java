package es.upm.miw.apaw.domain.persistenceports.clothingstore;

import es.upm.miw.apaw.domain.models.clothingstore.Invoice;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoicePersistence {
    Invoice readByNumber(String number);
    void delete(String number);
}
