package es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities;

import es.upm.miw.apaw_practice.domain.models.car_workshop.Owner;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
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
    private Boolean needsRevision;
    private OwnerEntity ownerEntity;
    private List<TyreSpecificationEntity> tyreSpecsEntities;

    public CarEntity() {
        // empty for framework
    }

    public CarEntity(String licensePlate, Boolean needsRevision,
                     OwnerEntity ownerEntity, List<TyreSpecificationEntity> tyreSpecsEntities) {
        this.id = UUID.randomUUID().toString();
        this.licensePlate = licensePlate;
        this.needsRevision = needsRevision;
        this.ownerEntity = ownerEntity;
        this.tyreSpecsEntities = tyreSpecsEntities;
    }

    public String getId() {
        return id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public boolean isNeedsRevision() {
        return needsRevision;
    }

    public void setNeedsRevision(Boolean needsRevision) {
        this.needsRevision = needsRevision;
    }

    public Owner getOwnerEntity() {
        return ownerEntity;
    }

    public void setOwnerEntity(OwnerEntity ownerEntity) {
        this.ownerEntity = ownerEntity;
    }

    public List<TyreSpecificationEntity> getTyreSpecsEntities() {
        return tyreSpecsEntities;
    }

    public void setTyreSpecsEntities(List<TyreSpecificationEntity> tyreSpecsEntities) {
        this.tyreSpecsEntities = tyreSpecsEntities;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(licensePlate);
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
                ", needsRevision=" + this.needsRevision +
                ", ownerEntity=" + this.ownerEntity.toString() +
                ", tyreSpecsEntities=" + this.tyreSpecsEntities.toString() +
                '}';
    }
}
