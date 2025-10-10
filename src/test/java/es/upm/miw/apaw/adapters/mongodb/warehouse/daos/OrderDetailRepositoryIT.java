package es.upm.miw.apaw.adapters.mongodb.warehouse.daos;

import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.OrderDetailEntity;
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
class OrderDetailRepositoryIT {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    void testFindAll() {
        List<OrderDetailEntity> details = this.orderDetailRepository.findAll();
        assertThat(details).isNotEmpty();
        assertThat(details).hasSize(3);
    }

    @Test
    void testFindById() {
        UUID id = UUID.fromString("bbbb3333-aaaa-bbbb-cccc-000000000001");
        Optional<OrderDetailEntity> detail = this.orderDetailRepository.findById(id);
        assertTrue(detail.isPresent());
        assertThat(detail.get().getQtyRequested()).isEqualTo(10);
        assertThat(detail.get().getProductItemEntity().getBarcode()).isEqualTo("PROD-9001");
    }

}
