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

    public static SpectatorBuilder.IdCard builder() {return new Builder(); }

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

    public static class Builder implements SpectatorBuilder.IdCard, SpectatorBuilder.Name, SpectatorBuilder.Optionals {

        private final Spectator spectator;

        public Builder() {
            this.spectator = new Spectator();
        }

        @Override
        public SpectatorBuilder.Name idCard(String idCard) {
            this.spectator.idCard = idCard;
            return this;
        }

        @Override
        public SpectatorBuilder.Optionals name(String name) {
            this.spectator.name = name;
            return this;
        }

        @Override
        public SpectatorBuilder.Optionals familyName(String familyName) {
            this.spectator.familyName = familyName;
            return this;
        }

        @Override
        public SpectatorBuilder.Optionals registrationDate(LocalDate registrationDate) {
            this.spectator.registrationDate = registrationDate;
            return this;
        }

        @Override
        public Spectator build() {
            return this.spectator;
        }
    }
}
