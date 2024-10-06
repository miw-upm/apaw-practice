package es.upm.miw.apaw_practice.adapters.mongodb.military.entities;

import es.upm.miw.apaw_practice.domain.models.military.Mission;
import es.upm.miw.apaw_practice.domain.models.military.Unit;
import es.upm.miw.apaw_practice.domain.models.military.Weapon;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Document
public class MissionEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String codeName;
    private Boolean international;
    private LocalDate startDate;
    @DBRef
    private UnitEntity unitEntity;
    @DBRef
    private List<WeaponEntity> weaponEntities;

    public MissionEntity() {
        //empty for framework
    }

    public MissionEntity(String codeName, Boolean international, LocalDate startDate, UnitEntity unitEntity, List<WeaponEntity> weaponEntities) {
        this.id = UUID.randomUUID().toString();
        this.codeName = codeName;
        this.international = international;
        this.startDate = startDate;
        this.unitEntity = unitEntity;
        this.weaponEntities = weaponEntities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public Boolean getInternational() {
        return international;
    }

    public void setInternational(Boolean international) {
        this.international = international;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public UnitEntity getUnitEntity() {
        return unitEntity;
    }

    public void setUnitEntity(UnitEntity unitEntity) {
        this.unitEntity = unitEntity;
    }

    public List<WeaponEntity> getWeaponEntities() {
        return weaponEntities;
    }

    public void setWeaponEntities(List<WeaponEntity> weaponEntities) {
        this.weaponEntities = weaponEntities;
    }

    public void fromMission(String codeName, Boolean international, LocalDate startDate, UnitEntity unitEntity, List<WeaponEntity> weaponEntities) {
        this.codeName = codeName;
        this.international = international;
        this.startDate = startDate;
        this.unitEntity = unitEntity;
        this.weaponEntities = weaponEntities;
    }

    public Mission toMission() {
        Mission mission = new Mission();
        BeanUtils.copyProperties(this, mission, "unitEntity, weaponEntities");
        Unit unit = this.unitEntity.toUnit();
        List<Weapon> weapons = this.weaponEntities.stream()
                .map(WeaponEntity::toWeapon)
                .toList();
        mission.setUnit(unit);
        mission.setWeapons(weapons);
        return mission;
    }
    @Override
    public int hashCode() {
        return codeName.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && codeName.equals(((MissionEntity) obj).codeName);
    }

    @Override
    public String toString() {
        return "MissionEntity{" +
                "id='" + id + '\'' +
                ", codeName='" + codeName + '\'' +
                ", international=" + international +
                ", startDate=" + startDate +
                ", unitEntity=" + unitEntity +
                ", weaponEntities=" + weaponEntities +
                '}';
    }
}
