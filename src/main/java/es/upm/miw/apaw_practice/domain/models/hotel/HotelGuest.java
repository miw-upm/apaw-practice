package es.upm.miw.apaw_practice.domain.models.hotel;

import java.time.LocalDateTime;

public class HotelGuest {
    private String name;
    private String DNI;
    private Long telephone;
    private LocalDateTime entryDate;
    private LocalDateTime departureDate;

    HotelGuest(){
        //empty for framework
    }

    HotelGuest(String name, String DNI, Long telephone, LocalDateTime entryDate, LocalDateTime departureDate){
        this.name = name;
        this.DNI = DNI;
        this.telephone = telephone;
        this.entryDate = entryDate;
        this.departureDate = departureDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public Long getTelephone() {
        return telephone;
    }

    public void setTelephone(Long telephone) {
        this.telephone = telephone;
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
                "name='" + name + '\'' +
                ", DNI='" + DNI + '\'' +
                ", telephone=" + telephone +
                ", entryDate=" + entryDate +
                ", departureDate=" + departureDate +
                '}';
    }
}
