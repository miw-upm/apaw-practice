package es.upm.miw.apaw.adapters.mongodb.martialartsgym.persistence;

import es.upm.miw.apaw.adapters.mongodb.martialartsgym.daos.EquipmentRepository;
import es.upm.miw.apaw.adapters.mongodb.martialartsgym.entities.EquipmentEntity;
import es.upm.miw.apaw.domain.models.martialartsgym.Equipment;
import org.junit.jupiter.api.BeforeEach;
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

    private Integer existingBarCode;

    @BeforeEach
    void setUp() {
        this.equipmentRepository.deleteAll();

        EquipmentEntity entity = EquipmentEntity.builder()
                .barCode(1005)
                .itemLabel("Boxing Gloves")
                .unitCost(new BigDecimal("30.00"))
                .build();

        this.equipmentRepository.save(entity);
        this.existingBarCode = entity.getBarCode();
    }

    @Test
    void testUpdateUnitCost() {
        BigDecimal newCost = new BigDecimal("50.00");

        Equipment updated = this.equipmentPersistence.updateUnitCost(existingBarCode, newCost);

        assertThat(updated).isNotNull();
        assertThat(updated.getBarCode()).isEqualTo(existingBarCode);
        assertThat(updated.getUnitCost()).isEqualByComparingTo(newCost);


        EquipmentEntity savedEntity = this.equipmentRepository.findById(existingBarCode).orElseThrow();
        assertThat(savedEntity.getUnitCost()).isEqualByComparingTo(newCost);
    }

    @Test
    void testUpdateUnitCostNotFound() {
        Integer nonExistentBarCode = 9999;
        BigDecimal newCost = new BigDecimal("60.00");

        try {
            this.equipmentPersistence.updateUnitCost(nonExistentBarCode, newCost);
        } catch (RuntimeException ex) {
            assertThat(ex.getMessage()).contains("Equipment not found");
        }
    }
}
