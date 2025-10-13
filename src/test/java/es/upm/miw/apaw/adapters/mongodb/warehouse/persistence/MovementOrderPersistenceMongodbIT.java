package es.upm.miw.apaw.adapters.mongodb.warehouse.persistence;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.warehouse.MovementOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class MovementOrderPersistenceMongodbIT {

    @Autowired
    private MovementOrderPersistenceMongodb movementOrderPersistence;

    @Test
    void testReadAll() {
        List<MovementOrder> orders = this.movementOrderPersistence.readAll().toList();
        assertThat(orders).isNotEmpty();
        assertThat(orders)
                .extracting(MovementOrder::getTypeOrder)
                .contains("INBOUND", "OUTBOUND");
    }

    @Test
    void testReadById() {
        UUID id = UUID.fromString("dddddddd-eeee-ffff-aaaa-bbbbcccc0001");
        MovementOrder order = this.movementOrderPersistence.read(id);
        assertThat(order.getTypeOrder()).isEqualTo("INBOUND");
        assertThat(order.getPartnerName()).isEqualTo("Supplier XYZ");
        assertThat(order.getOrderDetails()).isNotEmpty();
    }

}
