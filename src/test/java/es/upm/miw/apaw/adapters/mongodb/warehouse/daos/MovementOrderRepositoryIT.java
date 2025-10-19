package es.upm.miw.apaw.adapters.mongodb.warehouse.daos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

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
                            .isEqualTo(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"));
                    assertThat(order.getRegistrationDate()).isNotNull();
                    assertThat(order.getOrderDetailEntities()).hasSize(2);
                });
    }

}