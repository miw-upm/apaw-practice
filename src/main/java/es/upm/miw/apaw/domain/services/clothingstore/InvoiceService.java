package es.upm.miw.apaw.domain.services.clothingstore;

import es.upm.miw.apaw.domain.models.clothingstore.Invoice;
import es.upm.miw.apaw.domain.persistenceports.clothingstore.InvoicePersistence;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

    private final InvoicePersistence invoicePersistence;

    public InvoiceService(InvoicePersistence invoicePersistence) {
        this.invoicePersistence = invoicePersistence;
    }

    public Invoice readByNumber(String number) {
        return this.invoicePersistence.readByNumber(number);
    }

    public void delete(String number) {
        this.invoicePersistence.delete(number);
    }
}
