package es.upm.miw.apaw.domain.ports.out.invoice;

import es.upm.miw.apaw.domain.model.invoice.Invoice;

public interface InvoiceGateway {

    Invoice create(Invoice invoice);
}