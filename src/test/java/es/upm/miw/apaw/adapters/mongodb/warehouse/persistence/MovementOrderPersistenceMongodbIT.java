package es.upm.miw.apaw.adapters.mongodb.warehouse.persistence;

import es.upm.miw.apaw.domain.models.warehouse.MovementOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class MovementOrderPersistenceMongodbIT {

    @Autowired
    private MovementOrderPersistenceMongodb movementOrderPersistence;

    @Test
    void testReadById() {
        UUID existingId = UUID.fromString("dddddddd-eeee-ffff-aaaa-bbbbcccc0001");

        Optional<MovementOrder> optionalOrder = this.movementOrderPersistence.readById(existingId);
        assertThat(optionalOrder).isPresent();

        MovementOrder order = optionalOrder.get();
        assertThat(order.getTypeOrder()).isNotBlank();
        assertThat(order.getPartnerName()).isNotBlank();
        assertThat(order.getOrderDetails()).isNotNull().isNotEmpty();
    }

    @Test
    void testFindAll() {
        assertThat(this.movementOrderPersistence.findAll())
                .isNotEmpty()
                .anySatisfy(order -> {
                    assertThat(order.getTypeOrder()).isIn("INBOUND", "OUTBOUND");
                    assertThat(order.getOrderDetails()).isNotEmpty();
                    assertThat(order.getUser()).isNotNull();
                });
    }

}