package es.upm.miw.apaw_practice.domain.models.car_workshop;

import java.util.List;
import java.util.Objects;

public class Car {

    private String licensePlate;
    private boolean needsRevision;
    private Owner owner;
    private List<Tyre> tyres;

    public Car() {
        // empty for framework
    }

    public Car(String licensePlate, boolean needsRevision, Owner owner, List<Tyre> tyres) {
        this.licensePlate = licensePlate;
        this.needsRevision = needsRevision;
        this.owner = owner;
        this.tyres = tyres;
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

    public void setNeedsRevision(boolean needsRevision) {
        this.needsRevision = needsRevision;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Tyre> getTyres() {
        return tyres;
    }

    public void setTyres(List<Tyre> tyres) {
        this.tyres = tyres;
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
                ", needsRevision=" + this.needsRevision +
                ", owner=" + this.owner.toString() +
                ", tyres=" + this.tyres.toString() +
                '}';
    }
}
