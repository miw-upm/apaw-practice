package es.upm.miw.apaw.adapters.mongodb.warehouse.daos;


import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.MovementOrderEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        List<MovementOrderEntity> outboundOrders = this.movementOrderRepository.findByTypeOrder("OUTBOUND");
        assertThat(outboundOrders).isNotEmpty();
        assertThat(outboundOrders.get(0).getPartnerName()).isEqualTo("Talleres Omega S.A.");
        assertThat(outboundOrders.get(0).getCompletedOrder()).isTrue();
    }

    @Test
    void testFindByCompletedOrderTrue() {
        List<MovementOrderEntity> completedOrders = this.movementOrderRepository.findByCompletedOrderTrue();
        assertThat(completedOrders).isNotEmpty();
        assertThat(completedOrders).allMatch(order -> order.getCompletedOrder());
    }

    @Test
    void testFindById() {
        UUID id = UUID.fromString("bbbb3333-4444-5555-6666-777788880001");
        Optional<MovementOrderEntity> order = this.movementOrderRepository.findById(id);
        assertTrue(order.isPresent());
        assertThat(order.get().getTypeOrder()).isEqualTo("OUTBOUND");
    }

}
