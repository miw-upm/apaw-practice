
package es.upm.miw.apaw.adapters.in.invoice;

import es.upm.miw.apaw.domain.model.invoice.CreationInvoice;
import es.upm.miw.apaw.domain.model.invoice.Invoice;
import es.upm.miw.apaw.domain.model.invoice.LegalServiceInvoiceReport;
import es.upm.miw.apaw.domain.services.invoice.InvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(InvoiceResource.INVOICES)
@RequiredArgsConstructor
public class InvoiceResource {

    public static final String INVOICES = "/invoices";

    private final InvoiceService invoiceService;

    public static final String REPORT = "/report";

    @GetMapping(REPORT)
    public List<LegalServiceInvoiceReport> findLegalServiceInvoiceReport() {
        return this.invoiceService.findLegalServiceInvoiceReport();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice create(@Valid @RequestBody CreationInvoice creation) {
        return this.invoiceService.create(creation);
    }
}