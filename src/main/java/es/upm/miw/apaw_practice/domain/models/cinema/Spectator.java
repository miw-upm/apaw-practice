package es.upm.miw.apaw_practice.domain.models.cinema;

import java.time.LocalDate;

public class Spectator {
    private String idCard;
    private String name;
    private String familyName;
    private LocalDate registrationDate;

    public Spectator() {
        //empty for framework
    }

    public Spectator(String idCard, String name, String familyName, LocalDate registrationDate) {
        this.idCard = idCard;
        this.name = name;
        this.familyName = familyName;
        this.registrationDate = registrationDate;
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

    @Override
    public String toString() {
        return "Spectator{" +
                "idCard='" + idCard + '\'' +
                ", name='" + name + '\'' +
                ", familyName='" + familyName + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
