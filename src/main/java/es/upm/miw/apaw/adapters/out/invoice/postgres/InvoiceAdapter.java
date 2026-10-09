
package es.upm.miw.apaw.adapters.out.invoice.postgres;

import es.upm.miw.apaw.domain.model.invoice.Invoice;
import es.upm.miw.apaw.domain.model.invoice.InvoiceFindCriteria;
import es.upm.miw.apaw.domain.model.invoice.LegalServiceInvoiceReport;
import es.upm.miw.apaw.domain.ports.out.invoice.InvoiceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;

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


    @Override
    @Transactional(readOnly = true)
    public List<Invoice> findByCriteria(InvoiceFindCriteria criteria) {
        return this.invoiceRepository.findAll((root, query, cb) -> {
                    List<jakarta.persistence.criteria.Predicate> predicates =
                            new ArrayList<>();

                    if (criteria.getPaid() != null) {
                        predicates.add(
                                cb.equal(root.get("paid"), criteria.getPaid())
                        );
                    }

                    if (criteria.getIssueYear() != null) {
                        int year = criteria.getIssueYear();
                        LocalDate start = LocalDate.of(year, 1, 1);
                        LocalDate end = start.plusYears(1);

                        predicates.add(
                                cb.greaterThanOrEqualTo(
                                        root.get("issueDate"), start
                                )
                        );
                        predicates.add(
                                cb.lessThan(root.get("issueDate"), end)
                        );
                    }

                    if (criteria.getServiceName() != null
                            && !criteria.getServiceName().isBlank()) {
                        var services = root.join("services");

                        predicates.add(
                                cb.equal(
                                        cb.lower(services.get("name")),
                                        criteria.getServiceName()
                                                .trim()
                                                .toLowerCase(Locale.ROOT)
                                )
                        );
                    }

                    query.distinct(true);
                    return cb.and(predicates.toArray(
                            new jakarta.persistence.criteria.Predicate[0]
                    ));
                }).stream()
                .map(InvoiceEntity::toDomain)
                .toList();
    }

}