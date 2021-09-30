package es.upm.miw.apaw_practice.domain.models.hotel;

import java.time.LocalDateTime;

public class HotelGuest {
    private String dniGuest;
    private String nameGuest;
    private LocalDateTime entryDate;
    private LocalDateTime departureDate;


    HotelGuest() {
        //empty for framework
    }

    HotelGuest(String nameGuest, String dniGuest, LocalDateTime entryDate, LocalDateTime departureDate) {
        this.nameGuest = nameGuest;
        this.dniGuest = dniGuest;
        this.entryDate = entryDate;
        this.departureDate = departureDate;
    }

    public String getDniGuest() {
        return dniGuest;
    }

    public void setDniGuest(String dniGuest) {
        this.dniGuest = dniGuest;
    }

    public String getNameGuest() {
        return nameGuest;
    }

    public void setNameGuest(String nameGuest) {
        this.nameGuest = nameGuest;
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

    @Override
    public String toString() {
        return "HotelGuest{" +
                "dniGuest='" + dniGuest + '\'' +
                ", nameGuest='" + nameGuest + '\'' +
                ", entryDate=" + entryDate +
                ", departureDate=" + departureDate +
                '}';
    }
}
