package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.adapters.out.invoice.postgres.InvoiceEntity;
import es.upm.miw.apaw.adapters.out.invoice.postgres.InvoiceRepository;
import es.upm.miw.apaw.adapters.out.invoice.postgres.LegalServiceEntity;
import es.upm.miw.apaw.adapters.out.invoice.postgres.LegalServiceRepository;
import es.upm.miw.apaw.domain.model.invoice.PaymentType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Log4j2
@Component
@Profile("test")
@Order(2)
@RequiredArgsConstructor
public class InvoiceSeederForDev implements ApplicationRunner {

    private static final UUID USER_ID =
            UUID.fromString("11111111-1111-1111-1111-111111111111");

    private final InvoiceRepository invoiceRepository;
    private final LegalServiceRepository legalServiceRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        this.seed();
    }

    private void seed() {
        log.warn("------- Initial Load of Test Invoices -----------");

        List<InvoiceEntity> invoices = List.of(
                this.createInvoice(
                        "b1234567-bbbb-cccc-dddd-eeeeffff0000",
                        "TEST-INV-001", 0, true,
                        LegalServiceSeederForDev.ID_0),
                this.createInvoice(
                        "b1234567-bbbb-cccc-dddd-eeeeffff0001",
                        "TEST-INV-002", 1, true,
                        LegalServiceSeederForDev.ID_0),
                this.createInvoice(
                        "b1234567-bbbb-cccc-dddd-eeeeffff0002",
                        "TEST-INV-003", 2, false,
                        LegalServiceSeederForDev.ID_0),
                this.createInvoice(
                        "b1234567-bbbb-cccc-dddd-eeeeffff0003",
                        "TEST-INV-004", 3, true,
                        LegalServiceSeederForDev.ID_1),
                this.createInvoice(
                        "b1234567-bbbb-cccc-dddd-eeeeffff0004",
                        "TEST-INV-005", 4, false,
                        LegalServiceSeederForDev.ID_1),
                this.createInvoice(
                        "b1234567-bbbb-cccc-dddd-eeeeffff0005",
                        "TEST-INV-006", 5, false,
                        LegalServiceSeederForDev.ID_3)
        );

        List<InvoiceEntity> newInvoices = invoices.stream()
                .filter(invoice -> !this.invoiceRepository.existsById(invoice.getId()))
                .toList();

        this.invoiceRepository.saveAll(newInvoices);

        log.warn("        ------- invoices: {} added", newInvoices.size());
    }

    private InvoiceEntity createInvoice(
            String id,
            String invoiceNumber,
            int daysAfterStart,
            boolean paid,
            UUID serviceId) {

        LegalServiceEntity service = this.legalServiceRepository.findById(serviceId)
                .orElseThrow(() -> new IllegalStateException(
                        "Legal service not found: " + serviceId));

        return InvoiceEntity.builder()
                .id(UUID.fromString(id))
                .invoiceNumber(invoiceNumber)
                .issueDate(LocalDate.of(2026, 1, 1).plusDays(daysAfterStart))
                .taxableBase(service.getFee())
                .vatRate(new BigDecimal("0.21"))
                .paid(paid)
                .paymentType(PaymentType.CARD)
                .services(List.of(service))
                .userId(USER_ID)
                .build();
    }
}
