package es.upm.miw.apaw_practice.adapters.mongodb.gym.entities;

import es.upm.miw.apaw_practice.domain.models.gym.Athlete;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.UUID;

@Document
public class AthleteEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String nie;
    private String name;
    private String familyName;

    public AthleteEntity() {
        //empty

    }


    public AthleteEntity(Athlete athlete) {
        BeanUtils.copyProperties(athlete, this);
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNie() {
        return nie;
    }

    public void setNie(String nie) {
        this.nie = nie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AthleteEntity that = (AthleteEntity) o;
        return nie.equals(that.nie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nie);
    }

    @Override
    public String toString() {
        return "AthleteEntity{" +
                "id='" + id + '\'' +
                ", nie='" + nie + '\'' +
                ", name='" + name + '\'' +
                ", familyName='" + familyName + '\'' +
                '}';
    }
}
