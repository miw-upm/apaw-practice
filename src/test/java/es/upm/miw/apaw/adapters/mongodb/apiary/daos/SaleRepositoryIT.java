package es.upm.miw.apaw.adapters.mongodb.apiary.daos;

import es.upm.miw.apaw.adapters.mongodb.apiary.entities.SaleEntity;
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
import static org.bson.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ActiveProfiles("test")
class SaleRepositoryIT {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ApiarySeeder apiarySeeder;

    @BeforeEach
    void setUp() {
        this.apiarySeeder.deleteAll();
        this.apiarySeeder.seedDatabase();
    }

    @Test
    void testFindByIdSaleReturnsSale() {
        Optional<SaleEntity> saleOpt = saleRepository.findByIdSale(1);

        assertThat(saleOpt).isPresent();
        SaleEntity sale = saleOpt.get();

        assertThat(sale.getIdSale()).isEqualTo(1);
        assertThat(sale.getAmount()).isEqualByComparingTo(new BigDecimal("11.50"));
        assertThat(sale.getShippingAddress()).isEqualTo("Calle Mayor 10, Madrid");

        assertThat(sale.getProductEntities()).isNotEmpty();
        assertThat(sale.getProductEntities().get(0).getProduct()).isEqualTo("Miel de Romero");
    }

    @Test
    void testFindByIdSaleNotFound() {
        Optional<SaleEntity> saleOpt = saleRepository.findByIdSale(999);
        assertThat(saleOpt).isEmpty();
    }

    @Test
    void testToSaleCoversIfBranches() {
        SaleEntity entityNull = SaleEntity.builder()
                .idSale(1)
                .userId(null)
                .build();
        assertNull(entityNull.toSale().getClient());

        UUID userId = UUID.randomUUID();
        SaleEntity entityNotNull = SaleEntity.builder()
                .idSale(2)
                .userId(userId)
                .build();
        assertNotNull(entityNotNull.toSale().getClient());
        assertEquals(userId, entityNotNull.toSale().getClient().getId());
    }

    @Test
    void testSaleEntityConstructorCoversIfBranches() {
        Sale saleNull = Sale.builder()
                .idSale(1)
                .client(null)
                .build();
        SaleEntity entityNull = new SaleEntity(saleNull);
        assertNull(entityNull.getUserId());

        UUID userId = UUID.randomUUID();
        Sale saleNotNull = Sale.builder()
                .idSale(2)
                .client(UserDto.builder()
                        .id(userId)
                        .mobile("600000000")
                        .firstName("NombreTest")
                        .build())
                .build();
        SaleEntity entityNotNull = new SaleEntity(saleNotNull);
        assertEquals(userId, entityNotNull.getUserId());
    }
}
