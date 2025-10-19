package es.upm.miw.apaw.adapters.mongodb.martialartsgym.daos;

import es.upm.miw.apaw.adapters.mongodb.martialartsgym.entities.EquipmentEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class EquipmentRepositoryIT {

    @Autowired
    private EquipmentRepository equipmentRepository;

    private EquipmentEntity savedEntity;

    @BeforeEach
    void setUp() {
        this.equipmentRepository.deleteAll();

        EquipmentEntity entity = EquipmentEntity.builder()
                .barCode(2001)
                .itemLabel("Karate Belt")
                .unitCost(new BigDecimal("15.00"))
                .build();

        this.savedEntity = this.equipmentRepository.save(entity);
    }

    @Test
    void testFindById() {
        Optional<EquipmentEntity> found = this.equipmentRepository.findById(savedEntity.getBarCode());

        assertThat(found).isPresent();
        assertThat(found.get().getItemLabel()).isEqualTo("Karate Belt");
        assertThat(found.get().getUnitCost()).isEqualByComparingTo("15.00");
    }

    @Test
    void testUpdateUnitCostAndSave() {
        savedEntity.setUnitCost(new BigDecimal("25.50"));
        this.equipmentRepository.save(savedEntity);

        EquipmentEntity updated = this.equipmentRepository.findById(savedEntity.getBarCode()).orElseThrow();
        assertThat(updated.getUnitCost()).isEqualByComparingTo("25.50");
    }

    @Test
    void testDelete() {
        this.equipmentRepository.deleteById(savedEntity.getBarCode());
        Optional<EquipmentEntity> deleted = this.equipmentRepository.findById(savedEntity.getBarCode());
        assertThat(deleted).isEmpty();
    }
    @Test
    void testFullUpdateEquipment() {
        EquipmentEntity entity = EquipmentEntity.builder()
                .barCode(3001)
                .itemLabel("Old Headgear")
                .unitCost(new BigDecimal("35.00"))
                .build();
        this.equipmentRepository.save(entity);

        entity.setItemLabel("New Headgear");
        entity.setUnitCost(new BigDecimal("55.00"));
        this.equipmentRepository.save(entity);

        Optional<EquipmentEntity> updated = this.equipmentRepository.findById(3001);

        assertThat(updated).isPresent();
        assertThat(updated.get().getItemLabel()).isEqualTo("New Headgear");
        assertThat(updated.get().getUnitCost()).isEqualByComparingTo("55.00");
    }

}
