package es.upm.miw.apaw_practice.domain.models.hotel;

import java.math.BigDecimal;
import java.util.List;

public class Room {
    private Integer numberRoom;
    private BigDecimal priceRoom;
    private Boolean isVip;
    private List<HotelGuest> hotelGuests;

    Room() {
        //Empty for framework
    }

    Room(Integer numberRoom, BigDecimal priceRoom,Boolean isVip,  List<HotelGuest> hotelGuests) {
        this.numberRoom = numberRoom;
        this.priceRoom = priceRoom;
        this.isVip = isVip;
        this.hotelGuests = hotelGuests;
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

    public Boolean getVip() {
        return isVip;
    }

    public void setVip(Boolean vip) {
        isVip = vip;
    }

    public List<HotelGuest> getHotelGuests() {
        return hotelGuests;
    }

    public void setHotelGuests(List<HotelGuest> hotelGuests) {
        this.hotelGuests = hotelGuests;
    }

    @Override
    public String toString() {
        return "Room{" +
                "numberRoom=" + numberRoom +
                ", priceRoom=" + priceRoom +
                ", isVip=" + isVip +
                ", hotelGuests=" + hotelGuests +
                '}';
    }
}
