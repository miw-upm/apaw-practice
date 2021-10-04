package es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class RoomEntity {
    @Id
    private Integer numberRoom;
    private BigDecimal priceRoom;
    private Boolean vip;
    @DBRef
    private List<HotelGuestEntity> hotelGuestsEntities;

    public RoomEntity() {
        //empty for framework
    }

    public RoomEntity(Integer numberRoom, BigDecimal priceRoom, Boolean vip, List<HotelGuestEntity> hotelGuestsEntities) {
        this.numberRoom = numberRoom;
        this.priceRoom = priceRoom;
        this.vip = vip;
        this.hotelGuestsEntities = hotelGuestsEntities;
    }

    public List<HotelGuestEntity> getHotelGuestEntities() {
        return hotelGuestsEntities;
    }

    public Integer getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(Integer numberRoom) {
        this.numberRoom = numberRoom;
    }

    public BigDecimal getPriceRoom() {
        return priceRoom;
    }

    public void setPriceRoom(BigDecimal priceRoom) {
        this.priceRoom = priceRoom;
    }

    public Boolean isVip() {
        return vip;
    }

    public void setVip(Boolean vip) {
        this.vip = vip;
    }

    public List<HotelGuestEntity> getHotelGuestsEntities() {
        return hotelGuestsEntities;
    }

    public void setHotelGuests(List<HotelGuestEntity> hotelGuestsEntities) {
        this.hotelGuestsEntities = hotelGuestsEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomEntity that = (RoomEntity) o;
        return numberRoom.equals(that.numberRoom) && priceRoom.equals(that.priceRoom) && vip.equals(that.vip) && Objects.equals(hotelGuestsEntities, that.hotelGuestsEntities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberRoom, priceRoom, vip, hotelGuestsEntities);
    }

    @Override
    public String toString() {
        return "RoomEntity{" +
                "numberRoom=" + numberRoom +
                ", priceRoom=" + priceRoom +
                ", vip=" + vip +
                ", hotelGuestsEntities=" + hotelGuestsEntities +
                '}';
    }
}
