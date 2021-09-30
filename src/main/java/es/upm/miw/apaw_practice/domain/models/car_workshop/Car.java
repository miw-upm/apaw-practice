package es.upm.miw.apaw_practice.domain.models.car_workshop;

import java.util.List;
import java.util.Objects;

public class Car {

    private String licensePlate;
    private Boolean revision;
    private Owner owner;
    private List<TyreSpecification> tyreSpecs;

    public Car() {
        // empty for framework
    }

    public Car(String licensePlate, Boolean revision, Owner owner, List<TyreSpecification> tyreSpecs) {
        this.licensePlate = licensePlate;
        this.revision = revision;
        this.owner = owner;
        this.tyreSpecs = tyreSpecs;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public boolean getRevision() {
        return revision;
    }

    public void setRevision(Boolean revision) {
        this.revision = revision;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<TyreSpecification> getTyreSpecs() {
        return tyreSpecs;
    }

    public void setTyreSpecs(List<TyreSpecification> tyreSpecs) {
        this.tyreSpecs = tyreSpecs;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(licensePlate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return Objects.equals(this.licensePlate, ((Car) o).licensePlate);
    }

    @Override
    public String toString() {
        return "Car{" +
                "licensePlate='" + this.licensePlate + '\'' +
                ", needsRevision=" + this.revision +
                ", owner=" + this.owner.toString() +
                ", tyreSpecifications=" + this.tyreSpecs.toString() +
                '}';
    }
}
