package es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.UUID;

@Document
public class TyreSpecificationEntity {
    @Id
    private String id;
    private Integer width;
    private Integer diameter;
    private String loadSpeedIndex;

    public TyreSpecificationEntity() {
        //empty for framework
    }

    public TyreSpecificationEntity(Integer width, Integer diameter, String loadSpeedIndex) {
        this.id = UUID.randomUUID().toString();
        this.width = width;
        this.diameter = diameter;
        this.loadSpeedIndex = loadSpeedIndex;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getWidth() {
        return this.width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getDiameter() {
        return this.diameter;
    }

    public void setDiameter(Integer diameter) {
        this.diameter = diameter;
    }

    public String getLoadSpeedIndex() {
        return this.loadSpeedIndex;
    }

    public void setLoadSpeedIndex(String loadSpeedIndex) {
        this.loadSpeedIndex = loadSpeedIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.width, this.diameter, this.loadSpeedIndex);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TyreSpecificationEntity tyreSpecEntity = (TyreSpecificationEntity) o;
        return Objects.equals(this.width, tyreSpecEntity.width) &&
                Objects.equals(this.diameter, tyreSpecEntity.diameter) &&
                Objects.equals(this.loadSpeedIndex, tyreSpecEntity.loadSpeedIndex);
    }

    @Override
    public String toString() {
        return "TyreSpecificationEntity{" +
                "id='" + this.id + '\'' +
                ", width=" + this.width +
                ", diameter=" + this.diameter +
                ", loadSpeedIndex='" + this.loadSpeedIndex + '\'' +
                '}';
    }
}
