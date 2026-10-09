package es.upm.miw.apaw.domain.services.invoice;

import es.upm.miw.apaw.adapters.out.invoice.postgres.InvoiceEntity;
import es.upm.miw.apaw.adapters.out.invoice.postgres.InvoiceRepository;
import es.upm.miw.apaw.adapters.out.invoice.postgres.LegalServiceEntity;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.invoice.CreationInvoice;
import es.upm.miw.apaw.domain.model.invoice.Invoice;
import es.upm.miw.apaw.domain.model.invoice.InvoiceFindCriteria;
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
import java.util.Set;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.LegalServiceSeederForDev.ID_0;
import static es.upm.miw.apaw.config.seeders.LegalServiceSeederForDev.ID_1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class InvoiceServiceIT {

    private static final UUID TEST_USER_ID =
            UUID.fromString("11111111-1111-1111-1111-111111111111");

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @MockitoBean
    private UserFinder userFinder;

    // Test original: creación de una factura

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

    // Criterio 1: atributo directo de Invoice (paid)

    @Test
    @Transactional
    void testFindByCriteriaPaid() {
        InvoiceFindCriteria criteria = InvoiceFindCriteria.builder()
                .paid(true)
                .build();

        List<Invoice> invoices = this.invoiceService.findByCriteria(criteria);

        assertThat(invoices).isNotEmpty();
        assertThat(invoices).allSatisfy(invoice ->
                assertThat(invoice.getPaid()).isTrue());

        verifyNoInteractions(this.userFinder);
    }

    // Criterio 2: criterio derivado de issueDate (issueYear)

    @Test
    @Transactional
    void testFindByCriteriaIssueYear() {
        InvoiceFindCriteria criteria = InvoiceFindCriteria.builder()
                .issueYear(2026)
                .build();

        List<Invoice> invoices = this.invoiceService.findByCriteria(criteria);

        assertThat(invoices).isNotEmpty();
        assertThat(invoices).allSatisfy(invoice ->
                assertThat(invoice.getIssueDate().getYear()).isEqualTo(2026));

        verifyNoInteractions(this.userFinder);
    }

    // Criterio 3: atributo de la entidad relacionada LegalService

    @Test
    @Transactional
    void testFindByCriteriaServiceName() {
        InvoiceFindCriteria criteria = InvoiceFindCriteria.builder()
                .serviceName("Initial Legal Consultation")
                .build();

        List<Invoice> invoices = this.invoiceService.findByCriteria(criteria);

        assertThat(invoices).hasSize(3);
        assertThat(invoices).allSatisfy(invoice ->
                assertThat(invoice.getServices())
                        .anySatisfy(service ->
                                assertThat(service.getName())
                                        .isEqualTo("Initial Legal Consultation")));

        verifyNoInteractions(this.userFinder);
    }

    // Criterio 4: atributo del usuario obtenido mediante UserFinder.
    // Se comprueba que UserFinder se consulta una sola vez.

    @Test
    @Transactional
    void testFindByCriteriaCustomerIdentity() {
        UserSnapshot user = UserSnapshot.builder()
                .id(TEST_USER_ID)
                .identity("ABC123")
                .build();

        when(this.userFinder.findByIds(Set.of(TEST_USER_ID)))
                .thenReturn(List.of(user));

        InvoiceFindCriteria criteria = InvoiceFindCriteria.builder()
                .customerIdentity("ABC123")
                .build();

        List<Invoice> invoices = this.invoiceService.findByCriteria(criteria);

        assertThat(invoices).isNotEmpty();
        assertThat(invoices)
                .allSatisfy(invoice ->
                        assertThat(invoice.getCustomer().getId())
                                .isEqualTo(TEST_USER_ID));

        verify(this.userFinder, times(1))
                .findByIds(Set.of(TEST_USER_ID));
    }

    // Los criterios nulos no aplican filtros.

    @Test
    @Transactional
    void testFindByCriteriaAllNull() {
        InvoiceFindCriteria criteria = InvoiceFindCriteria.builder().build();

        List<Invoice> invoices = this.invoiceService.findByCriteria(criteria);

        assertThat(invoices).hasSize(6);

        verifyNoInteractions(this.userFinder);
    }

    // Un criterio de usuario sin coincidencias devuelve una lista vacía.

    @Test
    @Transactional
    void testFindByCriteriaCustomerIdentityWithoutMatches() {
        when(this.userFinder.findByIds(Set.of(TEST_USER_ID)))
                .thenReturn(List.of(
                        UserSnapshot.builder()
                                .id(TEST_USER_ID)
                                .identity("XYZ789")
                                .build()));

        InvoiceFindCriteria criteria = InvoiceFindCriteria.builder()
                .customerIdentity("NO-EXISTE")
                .build();

        List<Invoice> invoices = this.invoiceService.findByCriteria(criteria);

        assertThat(invoices).isEmpty();

        verify(this.userFinder, times(1))
                .findByIds(Set.of(TEST_USER_ID));
    }
}