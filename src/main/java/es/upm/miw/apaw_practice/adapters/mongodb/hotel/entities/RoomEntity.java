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
    private Integer number;
    private BigDecimal price;
    private boolean vip;
    @DBRef
    private List<HotelGuestEntity> hotelGuestsEntities;

    public RoomEntity() {
        //empty for framework
    }

    public RoomEntity(Integer number, BigDecimal price, boolean vip, List<HotelGuestEntity> hotelGuestsEntities) {
        this.number = number;
        this.price = price;
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
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
                "number=" + number +
                ", price=" + price +
                ", vip=" + vip +
                ", hotelGuestsEntities=" + hotelGuestsEntities +
                '}';
    }


    @Override
    public int hashCode() {
        return Objects.hash(number, price, vip, hotelGuestsEntities);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomEntity that = (RoomEntity) o;
        return Objects.equals(number, that.number) && Objects.equals(price, that.price) && Objects.equals(vip, that.vip) && Objects.equals(hotelGuestsEntities, that.hotelGuestsEntities);
    }
}