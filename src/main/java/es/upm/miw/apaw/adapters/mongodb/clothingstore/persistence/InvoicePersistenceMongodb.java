package es.upm.miw.apaw.adapters.mongodb.clothingstore.persistence;

import es.upm.miw.apaw.adapters.mongodb.clothingstore.daos.InvoiceRepository;
import es.upm.miw.apaw.adapters.mongodb.clothingstore.entities.InvoiceEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.clothingstore.Invoice;
import es.upm.miw.apaw.domain.persistenceports.clothingstore.InvoicePersistence;
import org.springframework.stereotype.Repository;

@Repository
public class InvoicePersistenceMongodb implements InvoicePersistence {

    private final InvoiceRepository invoiceRepository;

    public InvoicePersistenceMongodb(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Invoice readByNumber(String number) {
        return this.invoiceRepository.findById(number)
                .map(InvoiceEntity::toInvoice)
                .orElseThrow(() -> new NotFoundException("Invoice not found: " + number));
    }

    @Override
    public void delete(String number) {
        if (!this.invoiceRepository.existsById(number)) {
            throw new NotFoundException("Invoice not found: " + number);
        }
        this.invoiceRepository.deleteById(number);
    }
}
