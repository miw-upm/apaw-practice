
package es.upm.miw.apaw.adapters.out.invoice.postgres;

import es.upm.miw.apaw.domain.model.invoice.Invoice;
import es.upm.miw.apaw.domain.model.invoice.LegalServiceInvoiceReport;
import es.upm.miw.apaw.domain.ports.out.invoice.InvoiceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;

@Repository
@RequiredArgsConstructor
public class InvoiceAdapter implements InvoiceGateway {

    private final InvoiceRepository invoiceRepository;
    private final LegalServiceRepository legalServiceRepository;

    @Override
    @Transactional
    public Invoice create(Invoice invoice) {
        InvoiceEntity invoiceEntity = new InvoiceEntity(invoice);

        invoiceEntity.setServices(
                new ArrayList<>(invoice.getServices().stream()
                        .map(service -> this.legalServiceRepository
                                .getReferenceById(service.getId()))
                        .toList())
        );

        this.invoiceRepository.save(invoiceEntity);

        return invoice;
    }

    @Override
    public List<LegalServiceInvoiceReport> findLegalServiceInvoiceReport() {
        return this.invoiceRepository.findLegalServiceInvoiceReport();
    }

}