package es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities;

import es.upm.miw.apaw_practice.domain.models.cinema.Spectator;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

public class SpectatorEntity {
    private String idCard;
    private String name;
    private String familyName;
    private LocalDate registrationDate;

    public SpectatorEntity(){
        //empty for framework
    }

    public SpectatorEntity(String idCard, String name, String familyName) {
        this.idCard = idCard;
        this.name = name;
        this.familyName = familyName;
        this.registrationDate = LocalDate.now();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
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

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Spectator toSpectator() {
        Spectator spectator = new Spectator();
        BeanUtils.copyProperties(this, spectator);
        return spectator;
    }

    @Override
    public String toString() {
        return "SpectatorEntity{" +
                ", idCard='" + idCard + '\'' +
                ", name='" + name + '\'' +
                ", familyName='" + familyName + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
