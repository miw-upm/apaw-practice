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
        List<MovementOrderEntity> orders = this.movementOrderRepository.findAll();
        assertThat(orders).isNotEmpty();

        assertThat(orders)
                .anySatisfy(order -> {
                    assertThat(order.getUserId()).isEqualTo(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"));
                    assertThat(order.getRegistrationDate()).isNotNull();
                    assertThat(order.getOrderDetailEntities()).isNotEmpty();

                    OrderDetailEntity detail = order.getOrderDetailEntities().getFirst();
                    assertThat(detail.getProductItemEntity()).isNotNull();
                    assertThat(detail.getProductItemEntity().getBarcode()).isNotBlank();
                    assertThat(detail.getQtyRequested()).isPositive();
                });
    }

}