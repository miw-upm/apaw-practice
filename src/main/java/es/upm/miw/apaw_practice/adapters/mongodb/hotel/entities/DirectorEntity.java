package es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities;

import es.upm.miw.apaw_practice.domain.models.hotel.Director;
import es.upm.miw.apaw_practice.domain.models.hotel.Hotel;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Document
public class DirectorEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String dni;
    private String email;
    private Integer telephone;
    @DBRef
    private List<HotelEntity> hotelEntityList;

    public DirectorEntity() {
        //empty for framework
    }

    public DirectorEntity(String dni, String email, Integer telephone, List<HotelEntity> hotelEntityList) {
        this.id = UUID.randomUUID().toString();
        this.dni = dni;
        this.email = email;
        this.telephone = telephone;
        this.hotelEntityList = hotelEntityList;

    }

    public Director toDirector() {
        Director director = new Director();
        BeanUtils.copyProperties(this, director);
        return director;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    public List<HotelEntity> getHotelEntityList() {
        return hotelEntityList;
    }

    public void setHotelEntityList(List<HotelEntity> hotelEntityList) {
        this.hotelEntityList = hotelEntityList;
    }

    @Override
    public String toString() {
        return "DirectorEntity{" +
                "id='" + id + '\'' +
                ", dni='" + dni + '\'' +
                ", email='" + email + '\'' +
                ", telephone=" + telephone +
                ", hotelEntityList=" + hotelEntityList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectorEntity director = (DirectorEntity) o;
        return Objects.equals(id, director.id) &&
                Objects.equals(dni, director.dni) &&
                Objects.equals(email, director.email) &&
                Objects.equals(telephone, director.telephone) &&
                Objects.equals(hotelEntityList, director.hotelEntityList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dni, email, telephone, hotelEntityList);
    }

}


