package es.upm.miw.apaw.domain.services.martialartsgym;

import es.upm.miw.apaw.domain.models.martialartsgym.Equipment;
import es.upm.miw.apaw.domain.persistenceports.martialartsgym.EquipmentPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class EquipmentService {

    private final EquipmentPersistence equipmentPersistence;

    @Autowired
    public EquipmentService(EquipmentPersistence equipmentPersistence) {
        this.equipmentPersistence = equipmentPersistence;
    }

    public Equipment updateUnitCost(Integer barCode, BigDecimal newUnitCost) {
        return this.equipmentPersistence.updateUnitCost(barCode, newUnitCost);
    }

    public Equipment updateEquipment(Equipment equipment) {
        return this.equipmentPersistence.updateEquipment(equipment);
    }

}
