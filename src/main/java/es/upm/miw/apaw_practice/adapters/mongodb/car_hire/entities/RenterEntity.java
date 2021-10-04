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
    private String DNI;
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

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public Boolean getLikedCar() {
        return likedCar;
    }

    public void setLikedCar(Boolean likedCar) {
        this.likedCar = likedCar;
    }

    @Override
    public String toString() {
        return "RenterEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", DNI='" + DNI + '\'' +
                ", likedCar=" + likedCar +
                '}';
    }
}
