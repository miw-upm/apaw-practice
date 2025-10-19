package es.upm.miw.apaw.domain.services.clothingstore;

import es.upm.miw.apaw.adapters.mongodb.clothingstore.daos.GarmentRepository;
import es.upm.miw.apaw.adapters.mongodb.clothingstore.daos.clothingstoreSeeder;
import es.upm.miw.apaw.domain.exceptions.BadGatewayException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.clothingstore.Garment;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@ActiveProfiles("test")
class GarmentServiceIT {

    @Autowired
    private GarmentService garmentService;

    @Autowired
    private GarmentRepository garmentRepository;

    @Autowired
    private clothingstoreSeeder clothingstoreSeeder;

    @MockitoBean
    private UserRestClient userRestClient;

    // --- 与 user-seeder / clothingstore-seeder 对齐的常量 ---
    private static final UUID USER_ID = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000");
    private static final String KNOWN_MOBILE = "666000660";
    private static final String UNKNOWN_MOBILE = "999999999";

    // 搜索2：已知的发票号与去重后的 Garment id（来自 clothingstoreSeeder）
    private static final String KNOWN_INVOICE_NUMBER = "INV-2025-001";
    private static final UUID G1_ID = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff7001");
    private static final UUID G2_ID = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff7002");

    @BeforeEach
    void resetDb() {
        clothingstoreSeeder.deleteAll();
        clothingstoreSeeder.seedDatabase();

        UserDto mockUser = UserDto.builder()
                .id(USER_ID)
                .mobile(KNOWN_MOBILE)
                .firstName("user0")
                .build();
        given(userRestClient.readByMobile(KNOWN_MOBILE)).willReturn(mockUser);

        given(userRestClient.readByMobile(UNKNOWN_MOBILE))
                .willThrow(new BadGatewayException("User not found with MOBILE: " + UNKNOWN_MOBILE));
    }

    @Test
    void testFindByPriceBetween() {
        List<Garment> garments = this.garmentService
                .findByPriceBetween(new BigDecimal("50"), new BigDecimal("100"))
                .toList();

        assertThat(garments).isNotNull().isNotEmpty();
        assertThat(garments).allSatisfy(g ->
                assertThat(g.getPrice()).isBetween(new BigDecimal("50"), new BigDecimal("100"))
        );
    }

    @Test
    void testUpdate() {
        List<Garment> garments = this.garmentService
                .findByPriceBetween(new BigDecimal("0"), new BigDecimal("1000000"))
                .toList();
        assertThat(garments).isNotNull().isNotEmpty();

        Garment original = garments.getFirst();
        UUID id = original.getId();

        Garment changes = Garment.builder()
                .size(original.getSize())
                .onSale(original.getOnSale() == null ? Boolean.TRUE : !original.getOnSale())
                .price(original.getPrice() == null
                        ? new BigDecimal("15.00")
                        : original.getPrice().add(new BigDecimal("15.00")))
                .build();

        Garment updated = this.garmentService.update(id, changes);

        assertThat(updated).isNotNull();
        assertThat(updated.getId()).isEqualTo(id);
        assertThat(updated.getPrice()).isEqualByComparingTo(changes.getPrice());
        assertThat(updated.getOnSale()).isEqualTo(changes.getOnSale());
        assertThat(updated.getSize()).isEqualTo(changes.getSize());
    }

    @Test
    void testCreate() {
        Garment garment = Garment.builder()
                .size("S")
                .price(new BigDecimal("19.99"))
                .onSale(false)
                .build();

        Garment created = garmentService.create(garment);

        assertThat(created).isNotNull();
        assertThat(created.getId()).isNotNull();
        assertThat(created.getSize()).isEqualTo("S");
        assertThat(created.getPrice()).isEqualByComparingTo("19.99");
        assertThat(created.getOnSale()).isFalse();

        List<Garment> inRange = this.garmentService
                .findByPriceBetween(new BigDecimal("0"), new BigDecimal("20"))
                .toList();
        assertThat(inRange.stream().anyMatch(g -> g.getId().equals(created.getId()))).isTrue();
    }

    @Test
    void testDelete_ok() {
        UUID id = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff7001"); // seeder 中的一件衣服
        assertThat(garmentRepository.findById(id)).isPresent();

        garmentService.delete(id);

        assertThat(garmentRepository.findById(id)).isEmpty();
    }

    @Test
    void testSumDistinctPriceByMobile_ok() {
        BigDecimal total = garmentService.sumDistinctPriceByMobile(KNOWN_MOBILE);
        assertThat(total).isEqualByComparingTo("149.98"); // 59.99 + 89.99
    }

    @Test
    void testSumDistinctPriceByMobile_userNotFound() {
        assertThatThrownBy(() -> garmentService.sumDistinctPriceByMobile(UNKNOWN_MOBILE))
                .isInstanceOf(BadGatewayException.class);
    }

    @Test
    void testFindDistinctIdsByInvoiceNumber_ok() {
        List<UUID> ids = this.garmentService
                .findDistinctIdsByInvoiceNumber(KNOWN_INVOICE_NUMBER); // <-- 没有 .toList()

        assertThat(ids)
                .isNotNull()
                .containsExactlyInAnyOrder(G1_ID, G2_ID);
    }
}



