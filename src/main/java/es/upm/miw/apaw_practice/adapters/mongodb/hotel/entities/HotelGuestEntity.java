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
    private String dni;
    private String name;
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
        return HotelGuest.builder()
                .dni(this.dni)
                .name(this.name)
                .entryDate(this.entryDate)
                .departureDate(this.departureDate)
                .build();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelGuestEntity that = (HotelGuestEntity) o;
        return id.equals(that.id) && dni.equals(that.dni) && name.equals(that.name) && entryDate.equals(that.entryDate) && departureDate.equals(that.departureDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dni, name, entryDate, departureDate);
    }

    @Override
    public String toString() {
        return "HotelGuestEntity{" +
                "id='" + id + '\'' +
                ", dni='" + dni + '\'' +
                ", name='" + name + '\'' +
                ", entryDate=" + entryDate +
                ", departureDate=" + departureDate +
                '}';
    }


}
