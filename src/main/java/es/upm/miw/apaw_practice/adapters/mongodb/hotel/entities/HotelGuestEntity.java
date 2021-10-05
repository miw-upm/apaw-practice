package es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities;

import es.upm.miw.apaw_practice.domain.models.hotel.HotelGuest;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Document
public class HotelGuestEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String dniGuest;
    private String nameGuest;
    private LocalDateTime entryDate;
    private LocalDateTime departureDate;

    public HotelGuestEntity() {
        //empty for framework
    }

    public HotelGuestEntity(HotelGuest hotelGuest) {
        this.id = UUID.randomUUID().toString();
        BeanUtils.copyProperties(hotelGuest, this);
    }

    public HotelGuest toHotelGuest() {
        HotelGuest hotelGuest = new HotelGuest();
        BeanUtils.copyProperties(this, hotelGuest);
        return hotelGuest;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelGuestEntity that = (HotelGuestEntity) o;
        return id.equals(that.id) && dniGuest.equals(that.dniGuest) && nameGuest.equals(that.nameGuest) && entryDate.equals(that.entryDate) && departureDate.equals(that.departureDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dniGuest, nameGuest, entryDate, departureDate);
    }

    @Override
    public String toString() {
        return "HotelGuestEntity{" +
                "id='" + id + '\'' +
                ", dniGuest='" + dniGuest + '\'' +
                ", nameGuest='" + nameGuest + '\'' +
                ", entryDate=" + entryDate +
                ", departureDate=" + departureDate +
                '}';
    }


}
