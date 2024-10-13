package es.upm.miw.apaw_practice.adapters.mongodb.car.entities;

import es.upm.miw.apaw_practice.domain.models.car.OwnerCar;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Document
public class OwnerCarEntity {

    @Id
    private String id;
    private String name;

    @Indexed(unique = true)
    private String driverLicense;
    private LocalDate birthDate;

    public OwnerCarEntity() {
        //empty for framework
    }
    public OwnerCarEntity(OwnerCar ownerCar) {
        BeanUtils.copyProperties(ownerCar, this);
        this.id = UUID.randomUUID().toString();
    }

    public OwnerCar toOwnerCar(){
        OwnerCar ownerCar = new OwnerCar();
        BeanUtils.copyProperties(this, ownerCar);
        return ownerCar;
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

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnerCarEntity that = (OwnerCarEntity) o;
        return Objects.equals(driverLicense, that.driverLicense);
    }

    @Override
    public int hashCode() {
        return Objects.hash(driverLicense);
    }

    @Override
    public String toString() {
        return "OwnerClinicEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", driverLicense='" + driverLicense + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}