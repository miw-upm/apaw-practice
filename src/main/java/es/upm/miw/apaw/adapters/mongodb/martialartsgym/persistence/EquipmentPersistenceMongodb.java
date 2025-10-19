package es.upm.miw.apaw.adapters.mongodb.martialartsgym.persistence;

import es.upm.miw.apaw.adapters.mongodb.martialartsgym.daos.EquipmentRepository;
import es.upm.miw.apaw.adapters.mongodb.martialartsgym.entities.EquipmentEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.martialartsgym.Equipment;
import es.upm.miw.apaw.domain.persistenceports.martialartsgym.EquipmentPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class EquipmentPersistenceMongodb implements EquipmentPersistence {

    private final EquipmentRepository equipmentRepository;

    @Autowired
    public EquipmentPersistenceMongodb(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    public Equipment updateUnitCost(Integer barCode, BigDecimal newUnitCost) {
        EquipmentEntity entity = this.equipmentRepository.findById(barCode)
                .orElseThrow(() -> new NotFoundException("Equipment not found: " + barCode));

        entity.setUnitCost(newUnitCost);
        this.equipmentRepository.save(entity);
        return entity.toEquipment();
    }
}
