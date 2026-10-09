package es.upm.miw.apaw.domain.ports.out.invoice;

import es.upm.miw.apaw.domain.model.invoice.Invoice;
import es.upm.miw.apaw.domain.model.invoice.LegalServiceInvoiceReport;

import java.util.List;

public interface InvoiceGateway {

    Invoice create(Invoice invoice);

    List<LegalServiceInvoiceReport> findLegalServiceInvoiceReport();
}