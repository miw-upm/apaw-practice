package es.upm.miw.apaw_practice.domain.models.hotel;

import java.time.LocalDateTime;

public class HotelGuest {
    private String dni;
    private String name;
    private LocalDateTime entryDate;
    private LocalDateTime departureDate;


    public HotelGuest() {
        //empty for framework
    }

    public HotelGuest(String name, String dni, LocalDateTime entryDate, LocalDateTime departureDate) {
        this.name = name;
        this.dni = dni;
        this.entryDate = entryDate;
        this.departureDate = departureDate;
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
}
