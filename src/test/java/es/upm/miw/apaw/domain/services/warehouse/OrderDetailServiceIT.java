package es.upm.miw.apaw.domain.services.warehouse;

import es.upm.miw.apaw.adapters.mongodb.warehouse.persistence.OrderDetailPersistenceMongodb;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class OrderDetailServiceIT {

    @Autowired
    private OrderDetailPersistenceMongodb orderDetailPersistence;

    @Test
    void testSumUnitCostDistinctByPosition() {
        String position = "A1";

        BigDecimal result = this.orderDetailPersistence.sumUnitCostDistinctByPosition(position);

        assertThat(result).isNotNull();
        assertThat(result).isGreaterThan(BigDecimal.ZERO);
    }

    @Test
    void testSumUnitCostDistinctByPositionNotFound() {
        String invalidPosition = "Z9";

        assertThrows(RuntimeException.class,
                () -> this.orderDetailPersistence.sumUnitCostDistinctByPosition(invalidPosition));
    }

}
