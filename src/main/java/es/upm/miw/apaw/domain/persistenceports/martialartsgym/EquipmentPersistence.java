package es.upm.miw.apaw.domain.persistenceports.martialartsgym;

import es.upm.miw.apaw.domain.models.martialartsgym.Equipment;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.math.BigDecimal;

@Repository
public interface EquipmentPersistence {
    Equipment updateUnitCost(Integer barCode, BigDecimal newUnitCost);
    Equipment updateEquipment(Equipment equipment);
    List<String> findMobilesByItemLabel(String itemLabel);
}
