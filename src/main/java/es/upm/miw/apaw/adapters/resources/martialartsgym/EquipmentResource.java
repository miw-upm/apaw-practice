package es.upm.miw.apaw.adapters.resources.martialartsgym;

import es.upm.miw.apaw.domain.models.martialartsgym.Equipment;
import es.upm.miw.apaw.domain.services.martialartsgym.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(EquipmentResource.EQUIPMENT)
public class EquipmentResource {

    public static final String EQUIPMENT = "/equipment";

    private final EquipmentService equipmentService;

    @Autowired
    public EquipmentResource(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @PatchMapping("/{barCode}")
    public Equipment updateUnitCost(
            @PathVariable Integer barCode,
            @RequestBody BigDecimal newUnitCost) {
        return this.equipmentService.updateUnitCost(barCode, newUnitCost);
    }

    @PutMapping("/{barCode}")
    public Equipment updateEquipment(
            @PathVariable Integer barCode,
            @RequestBody Equipment equipment) {

        equipment.setBarCode(barCode);

        return this.equipmentService.updateEquipment(equipment);
    }

}
