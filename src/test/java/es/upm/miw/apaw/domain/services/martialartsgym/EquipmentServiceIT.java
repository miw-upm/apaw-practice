package es.upm.miw.apaw.domain.services.martialartsgym;

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
}
