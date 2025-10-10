package es.upm.miw.apaw.adapters.mongodb.warehouse.persistence;

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
    }

    @Test
    void testReadById() {
        UUID id = UUID.fromString("bbbb3333-4444-5555-6666-777788880001");
        MovementOrder order = this.movementOrderPersistence.read(id);
        assertThat(order.getPartnerName()).isEqualTo("Talleres Omega S.A.");
        assertThat(order.getTypeOrder()).isEqualTo("OUTBOUND");
    }

    @Test
    void testCreateAndDelete() {
        MovementOrder newOrder = MovementOrder.builder()
                .id(UUID.randomUUID())
                .typeOrder("INBOUND")
                .partnerName("Test Partner")
                .partnerAddress("Madrid, España")
                .completedOrder(false)
                .registrationDate(LocalDateTime.now())
                .build();

        MovementOrder created = this.movementOrderPersistence.create(newOrder);
        assertThat(created.getPartnerName()).isEqualTo("Test Partner");

        this.movementOrderPersistence.delete(created.getId());
    }

    @Test
    void testUpdate() {
        UUID id = UUID.fromString("bbbb3333-4444-5555-6666-777788880002");
        MovementOrder order = this.movementOrderPersistence.read(id);
        order.setPartnerAddress("Valencia, España");

        MovementOrder updated = this.movementOrderPersistence.update(id, order);
        assertThat(updated.getPartnerAddress()).isEqualTo("Valencia, España");
    }
}
