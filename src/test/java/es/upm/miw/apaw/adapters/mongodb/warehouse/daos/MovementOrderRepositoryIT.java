package es.upm.miw.apaw.adapters.mongodb.warehouse.daos;

import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.MovementOrderEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class MovementOrderRepositoryIT {

    @Autowired
    private MovementOrderRepository movementOrderRepository;

    @Test
    void testFindAll() {
        List<MovementOrderEntity> orders = this.movementOrderRepository.findAll();
        assertThat(orders).isNotEmpty();
        assertThat(orders).hasSize(2);
    }

    @Test
    void testFindByTypeOrder() {
        List<MovementOrderEntity> inboundOrders = this.movementOrderRepository.findByTypeOrder("INBOUND");
        assertThat(inboundOrders).isNotEmpty();
        MovementOrderEntity order = inboundOrders.get(0);
        assertThat(order.getPartnerName()).isEqualTo("Supplier XYZ");
        assertThat(order.getCompletedOrder()).isTrue();
    }

    @Test
    void testFindByCompletedOrderTrue() {
        List<MovementOrderEntity> completedOrders = this.movementOrderRepository.findByCompletedOrderTrue();
        assertThat(completedOrders).isNotEmpty();
        assertThat(completedOrders)
                .allMatch(MovementOrderEntity::getCompletedOrder);
    }

}
