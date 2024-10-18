package es.upm.miw.apaw_practice.domain.models.hotel_retired;

import java.time.LocalDateTime;
import java.util.Objects;

public class Guest {

    private String nif;
    private String fullName;
    private LocalDateTime birthDay;

    public Guest() {
        // empty for framework
    }

    public Guest(String nif, String fullName, LocalDateTime birthDay) {
        this.nif = nif;
        this.fullName = fullName;
        this.birthDay = birthDay;
    }

    public static GuestBuilders.Nif builder() {
        return new Builder();
    }

    public static class Builder implements GuestBuilders.Nif, GuestBuilders.FullName, GuestBuilders.BirthDay, GuestBuilders.Optionals {

        private final Guest guest;

        public Builder() {
            this.guest = new Guest();
        }

        @Override
        public GuestBuilders.FullName nif(String nif) {
            this.guest.nif = nif;
            return this;
        }

        @Override
        public GuestBuilders.BirthDay fullName(String fullName) {
            this.guest.fullName = fullName;
            return this;
        }

        @Override
        public GuestBuilders.Optionals birthDay(LocalDateTime birthDay) {
            this.guest.birthDay = birthDay;
            return this;
        }

        @Override
        public Guest build() {
            return this.guest;
        }
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDateTime getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDateTime birthDay) {
        this.birthDay = birthDay;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guest guest = (Guest) o;
        return Objects.equals(nif, guest.nif);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nif);
    }

    @Override
    public String toString() {
        return "Guest{" +
                "nif='" + nif + '\'' +
                ", fullName='" + fullName + '\'' +
                ", birthDay=" + birthDay +
                '}';
    }
}
