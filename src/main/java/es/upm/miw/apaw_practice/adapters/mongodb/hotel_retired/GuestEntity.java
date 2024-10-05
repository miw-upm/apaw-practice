package es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired;

import es.upm.miw.apaw_practice.domain.models.hotel_retired.Guest;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class GuestEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String nif;
    private String fullName;
    private LocalDateTime birthDay;

    public GuestEntity() {
        // empty for framework
    }

    public GuestEntity(String nif, String fullName, LocalDateTime birthDay) {
        this.id = UUID.randomUUID().toString();
        this.nif = nif;
        this.fullName = fullName;
        this.birthDay = birthDay;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void fromGuest(Guest guest) {
        BeanUtils.copyProperties(guest, this);
    }

    public Guest toGuest() {
        Guest guest = new Guest();
        BeanUtils.copyProperties(this, guest);
        return guest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuestEntity that = (GuestEntity) o;
        return Objects.equals(nif, that.nif);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nif);
    }

    @Override
    public String toString() {
        return "GuestEntity{" +
                "id='" + id + '\'' +
                ", nif='" + nif + '\'' +
                ", fullName='" + fullName + '\'' +
                ", birthDay=" + birthDay +
                '}';
    }
}
