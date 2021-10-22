package es.upm.miw.apaw_practice.adapters.mongodb.car_hire.entities;

import es.upm.miw.apaw_practice.domain.models.car_hire.Renter;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class RenterEntity {

    @Id
    private String id;
    private String name;
    @Indexed(unique = true)
    private String dni;
    private Boolean likedCar;

    public RenterEntity() {
        //empty for framework
    }

    public RenterEntity(Renter renter) {
        BeanUtils.copyProperties(renter, this);
        this.id = UUID.randomUUID().toString();
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Boolean getLikedCar() {
        return likedCar;
    }

    public void setLikedCar(Boolean likedCar) {
        this.likedCar = likedCar;
    }

    public Renter toRenter() {
        Renter renter = new Renter();
        BeanUtils.copyProperties(this, renter, "id");
        return renter;
    }

    @Override
    public String toString() {
        return "RenterEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", DNI='" + dni + '\'' +
                ", likedCar=" + likedCar +
                '}';
    }
}
