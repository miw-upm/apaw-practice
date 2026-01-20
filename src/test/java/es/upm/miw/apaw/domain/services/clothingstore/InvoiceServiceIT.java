package es.upm.miw.apaw.domain.services.clothingstore;

import es.upm.miw.apaw.adapters.mongodb.clothingstore.daos.InvoiceRepository;
import es.upm.miw.apaw.adapters.mongodb.clothingstore.daos.clothingstoreSeeder;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.clothingstore.Invoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class InvoiceServiceIT {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private clothingstoreSeeder clothingstoreSeeder;

    private static final String SEEDED_NUMBER = "INV-2025-001";

    @BeforeEach
    void resetDb() {
        clothingstoreSeeder.deleteAll();
        clothingstoreSeeder.seedDatabase();
    }

    @Test
    void testReadByNumber_ok() {
        Invoice invoice = invoiceService.readByNumber(SEEDED_NUMBER);
        assertThat(invoice).isNotNull();
        assertThat(invoice.getNumber()).isEqualTo(SEEDED_NUMBER);
    }

    @Test
    void testDelete_ok() {
        assertThat(invoiceRepository.findById(SEEDED_NUMBER)).isPresent();

        invoiceService.delete(SEEDED_NUMBER);

        assertThat(invoiceRepository.findById(SEEDED_NUMBER)).isEmpty();
    }

    @Test
    void testDelete_notFound() {
        assertThatThrownBy(() -> invoiceService.delete("INV-9999-000"))
                .isInstanceOf(NotFoundException.class);
    }
}
