package es.upm.miw.apaw.adapters.mongodb.warehouse.persistence;

import es.upm.miw.apaw.domain.models.warehouse.OrderDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class OrderDetailPersistenceMongodbIT {

    @Autowired
    private OrderDetailPersistenceMongodb orderDetailPersistence;

    @Test
    void testReadAll() {
        List<OrderDetail> orderDetails = this.orderDetailPersistence.readAll().toList();
        assertThat(orderDetails).isNotEmpty();
    }

    @Test
    void testReadById() {
        UUID id = UUID.fromString("bbbb3333-aaaa-bbbb-cccc-000000000001");
        OrderDetail orderDetail = this.orderDetailPersistence.read(id);
        assertThat(orderDetail.getQtyRequested()).isEqualTo(10);
        assertThat(orderDetail.getUnitCost()).isEqualByComparingTo(new BigDecimal("220.45"));
    }

}
