package es.upm.miw.apaw.adapters.mongodb.clothingstore.persistence;

import es.upm.miw.apaw.adapters.mongodb.DatabaseSeeder;
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
    private DatabaseSeeder databaseSeeder;

    @BeforeEach
    void setUp() {
        // 与老师一致：每次测试前重置并灌入固定基线数据
        this.databaseSeeder.reSeedDatabase();
    }

    @Test
    void testFindByPriceBetween() {
        // 区间覆盖种子中的 59.99 和 89.99
        BigDecimal min = new BigDecimal("50.00");
        BigDecimal max = new BigDecimal("100.00");

        List<Garment> garments = this.garmentPersistenceMongodb.findByPriceBetween(min, max)
                .toList();

        assertThat(garments).isNotNull().isNotEmpty();
        assertThat(garments)
                .allSatisfy(g ->
                        assertThat(g.getPrice()).isBetween(min, max)
                );
    }

    @Test
    void testUpdate() {
        // 选择一条已存在的数据（用固定的种子ID，便于断言）
        UUID seededId = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff7001"); // M / 59.99 / onSale=true

        Garment body = Garment.builder()
                .size("XL")
                .price(new BigDecimal("129.99"))
                .onSale(true)
                .build();

        // 按你当前实现，update 返回更新后的 Garment；若你的签名是 void，则改为先调用再 read/verify
        Garment updated = this.garmentPersistenceMongodb.update(seededId, body);

        assertThat(updated).isNotNull();
        assertThat(updated.getId()).isEqualTo(seededId);
        assertThat(updated.getSize()).isEqualTo("XL");
        assertThat(updated.getPrice()).isEqualByComparingTo("129.99");
        assertThat(updated.getOnSale()).isTrue();
    }
}
