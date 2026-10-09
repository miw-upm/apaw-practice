
package es.upm.miw.apaw.domain.services.invoice;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.invoice.CreationInvoice;
import es.upm.miw.apaw.domain.model.invoice.Invoice;
import es.upm.miw.apaw.domain.model.invoice.LegalService;
import es.upm.miw.apaw.domain.model.invoice.LegalServiceInvoiceReport;
import es.upm.miw.apaw.domain.ports.out.invoice.InvoiceGateway;
import es.upm.miw.apaw.domain.ports.out.invoice.LegalServiceGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceGateway invoiceGateway;
    private final LegalServiceGateway legalServiceGateway;
    private final UserFinder userFinder;

    public Invoice create(CreationInvoice creation) {
        Invoice invoice = new Invoice();

        BeanUtils.copyProperties(creation, invoice);

        List<LegalService> services = creation.getLegalServiceIds()
                .stream()
                .map(this::readLegalService)
                .toList();

        invoice.setServices(services);
        invoice.setCustomer(this.userFinder.read(creation.getUserId()));

        invoice.doDefault();

        return this.invoiceGateway.create(invoice);
    }

    public List<LegalServiceInvoiceReport> findLegalServiceInvoiceReport() {
        return this.invoiceGateway.findLegalServiceInvoiceReport();
    }

    private LegalService readLegalService(UUID id) {
        return this.legalServiceGateway.read(id)
                .orElseThrow(() ->
                        new NotFoundException("Legal service id not found: " + id));
    }
}