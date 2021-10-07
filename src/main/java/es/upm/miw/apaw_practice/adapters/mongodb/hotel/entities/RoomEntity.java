package es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities;

import es.upm.miw.apaw_practice.domain.models.hotel.HotelGuest;
import es.upm.miw.apaw_practice.domain.models.hotel.Room;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RoomEntity {
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


    public Room toRoom() {
        Room room = new Room();
        BeanUtils.copyProperties(this, room, "hotelGuestEntities");
        List<HotelGuest> hotelGuests = this.hotelGuestsEntities.stream()
                .map(HotelGuestEntity::toHotelGuest)
                .collect(Collectors.toList());
        room.setHotelGuests(hotelGuests);
        return room;

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

    public void setHotelGuestsEntities(List<HotelGuestEntity> hotelGuestsEntities) {
        this.hotelGuestsEntities = hotelGuestsEntities;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomEntity that = (RoomEntity) o;
        return Objects.equals(numberRoom, that.numberRoom) && Objects.equals(priceRoom, that.priceRoom) && Objects.equals(vip, that.vip) && Objects.equals(hotelGuestsEntities, that.hotelGuestsEntities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberRoom, priceRoom, vip, hotelGuestsEntities);
    }

}