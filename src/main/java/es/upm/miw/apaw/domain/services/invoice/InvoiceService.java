
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
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.invoice.InvoiceFindCriteria;

import java.math.BigDecimal;
import java.util.Set;
import java.util.Objects;
import java.util.stream.Collectors;
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

        BigDecimal taxableBase = services.stream()
                .map(LegalService::getFee)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        invoice.setTaxableBase(taxableBase);
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


    public List<Invoice> findByCriteria(InvoiceFindCriteria criteria) {
        List<Invoice> invoices = this.invoiceGateway.findByCriteria(criteria);

        // Si no se solicita el filtro de usuario, devolvemos el resultado.
        if (criteria.getCustomerIdentity() == null) {
            return invoices;
        }

        if (invoices.isEmpty()) {
            return invoices;
        }

        // Recoger todos los identificadores de usuario sin duplicados.
        Set<UUID> userIds = invoices.stream()
                .map(invoice -> invoice.getCustomer().getId())
                .collect(Collectors.toSet());

        // Una única llamada a apaw-user.
        List<UserSnapshot> users = this.userFinder.findByIds(userIds);

        // Filtrar localmente usando los datos recibidos.
        return invoices.stream()
                .filter(invoice -> users.stream().anyMatch(user ->
                        Objects.equals(
                                user.getId(),
                                invoice.getCustomer().getId()
                        )
                                && criteria.getCustomerIdentity()
                                .equalsIgnoreCase(user.getIdentity())
                ))
                .toList();
    }
}