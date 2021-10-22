package es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities;

import es.upm.miw.apaw_practice.domain.models.car_workshop.Car;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Tyre;
import es.upm.miw.apaw_practice.domain.models.car_workshop.TyreSpecification;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
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
    private OwnerEntity owner;
    @DBRef
    private List<TyreSpecificationEntity> tyreSpecsEntities;

    public CarEntity() {
        // empty for framework
    }

    public CarEntity(String licensePlate, Boolean revision,
                     OwnerEntity owner, List<TyreSpecificationEntity> tyreSpecsEntities) {
        this.id = UUID.randomUUID().toString();
        this.licensePlate = licensePlate;
        this.revision = revision;
        this.owner = owner;
        this.tyreSpecsEntities = tyreSpecsEntities;
    }

    public CarEntity(Car car) {
        this.id = UUID.randomUUID().toString();
        BeanUtils.copyProperties(car, this);
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

    public OwnerEntity getOwner() {
        return this.owner;
    }

    public void setOwner(OwnerEntity owner) {
        this.owner = owner;
    }

    public List<TyreSpecificationEntity> getTyreSpecsEntities() {
        return this.tyreSpecsEntities;
    }

    public void setTyreSpecsEntities(List<TyreSpecificationEntity> tyreSpecsEntities) {
        this.tyreSpecsEntities = tyreSpecsEntities;
    }

    public Car toCar() {
        Car car = new Car();
        BeanUtils.copyProperties(this, car);
        car.setRevision(this.revision);
        if (this.owner != null) {
            car.setOwner(this.owner.toOwner());
        }
        if (this.tyreSpecsEntities != null){
            List<TyreSpecification> tyreSpecs = new ArrayList<>();
            for(TyreSpecificationEntity tyreSpecification : this.tyreSpecsEntities){
                tyreSpecs.add(tyreSpecification.toTyreSpecification());
            }
            car.setTyreSpecs(tyreSpecs);
        }
        return car;
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
                ", ownerEntity=" + this.owner.toString() +
                ", tyreSpecsEntities=" + this.tyreSpecsEntities.toString() +
                '}';
    }


}
