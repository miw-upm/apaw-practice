package es.upm.miw.apaw_practice.domain.models.theme_park;

import java.time.LocalDateTime;

public class User {
    private String idMembership;
    private String address;
    private LocalDateTime entranceDate;
    private Boolean oneYearMembership;

    public static UserBuilder.IdMembership builder() {
        return new Builder();
    }

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

    public static class Builder implements UserBuilder.IdMembership, UserBuilder.Address, UserBuilder.EntranceDate, UserBuilder.OneYearMembership, UserBuilder.Builder {

        private final User instance;

        private Builder() {
            instance = new User();
        }

        @Override
        public UserBuilder.Address idMembership(String idMembership) {
            instance.idMembership = idMembership;
            return this;
        }

        @Override
        public UserBuilder.EntranceDate address(String address) {
            instance.address = address;
            return this;
        }

        @Override
        public UserBuilder.OneYearMembership entranceDate(LocalDateTime entranceDate) {
            instance.entranceDate = entranceDate;
            return this;
        }

        @Override
        public UserBuilder.Builder oneYearMembership(Boolean oneYearMembership) {
            instance.oneYearMembership = oneYearMembership;
            return this;
        }

        @Override
        public User build() {
            return instance;
        }
    }
}
