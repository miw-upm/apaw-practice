package es.upm.miw.apaw.adapters.mongodb.warehouse.daos;

import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.MovementOrderEntity;
import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.OrderDetailEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class MovementOrderRepositoryIT {

    @Autowired
    private MovementOrderRepository movementOrderRepository;

    @Test
    void testFindAll() {
        assertThat(this.movementOrderRepository.findAll())
                .anySatisfy(order -> {
                    assertThat(order.getUserId())
                            .isEqualTo(UUID.fromString("eeeeeeee-ffff-aaaa-bbbb-ccccdddd0001"));
                    assertThat(order.getRegistrationDate()).isNotNull();
                    assertThat(order.getOrderDetailEntities()).hasSize(2);
                    OrderDetailEntity detail = order.getOrderDetailEntities().getFirst();
                    assertThat(detail.getProductItemEntity().getBarcode()).isIn("PI-001", "PI-002");
                    assertThat(detail.getQtyRequested()).isGreaterThan(0);
                });
    }

}