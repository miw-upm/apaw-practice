package es.upm.miw.apaw.adapters.mongodb.apiary.daos;

import es.upm.miw.apaw.adapters.mongodb.apiary.entities.SaleEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

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


}
