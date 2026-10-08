package es.upm.miw.apaw.adapters.out.invoice.postgres;

import es.upm.miw.apaw.domain.ports.out.invoice.InvoiceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class InvoiceAdapter implements InvoiceGateway {

    private final InvoiceRepository invoiceRepository;
}