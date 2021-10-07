package es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities;

import es.upm.miw.apaw_practice.domain.models.car_workshop.Car;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Document
public class CarEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String licensePlate;
    private Boolean revision;
    @DBRef
    private OwnerEntity ownerEntity;
    @DBRef
    private List<TyreSpecificationEntity> tyreSpecsEntities;

    public CarEntity() {
        // empty for framework
    }

    public CarEntity(String licensePlate, Boolean revision,
                     OwnerEntity ownerEntity, List<TyreSpecificationEntity> tyreSpecsEntities) {
        this.id = UUID.randomUUID().toString();
        this.licensePlate = licensePlate;
        this.revision = revision;
        this.ownerEntity = ownerEntity;
        this.tyreSpecsEntities = tyreSpecsEntities;
    }

    public CarEntity(Car car) {
        this.id = UUID.randomUUID().toString();
        BeanUtils.copyProperties(this, car);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return this.licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Boolean isRevision() {
        return this.revision;
    }

    public void setRevision(Boolean revision) {
        this.revision = revision;
    }

    public OwnerEntity getOwnerEntity() {
        return this.ownerEntity;
    }

    public void setOwnerEntity(OwnerEntity ownerEntity) {
        this.ownerEntity = ownerEntity;
    }

    public List<TyreSpecificationEntity> getTyreSpecsEntities() {
        return this.tyreSpecsEntities;
    }

    public void setTyreSpecsEntities(List<TyreSpecificationEntity> tyreSpecsEntities) {
        this.tyreSpecsEntities = tyreSpecsEntities;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.licensePlate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return Objects.equals(this.licensePlate, ((CarEntity) o).licensePlate);
    }

    @Override
    public String toString() {
        return "CarEntity{" +
                "id='" + this.id + '\'' +
                ", licensePlate='" + this.licensePlate + '\'' +
                ", needsRevision=" + this.revision +
                ", ownerEntity=" + this.ownerEntity.toString() +
                ", tyreSpecsEntities=" + this.tyreSpecsEntities.toString() +
                '}';
    }
}
