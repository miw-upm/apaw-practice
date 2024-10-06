package es.upm.miw.apaw_practice.adapters.mongodb.military.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.military.daos.MissionRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.military.daos.UnitRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.military.daos.WeaponRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.military.entities.MissionEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.military.entities.UnitEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.military.entities.WeaponEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.military.Mission;
import es.upm.miw.apaw_practice.domain.models.military.Weapon;
import es.upm.miw.apaw_practice.domain.persistence_ports.military.MissionPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("missionPersistence")
public class MissionPersistenceMongodb implements MissionPersistence {
    private final MissionRepository missionRepository;
    private final UnitRepository unitRepository;
    private final WeaponRepository weaponRepository;

    @Autowired
    public MissionPersistenceMongodb(MissionRepository missionRepository, UnitRepository unitRepository, WeaponRepository weaponRepository) {
        this.missionRepository = missionRepository;
        this.unitRepository = unitRepository;
        this.weaponRepository = weaponRepository;
    }

    @Override
    public Mission update(String codeName, Mission mission) {
        MissionEntity missionEntity = this.missionRepository
                .findByCodeName(codeName)
                .orElseThrow(() -> new NotFoundException("Mission codeName: " + mission.getCodeName()));
        UnitEntity unitEntity = this.unitRepository
                        .findByName(mission.getUnit().getName())
                                .orElseThrow(() -> new NotFoundException("Unit name: " + mission.getUnit().getName()));
        List<String> serialCodes = mission.getWeapons().stream()
                .map(Weapon::getSerialCode)
                .toList();
        List<WeaponEntity> weaponEntities = this.weaponRepository.
                findBySerialCodeIn(serialCodes);
        missionEntity.fromMission(mission.getCodeName(), mission.getInternational(), mission.getStartDate(), unitEntity, weaponEntities);
        return this.missionRepository
                .save(missionEntity)
                .toMission();
    }
}
