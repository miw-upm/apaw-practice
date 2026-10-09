package es.upm.miw.apaw.adapters.out.invoice.postgres;

import es.upm.miw.apaw.domain.model.invoice.LegalServiceInvoiceReport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

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
}
