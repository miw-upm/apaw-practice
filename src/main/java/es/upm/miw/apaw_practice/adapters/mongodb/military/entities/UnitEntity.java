package es.upm.miw.apaw_practice.adapters.mongodb.military.entities;

import es.upm.miw.apaw_practice.domain.models.military.Soldier;
import es.upm.miw.apaw_practice.domain.models.military.Unit;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document
public class UnitEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String branch;
    private String location;
    @DBRef
    private List<SoldierEntity> soldierEntities;

    public UnitEntity() {
        //empty for framework
    }

    public UnitEntity(String name, String branch, String location, List<SoldierEntity> soldierEntities) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.branch = branch;
        this.location = location;
        this.soldierEntities = soldierEntities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<SoldierEntity> getSoldierEntities() {
        return soldierEntities;
    }

    public void setSoldierEntities(List<SoldierEntity> soldierEntities) {
        this.soldierEntities = soldierEntities;
    }

    public Unit toUnit() {
        Unit unit = new Unit();
        BeanUtils.copyProperties(this, unit, "soldierEntities");
        List<Soldier> soldiers = this.soldierEntities.stream()
                .map(SoldierEntity::toSoldier)
                .toList();
        unit.setSoldiers(soldiers);
        return unit;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (name.equals(((UnitEntity) obj).name));
    }

    @Override
    public String toString() {
        return "UnitEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", branch='" + branch + '\'' +
                ", location='" + location + '\'' +
                ", soldierEntities=" + soldierEntities +
                '}';
    }
}
