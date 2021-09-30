package es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document
public class PetEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private Integer chip;
    private Integer age;
    private String nick;
    private String owner;
    private List<DiagnosisEntity> diagnosisEntities;

    public PetEntity() {
        //empty for framework
    }

    public PetEntity(Integer chip, Integer age, String nick, String owner, List<DiagnosisEntity> diagnosisEntities) {
        this.id = UUID.randomUUID().toString();
        this.chip = chip;
        this.age = age;
        this.nick = nick;
        this.owner = owner;
        this.diagnosisEntities = diagnosisEntities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getChip() {
        return chip;
    }

    public void setChip(Integer chip) {
        this.chip = chip;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<DiagnosisEntity> getDiagnosisEntities() {
        return diagnosisEntities;
    }

    public void setDiagnosisEntities(List<DiagnosisEntity> diagnosisEntities) {
        this.diagnosisEntities = diagnosisEntities;
    }

    @Override
    public int hashCode() {
        return chip.hashCode();
    }
}
