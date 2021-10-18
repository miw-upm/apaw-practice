package es.upm.miw.apaw_practice.domain.models.hotel;

import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.Objects;

public class HotelGuest {
    private String dni;
    private String name;
    private LocalDateTime entryDate;
    private LocalDateTime departureDate;


    public HotelGuest() {
        //empty for framework
    }

    public static HotelGuestBuilders.Dni builder() {
        return new Builder();
    }

    public static HotelGuest ofDni(HotelGuest hotelGuest){
        HotelGuest hotelGuestDto = new HotelGuest();
        hotelGuestDto.setDni(hotelGuest.getDni());
        return hotelGuestDto;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public boolean isNull() {
        return this.name == null
                || this.dni == null
                || this.entryDate == null
                || this.departureDate == null;
    }

    @Override
    public String toString() {
        return "HotelGuest{" +
                "dni='" + dni + '\'' +
                ", name='" + name + '\'' +
                ", entryDate=" + entryDate +
                ", departureDate=" + departureDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelGuest that = (HotelGuest) o;
        return Objects.equals(dni, that.dni) && Objects.equals(name, that.name) && Objects.equals(entryDate, that.entryDate) && Objects.equals(departureDate, that.departureDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni, name, entryDate, departureDate);
    }

    public static class Builder implements HotelGuestBuilders.Dni, HotelGuestBuilders.Name, HotelGuestBuilders.EntryDate, HotelGuestBuilders.DepartureDate, HotelGuestBuilders.Optionals{
        private final HotelGuest hotelGuest;

        public Builder() {
            this.hotelGuest = new HotelGuest();
        }

        @Override
        public HotelGuestBuilders.Name dni(String dni) {
            this.hotelGuest.dni = dni;
            return this;
        }

        @Override
        public HotelGuestBuilders.EntryDate name(String name) {
            this.hotelGuest.name = name;
            return this;
        }

        @Override
        public HotelGuestBuilders.DepartureDate entryDate(LocalDateTime entryDate) {
            this.hotelGuest.entryDate = entryDate;
            return this;
        }

        @Override
        public HotelGuestBuilders.Optionals departureDate(LocalDateTime departureDate) {
            this.hotelGuest.departureDate = departureDate;
            return this;
        }

        @Override
        public HotelGuest build() {
            return this.hotelGuest;
        }
    }
}
