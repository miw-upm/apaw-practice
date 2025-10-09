package es.upm.miw.apaw.adapters.mongodb.apiary.persistence;

import es.upm.miw.apaw.adapters.mongodb.apiary.daos.SaleRepository;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.apiary.Sale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class SalePersistenceMongodbIT {

    @Autowired
    private SalePersistenceMongodb salePersistence;

    @Autowired
    private SaleRepository saleRepository;

    @BeforeEach
    void setUp() {
        saleRepository.deleteAll();
    }

    @Test
    void testCreateSale() {
        UserDto client = UserDto.builder()
                .id(UUID.randomUUID())
                .mobile("600123456")
                .firstName("John")
                .build();

        Sale sale = Sale.builder()
                .idSale(1)
                .paymentForm(1) // Ejemplo: 1 = tarjeta
                .shippingAddress("Calle Falsa 123")
                .amount(new BigDecimal("100.50"))
                .client(client)
                .build();

        Sale createdSale = salePersistence.create(sale);

        assertThat(createdSale).isNotNull();
        assertThat(createdSale.getIdSale()).isEqualTo(1);
        assertThat(createdSale.getPaymentForm()).isEqualTo(1);
        assertThat(createdSale.getShippingAddress()).isEqualTo("Calle Falsa 123");
        assertThat(createdSale.getAmount()).isEqualByComparingTo(new BigDecimal("100.50"));

        Optional<?> saleEntity = saleRepository.findByIdSale(1);
        assertThat(saleEntity).isPresent();
    }

    @Test
    void testDeleteSale() {
        UserDto client = UserDto.builder()
                .id(UUID.randomUUID())
                .mobile("600123456")
                .firstName("Alice")
                .build();

        Sale sale = Sale.builder()
                .idSale(2)
                .paymentForm(2) // Ejemplo: 2 = PayPal
                .shippingAddress("Avenida Siempre Viva 742")
                .amount(new BigDecimal("50.00"))
                .client(client)
                .build();

        salePersistence.create(sale);

        assertThat(salePersistence.existIdSale(2)).isTrue();

        salePersistence.delete(2);

        assertThat(salePersistence.existIdSale(2)).isFalse();
    }
}
