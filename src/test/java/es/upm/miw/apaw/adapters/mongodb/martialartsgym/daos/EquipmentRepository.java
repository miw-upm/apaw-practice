package es.upm.miw.apaw.adapters.mongodb.martialartsgym.persistence;

import es.upm.miw.apaw.adapters.mongodb.martialartsgym.daos.EquipmentRepository;
import es.upm.miw.apaw.adapters.mongodb.martialartsgym.entities.EquipmentEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class EquipmentPersistenceMongodbIT {

    @Autowired
    private EquipmentPersistenceMongodb equipmentPersistence;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Test
    void testUpdateUnitCost() {
        EquipmentEntity entity = EquipmentEntity.builder()
                .barCode(1002)
                .itemLabel("Punching Bag")
                .unitCost(new BigDecimal("60.00"))
                .build();
        equipmentRepository.save(entity);

        var updated = equipmentPersistence.updateUnitCost(1002, new BigDecimal("75.00"));
        assertThat(updated.getUnitCost()).isEqualByComparingTo(new BigDecimal("75.00"));
    }
}
