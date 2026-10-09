package es.upm.miw.apaw.adapters.out.invoice.postgres;

import es.upm.miw.apaw.domain.model.invoice.InvoiceFindCriteria;
import es.upm.miw.apaw.domain.model.invoice.LegalServiceInvoiceReport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class InvoiceRepositoryIT {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Test
    void testFindLegalServiceInvoiceReport() {
        List<LegalServiceInvoiceReport> report =
                this.invoiceRepository.findLegalServiceInvoiceReport();

        assertThat(report)
                .extracting(LegalServiceInvoiceReport::getTotalInvoiceCount)
                .isSortedAccordingTo(Comparator.reverseOrder());

        assertThat(report)
                .filteredOn(item ->
                        item.getServiceName().equals("Initial Legal Consultation"))
                .singleElement()
                .satisfies(item -> {
                    assertThat(item.getTotalInvoiceCount()).isEqualTo(3);
                    assertThat(item.getPaidInvoiceCount()).isEqualTo(2);
                });

        assertThat(report)
                .filteredOn(item ->
                        item.getServiceName().equals("Criminal Law Consultation"))
                .singleElement()
                .satisfies(item -> {
                    assertThat(item.getTotalInvoiceCount()).isEqualTo(2);
                    assertThat(item.getPaidInvoiceCount()).isEqualTo(1);
                });

        assertThat(report)
                .filteredOn(item ->
                        item.getServiceName().equals("Contract Drafting"))
                .singleElement()
                .satisfies(item -> {
                    assertThat(item.getTotalInvoiceCount()).isEqualTo(1);
                    assertThat(item.getPaidInvoiceCount()).isEqualTo(0);
                });
    }

    @Test
    void testFindByCriteriaPaid() {
        InvoiceFindCriteria criteria = InvoiceFindCriteria.builder()
                .paid(true)
                .issueYear(2026)
                .serviceName("Initial Legal Consultation")
                .build();

        List<InvoiceEntity> invoices =
                this.invoiceRepository.findByCriteria(
                        criteria.getPaid(),
                        criteria.getIssueYear(),
                        criteria.getServiceName());

        assertThat(invoices).hasSize(2);
        assertThat(invoices)
                .allSatisfy(invoice ->
                        assertThat(invoice.getPaid()).isTrue());
    }

    @Test
    void testFindByCriteriaIssueYear() {
        InvoiceFindCriteria criteria = InvoiceFindCriteria.builder()
                .issueYear(2026)
                .serviceName("Initial Legal Consultation")
                .build();

        List<InvoiceEntity> invoices =
                this.invoiceRepository.findByCriteria(
                        criteria.getPaid(),
                        criteria.getIssueYear(),
                        criteria.getServiceName());

        assertThat(invoices).hasSize(3);
        assertThat(invoices)
                .allSatisfy(invoice ->
                        assertThat(invoice.getIssueDate().getYear())
                                .isEqualTo(2026));
    }
    @Test
    @Transactional
    void testFindByCriteriaServiceName() {
        List<InvoiceEntity> invoices = this.invoiceRepository.findByCriteria(
                null,
                null,
                "Initial Legal Consultation"
        );
        assertThat(invoices).hasSize(3);
        assertThat(invoices).allSatisfy(invoice ->
                assertThat(invoice.getServices())
                        .anySatisfy(service ->
                                assertThat(service.getName())
                                        .isEqualTo("Initial Legal Consultation")));
    }

    @Test
    void testFindByCriteriaNullDoesNotFilter() {
        List<InvoiceEntity> allInvoices =
                this.invoiceRepository.findByCriteria(null, null, null);

        List<InvoiceEntity> paidInvoices =
                this.invoiceRepository.findByCriteria(true, null, null);

        assertThat(allInvoices).isNotEmpty();
        assertThat(paidInvoices.size()).isLessThanOrEqualTo(allInvoices.size());
    }
}