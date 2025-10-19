package es.upm.miw.apaw.adapters.mongodb.warehouse.persistence;

import es.upm.miw.apaw.adapters.mongodb.warehouse.daos.LocationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class OrderDetailPersistenceMongodbIT {

    @Autowired
    private OrderDetailPersistenceMongodb orderDetailPersistence;

    @Autowired
    private LocationRepository locationRepository;

    @Test
    void testFindBySumUnitCostDistinctByPosition() {
        String position = "A1";

        BigDecimal result = this.orderDetailPersistence.findBySumUnitCostDistinctByPosition(position);

        assertThat(result).isNotNull();
        assertThat(result).isGreaterThan(BigDecimal.ZERO);
    }

    @Test
    void testFindBySumUnitCostDistinctByPositionNotFound() {
        String invalidPosition = "Z9";

        assertThrows(RuntimeException.class,
                () -> this.orderDetailPersistence.findBySumUnitCostDistinctByPosition(invalidPosition));
    }

}
