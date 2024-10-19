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
import java.util.stream.Stream;

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
    public Stream<Mission> readAll() {
        return this.missionRepository.findAll().stream()
                .map(MissionEntity::toMission);
    }

    @Override
    public Mission update(String codeName, Mission mission) {
        MissionEntity missionEntity = this.findMissionEntity(codeName);
        UnitEntity unitEntity = this.findUnitEntity(mission.getUnit().getName());
        List<WeaponEntity> weaponEntities = this.findWeaponEntities(mission.getWeapons());
        missionEntity.fromMission(mission.getCodeName(), mission.getInternational(), mission.getStartDate(), unitEntity, weaponEntities);
        return this.missionRepository
                .save(missionEntity)
                .toMission();
    }

    private MissionEntity findMissionEntity(String codeName) {
        return this.missionRepository
                .findByCodeName(codeName)
                .orElseThrow(() -> new NotFoundException("Mission codeName: " + codeName));
    }

    private UnitEntity findUnitEntity(String name) {
        return this.unitRepository
                .findByName(name)
                .orElseThrow(() -> new NotFoundException("Unit name: " + name));
    }

    private List<WeaponEntity> findWeaponEntities(List<Weapon> weapons) {
        List<String> serialCodes = weapons.stream()
                .map(Weapon::getSerialCode)
                .toList();
        List<WeaponEntity> weaponEntities = this.weaponRepository.
                findBySerialCodeIn(serialCodes);

        if (weaponEntities.size() != serialCodes.size()) {
            List<String> missingWeapons = serialCodes.stream()
                    .filter(serialCode -> weaponEntities.stream()
                            .noneMatch(weaponEntity -> weaponEntity.getSerialCode().equals(serialCode)))
                    .toList();
            throw new NotFoundException("Weapons with serial codes not found: " + String.join(", ", missingWeapons));
        }
        return weaponEntities;
    }
}
