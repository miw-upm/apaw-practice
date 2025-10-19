package es.upm.miw.apaw.adapters.mongodb.martialartsgym.persistence;

import es.upm.miw.apaw.adapters.mongodb.martialartsgym.daos.EquipmentRepository;
import es.upm.miw.apaw.adapters.mongodb.martialartsgym.entities.ClassSessionEntity;
import es.upm.miw.apaw.adapters.mongodb.martialartsgym.entities.EquipmentEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.martialartsgym.Equipment;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import es.upm.miw.apaw.domain.persistenceports.martialartsgym.EquipmentPersistence;
import es.upm.miw.apaw.adapters.mongodb.martialartsgym.entities.DojoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class EquipmentPersistenceMongodb implements EquipmentPersistence {

    private final EquipmentRepository equipmentRepository;
    @Autowired
    private UserRestClient userRestClient;


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

    @Override
    public Equipment updateEquipment(Equipment equipment) {
        EquipmentEntity entity = this.equipmentRepository.findById(equipment.getBarCode())
                .orElseThrow(() -> new NotFoundException("Equipment not found: " + equipment.getBarCode()));

        entity.setItemLabel(equipment.getItemLabel());
        entity.setUnitCost(equipment.getUnitCost());

        this.equipmentRepository.save(entity);
        return entity.toEquipment();
    }
    @Override
    public List<String> findMobilesByItemLabel(String itemLabel) {
        EquipmentEntity equipment = this.equipmentRepository.findAll().stream()
                .filter(e -> e.getItemLabel().equalsIgnoreCase(itemLabel))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Equipment not found: " + itemLabel));

        DojoEntity dojo = equipment.getDojo();
        if (dojo == null || dojo.getClassSessions() == null) {
            return List.of();
        }

        return dojo.getClassSessions().stream()
                .filter(Objects::nonNull)
                .map(ClassSessionEntity::getAttendeeIds)
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .distinct()
                .map(userRestClient::readById)
                .map(UserDto::getMobile)
                .distinct()
                .collect(Collectors.toList());
    }
}
