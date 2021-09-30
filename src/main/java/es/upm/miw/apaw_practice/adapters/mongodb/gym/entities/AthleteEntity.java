package es.upm.miw.apaw_practice.adapters.mongodb.gym.entities;

import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.UUID;

@Document
public class AthleteEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String athleteDni;
    private String athleteName;
    private String atheleFamilyname;
    private String athleteAddress;

    public AthleteEntity() {
        //empty

    }

    public AthleteEntity(String id, String athleteDni, String athleteName, String atheleFamilyname, String athleteAddress) {
        this.id = UUID.randomUUID().toString();
        this.athleteDni = athleteDni;
        this.athleteName = athleteName;
        this.atheleFamilyname = atheleFamilyname;
        this.athleteAddress = athleteAddress;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAthleteDni() {
        return athleteDni;
    }

    public void setAthleteDni(String athleteDni) {
        this.athleteDni = athleteDni;
    }

    public String getAthleteName() {
        return athleteName;
    }

    public void setAthleteName(String athleteName) {
        this.athleteName = athleteName;
    }

    public String getAtheleFamilyname() {
        return atheleFamilyname;
    }

    public void setAtheleFamilyname(String atheleFamilyname) {
        this.atheleFamilyname = atheleFamilyname;
    }

    public String getAthleteAddress() {
        return athleteAddress;
    }

    public void setAthleteAddress(String athleteAddress) {
        this.athleteAddress = athleteAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AthleteEntity that = (AthleteEntity) o;
        return id.equals(that.id) && athleteDni.equals(that.athleteDni) && Objects.equals(athleteName, that.athleteName) && Objects.equals(atheleFamilyname, that.atheleFamilyname) && Objects.equals(athleteAddress, that.athleteAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, athleteDni, athleteName, atheleFamilyname, athleteAddress);
    }

    @Override
    public String toString() {
        return "AthleteEntity{" +
                "id='" + id + '\'' +
                ", athleteDni='" + athleteDni + '\'' +
                ", athleteName='" + athleteName + '\'' +
                ", atheleFamilyname='" + atheleFamilyname + '\'' +
                ", athleteAddress='" + athleteAddress + '\'' +
                '}';
    }
}
