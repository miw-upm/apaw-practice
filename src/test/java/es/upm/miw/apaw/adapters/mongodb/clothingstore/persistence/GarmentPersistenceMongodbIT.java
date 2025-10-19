package es.upm.miw.apaw.adapters.mongodb.clothingstore.persistence;

import es.upm.miw.apaw.adapters.mongodb.clothingstore.daos.GarmentRepository;
import es.upm.miw.apaw.adapters.mongodb.clothingstore.daos.clothingstoreSeeder;
import es.upm.miw.apaw.domain.models.clothingstore.Garment;
import org.junit.jupiter.api.BeforeEach;
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
class GarmentPersistenceMongodbIT {

    @Autowired
    private GarmentPersistenceMongodb garmentPersistenceMongodb;

    @Autowired
    private GarmentRepository garmentRepository;

    @Autowired
    private clothingstoreSeeder clothingstoreSeeder;

    private static final String KNOWN_MOBILE = "666000660";
    private static final String KNOWN_INVOICE_NUMBER = "INV-2025-001";
    private static final UUID   G1_ID = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff7001");
    private static final UUID   G2_ID = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff7002");

    @BeforeEach
    void resetDb() {
        clothingstoreSeeder.deleteAll();
        clothingstoreSeeder.seedDatabase();
    }

    @Test
    void testFindByPriceBetween() {
        BigDecimal min = new BigDecimal("50.00");
        BigDecimal max = new BigDecimal("100.00");

        List<Garment> garments = this.garmentPersistenceMongodb.findByPriceBetween(min, max)
                .toList();

        assertThat(garments).isNotNull().isNotEmpty();
        assertThat(garments).allSatisfy(g ->
                assertThat(g.getPrice()).isBetween(min, max)
        );
    }

    @Test
    void testCreate() {
        Garment newGarment = Garment.builder()
                .size("S")
                .price(new BigDecimal("19.99"))
                .onSale(false)
                .build();

        Garment created = this.garmentPersistenceMongodb.create(newGarment);

        assertThat(created).isNotNull();
        assertThat(created.getId()).isNotNull();
        assertThat(created.getSize()).isEqualTo("S");
        assertThat(created.getPrice()).isEqualByComparingTo("19.99");
        assertThat(created.getOnSale()).isFalse();

        List<Garment> garments = this.garmentPersistenceMongodb
                .findByPriceBetween(new BigDecimal("10"), new BigDecimal("30"))
                .toList();

        assertThat(garments).extracting(Garment::getId).contains(created.getId());
    }

    @Test
    void testUpdate() {
        UUID seededId = G1_ID;

        Garment body = Garment.builder()
                .size("XL")
                .price(new BigDecimal("129.99"))
                .onSale(true)
                .build();

        Garment updated = this.garmentPersistenceMongodb.update(seededId, body);

        assertThat(updated).isNotNull();
        assertThat(updated.getId()).isEqualTo(seededId);
        assertThat(updated.getSize()).isEqualTo("XL");
        assertThat(updated.getPrice()).isEqualByComparingTo("129.99");
        assertThat(updated.getOnSale()).isTrue();
    }

    @Test
    void testDelete_ok() {
        UUID id = G1_ID;
        assertThat(garmentRepository.findById(id)).isPresent();

        garmentPersistenceMongodb.delete(id);

        assertThat(garmentRepository.findById(id)).isEmpty();
    }

    @Test
    void testSumDistinctPriceByMobile_ok() {
        BigDecimal result = garmentPersistenceMongodb.sumDistinctPriceByMobile(KNOWN_MOBILE);
        System.out.println(">>> Persistence sumDistinctPriceByMobile(" + KNOWN_MOBILE + ") = " + result);

        //  59.99 + 89.99 = 149.98
        assertThat(result).isEqualByComparingTo("149.98");
    }

    @Test
    void testFindDistinctIdsByInvoiceNumber_ok() {
        List<UUID> ids = garmentPersistenceMongodb
                .findDistinctIdsByInvoiceNumber(KNOWN_INVOICE_NUMBER)
                .toList();

        assertThat(ids).containsExactlyInAnyOrder(G1_ID, G2_ID);
    }
}


