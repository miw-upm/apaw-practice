package es.upm.miw.apaw.domain.services.martialartsgym;

import es.upm.miw.apaw.adapters.mongodb.martialartsgym.daos.EquipmentRepository;
import es.upm.miw.apaw.adapters.mongodb.martialartsgym.entities.EquipmentEntity;
import es.upm.miw.apaw.domain.models.martialartsgym.Equipment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class EquipmentServiceIT {

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Test
    void testUpdateUnitCostIntegration() {
        EquipmentEntity equipment = EquipmentEntity.builder()
                .barCode(1003)
                .itemLabel("Helmet")
                .unitCost(new BigDecimal("30.00"))
                .build();

        equipmentRepository.save(equipment);

        var updated = equipmentService.updateUnitCost(1003, new BigDecimal("40.00"));
        assertThat(updated.getUnitCost()).isEqualByComparingTo(new BigDecimal("40.00"));
    }

    @Test
    void testFullUpdateEquipmentIntegration() {
        EquipmentEntity entity = EquipmentEntity.builder()
                .barCode(4001)
                .itemLabel("Old Shin Guard")
                .unitCost(new BigDecimal("25.00"))
                .build();
        equipmentRepository.save(entity);

        Equipment updated = Equipment.builder()
                .barCode(4001)
                .itemLabel("New Shin Guard")
                .unitCost(new BigDecimal("45.00"))
                .build();

        Equipment result = equipmentService.updateEquipment(updated);

        assertThat(result.getItemLabel()).isEqualTo("New Shin Guard");
        assertThat(result.getUnitCost()).isEqualByComparingTo("45.00");

        EquipmentEntity persisted = equipmentRepository.findById(4001).orElseThrow();
        assertThat(persisted.getItemLabel()).isEqualTo("New Shin Guard");
        assertThat(persisted.getUnitCost()).isEqualByComparingTo("45.00");
    }

}
