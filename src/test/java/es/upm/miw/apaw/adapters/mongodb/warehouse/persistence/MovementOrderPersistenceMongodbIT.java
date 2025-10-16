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
        Optional<MovementOrder> order = this.movementOrderPersistence
                .readById(UUID.fromString("dddddddd-eeee-ffff-aaaa-bbbbcccc0001"));
        assertThat(order).isPresent();
        assertThat(order.get().getTypeOrder()).isEqualTo("INBOUND");
        assertThat(order.get().getPartnerName()).isEqualTo("Supplier XYZ");
        assertThat(order.get().getOrderDetails()).hasSize(2);
    }

}