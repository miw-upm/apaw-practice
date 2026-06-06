package es.upm.miw.apaw.adapters.mongodb.clothingstore.persistence;

import es.upm.miw.apaw.adapters.mongodb.clothingstore.daos.OrderRepository;
import es.upm.miw.apaw.adapters.mongodb.clothingstore.daos.clothingstoreSeeder;
import es.upm.miw.apaw.domain.models.clothingstore.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class OrderPersistenceMongodbIT {

    private static final UUID SEEDED_ID = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff7010");

    @Autowired
    private OrderPersistenceMongodb orderPersistenceMongodb;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private clothingstoreSeeder clothingstoreSeeder;

    @BeforeEach
    void resetDb() {
        this.clothingstoreSeeder.deleteAll();
        this.clothingstoreSeeder.seedDatabase();
    }

    @Test
    void testReadAll() {
        List<Order> orders = this.orderPersistenceMongodb.readAll().toList();

        assertThat(orders)
                .extracting(Order::getId)
                .contains(SEEDED_ID);
        assertThat(orders)
                .extracting(Order::getStatus)
                .contains("PAID");
    }

    @Test
    void testCreate() {
        Order order = Order.builder()
                .date(LocalDate.of(2026, 1, 10))
                .total(new BigDecimal("39.99"))
                .itemCount(1)
                .status("PENDING")
                .paymentMethod("CASH")
                .build();

        Order created = this.orderPersistenceMongodb.create(order);

        assertThat(created.getId()).isNotNull();
        assertThat(created.getDate()).isEqualTo(LocalDate.of(2026, 1, 10));
        assertThat(created.getTotal()).isEqualByComparingTo("39.99");
        assertThat(created.getItemCount()).isEqualTo(1);
        assertThat(created.getStatus()).isEqualTo("PENDING");
        assertThat(created.getPaymentMethod()).isEqualTo("CASH");
        assertThat(this.orderRepository.findAll())
                .extracting(orderEntity -> orderEntity.getStatus())
                .contains("PENDING");
    }
}
