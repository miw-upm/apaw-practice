package es.upm.miw.apaw.domain.services.clothingstore;

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
class GarmentServiceIT {

    @Autowired
    private GarmentService garmentService;

    @Autowired
    private DatabaseSeeder databaseSeeder;

    @BeforeEach
    void seed() {
        databaseSeeder.reSeedDatabase();
    }

    @Test
    void testFindByPriceBetween() {
        List<Garment> garments = this.garmentService
                .findByPriceBetween(new BigDecimal("50"), new BigDecimal("100"))
                .toList();

        assertThat(garments).isNotNull();
        // 如果你的 seeder 确保了 [50,100] 内有数据，可以保留下面这句
        // 如果不确定可以先去掉，只断言 not null 和区间
        assertThat(garments).isNotEmpty();

        assertThat(garments).allSatisfy(g ->
                assertThat(g.getPrice()).isBetween(new BigDecimal("50"), new BigDecimal("100"))
        );
    }
    @Test
    void testUpdate() {
        // 先拿一条现有数据
        List<Garment> garments = this.garmentService
                .findByPriceBetween(new BigDecimal("0"), new BigDecimal("1000000"))
                .toList();

        assertThat(garments).isNotNull();
        assertThat(garments).isNotEmpty();

        Garment original = garments.get(0);
        UUID id = original.getId();

        // 准备要修改的对象
        Garment changes = new Garment();
        changes.setSize(original.getSize());
        changes.setOnSale(original.getOnSale() == null ? Boolean.TRUE : !original.getOnSale());
        changes.setPrice(original.getPrice() == null
                ? new BigDecimal("15.00")
                : original.getPrice().add(new BigDecimal("15.00")));

        // 调用 service 的 update 方法
        Garment updated = this.garmentService.update(id, changes);

        //  验证返回的 Garment 对象确实被修改
        assertThat(updated).isNotNull();
        assertThat(updated.getId()).isEqualTo(id);
        assertThat(updated.getPrice()).isEqualByComparingTo(changes.getPrice());
        assertThat(updated.getOnSale()).isEqualTo(changes.getOnSale());
        assertThat(updated.getSize()).isEqualTo(changes.getSize());
    }
}

