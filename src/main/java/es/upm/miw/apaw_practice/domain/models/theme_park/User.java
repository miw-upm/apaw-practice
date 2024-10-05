package es.upm.miw.apaw_practice.domain.models.theme_park;

import java.time.LocalDateTime;

public class User {
    private String idMembership;
    private String address;
    private LocalDateTime entranceDate;
    private Boolean oneYearMembership;

    public User() {
        //empty for framework
    }

    public User(String idMembership, String address, LocalDateTime entranceDate, Boolean oneYearMembership) {
        this.idMembership = idMembership;
        this.address = address;
        this.entranceDate = entranceDate;
        this.oneYearMembership = oneYearMembership;
    }

    public String getIdMembership() {
        return idMembership;
    }

    public void setIdMembership(String idMembership) {
        this.idMembership = idMembership;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getEntranceDate() {
        return entranceDate;
    }

    public void setEntranceDate(LocalDateTime entranceDate) {
        this.entranceDate = entranceDate;
    }

    public Boolean getOneYearMembership() {
        return oneYearMembership;
    }

    public void setOneYearMembership(Boolean oneYearMembership) {
        this.oneYearMembership = oneYearMembership;
    }

    @Override
    public String toString() {
        return "User{" +
                "idMembership='" + idMembership + '\'' +
                ", address='" + address + '\'' +
                ", entranceDate='" + entranceDate + '\'' +
                ", oneYearMembership='" + oneYearMembership + '\'' +
                '}';
    }
}
