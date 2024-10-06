package es.upm.miw.apaw_practice.adapters.mongodb.military.entities;

import es.upm.miw.apaw_practice.domain.models.military.Soldier;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Document
public class SoldierEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String identityDocument;
    private String fullName;
    private String rank;
    private LocalDate birthDate;

    public SoldierEntity() {
        //empty for framework
    }

    public SoldierEntity(Soldier soldier) {
        BeanUtils.copyProperties(soldier, this);
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentityDocument() {
        return identityDocument;
    }

    public void setIdentityDocument(String identityDocument) {
        this.identityDocument = identityDocument;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Soldier toSoldier() {
        Soldier soldier = new Soldier();
        BeanUtils.copyProperties(this, soldier);
        return soldier;
    }

    @Override
    public int hashCode() {
        return identityDocument.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (identityDocument.equals(((SoldierEntity) obj).identityDocument));
    }

    @Override
    public String toString() {
        return "SoldierEntity{" +
                "id='" + id + '\'' +
                ", identityDocument='" + identityDocument + '\'' +
                ", fullName='" + fullName + '\'' +
                ", rank='" + rank + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
