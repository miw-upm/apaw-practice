package es.upm.miw.apaw.adapters.mongodb.clothingstore.persistence;

import es.upm.miw.apaw.adapters.mongodb.clothingstore.daos.clothingstoreSeeder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class OrderPersistenceMongodbIT {

    @Autowired
    private OrderPersistenceMongodb orderPersistenceMongodb;

    @Autowired
    private clothingstoreSeeder clothingstoreSeeder;

    private static final String KNOWN_INVOICE_NUMBER = "INV-2025-001";
    private static final UUID G1_ID = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff7001");
    private static final UUID G2_ID = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff7002");

    @BeforeEach
    void resetDb() {
        clothingstoreSeeder.deleteAll();
        clothingstoreSeeder.seedDatabase();
    }

    @Test
    void testFindDistinctGarmentIdsByInvoiceNumber_ok() {
        List<UUID> ids = this.orderPersistenceMongodb
                .findDistinctGarmentIdsByInvoiceNumber(KNOWN_INVOICE_NUMBER)
                .toList();

        assertThat(ids).containsExactlyInAnyOrder(G1_ID, G2_ID);
    }
}
