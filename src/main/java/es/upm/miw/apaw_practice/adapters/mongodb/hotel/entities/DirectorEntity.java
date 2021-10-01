package es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities;

import es.upm.miw.apaw_practice.domain.models.hotel.Director;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.UUID;

@Document
public class DirectorEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String dniDirector;
    private String email;
    private Integer telephone;

    public DirectorEntity() {
        //empty for framework
    }

    public DirectorEntity(Director director) {
        this.id = UUID.randomUUID().toString();
        BeanUtils.copyProperties(director, this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDniDirector() {
        return dniDirector;
    }

    public void setDniDirector(String dniDirector) {
        this.dniDirector = dniDirector;
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

    @Override
    public int hashCode() {
        return Objects.hash(id, dniDirector, email, telephone);
    }

    @Override
    public String toString() {
        return "DirectorEntity{" +
                "id='" + id + '\'' +
                ", dniDirector='" + dniDirector + '\'' +
                ", email='" + email + '\'' +
                ", telephone=" + telephone +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectorEntity that = (DirectorEntity) o;
        return id.equals(that.id) && dniDirector.equals(that.dniDirector) && Objects.equals(email, that.email) && Objects.equals(telephone, that.telephone);
    }
}


