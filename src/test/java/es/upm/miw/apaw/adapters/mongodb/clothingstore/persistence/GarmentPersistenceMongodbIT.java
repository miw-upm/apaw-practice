package es.upm.miw.apaw.adapters.mongodb.clothingstore.persistence;

import es.upm.miw.apaw.adapters.mongodb.DatabaseSeeder;
import es.upm.miw.apaw.adapters.mongodb.clothingstore.daos.GarmentRepository;
import es.upm.miw.apaw.adapters.mongodb.clothingstore.daos.clothingstoreSeeder;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.clothingstore.Garment;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

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

    @MockitoBean
    private UserRestClient userRestClient;

    // 和 clothingstoreSeeder 里 Order.userId 对应
    private static final UUID SEEDED_USER_ID = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000");
    private static final String KNOWN_MOBILE = "666000660";

    @BeforeEach
    void resetDb() {
        clothingstoreSeeder.deleteAll();
        clothingstoreSeeder.seedDatabase();

        UserDto dto = new UserDto();
        dto.setId(SEEDED_USER_ID);
        dto.setMobile(KNOWN_MOBILE);
        BDDMockito.given(userRestClient.readByMobile(KNOWN_MOBILE)).willReturn(dto);
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

        assertThat(garments).extracting(Garment::getSize).contains("S");
    }

    @Test
    void testUpdate() {
        UUID seededId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff7001");

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
        UUID id = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff7001");
        assertThat(garmentRepository.findById(id)).isPresent();
        garmentPersistenceMongodb.delete(id);
        assertThat(garmentRepository.findById(id)).isEmpty();
    }

    @Test
    void testSumDistinctPriceByMobile_ok() {
        BigDecimal result = garmentPersistenceMongodb.sumDistinctPriceByMobile(KNOWN_MOBILE);
        System.out.println(">>> Persistence sumDistinctPriceByMobile(" + KNOWN_MOBILE + ") = " + result);

        // seeder 2 garments: 59.99 + 89.99 = 149.98
        assertThat(result).isEqualByComparingTo("149.98");
    }
}

