
package es.upm.miw.apaw.domain.services.invoice;

import es.upm.miw.apaw.adapters.out.invoice.postgres.InvoiceEntity;
import es.upm.miw.apaw.adapters.out.invoice.postgres.InvoiceRepository;
import es.upm.miw.apaw.adapters.out.invoice.postgres.LegalServiceEntity;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.invoice.CreationInvoice;
import es.upm.miw.apaw.domain.model.invoice.Invoice;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.LegalServiceSeederForDev.ID_0;
import static es.upm.miw.apaw.config.seeders.LegalServiceSeederForDev.ID_1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class InvoiceServiceIT {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @MockitoBean
    private UserFinder userFinder;

    @Test
    @Transactional
    void testCreate() {
        UserSnapshot user = UserSnapshot.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                .mobile("600000100")
                .firstName("cliente0")
                .build();

        CreationInvoice creation = CreationInvoice.builder()
                .vatRate(new BigDecimal("0.21"))
                .legalServiceIds(List.of(ID_0, ID_1))
                .userId(user.getId())
                .build();

        when(this.userFinder.read(user.getId())).thenReturn(user);

        Invoice invoice = this.invoiceService.create(creation);

        assertThat(invoice.getId()).isNotNull();
        assertThat(invoice.getInvoiceNumber()).isNotBlank();
        assertThat(invoice.getIssueDate()).isEqualTo(LocalDate.now());
        assertThat(invoice.getPaid()).isFalse();
        assertThat(invoice.getVatRate()).isEqualByComparingTo("0.21");
        assertThat(invoice.getServices())
                .extracting("id")
                .containsExactly(ID_0, ID_1);
        assertThat(invoice.getCustomer()).isEqualTo(user);

        InvoiceEntity entity = this.invoiceRepository
                .findById(invoice.getId())
                .orElseThrow();

        assertThat(entity.getInvoiceNumber()).isEqualTo(invoice.getInvoiceNumber());
        assertThat(entity.getIssueDate()).isEqualTo(invoice.getIssueDate());
        assertThat(entity.getVatRate()).isEqualByComparingTo("0.21");
        assertThat(entity.getUserId()).isEqualTo(user.getId());
        assertThat(entity.getServices())
                .extracting(LegalServiceEntity::getId)
                .containsExactly(ID_0, ID_1);
    }
}