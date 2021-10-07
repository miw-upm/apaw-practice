package es.upm.miw.apaw_practice.domain.models.hotel;

import java.math.BigDecimal;
import java.util.List;

public class Room {
    private Integer number;
    private BigDecimal price;
    private Boolean vip;
    private List<HotelGuest> hotelGuests;

    public Room() {
        //Empty for framework
    }

    Room(Integer number, BigDecimal price, Boolean vip, List<HotelGuest> hotelGuests) {
        this.number = number;
        this.price = price;
        this.vip = vip;
        this.hotelGuests = hotelGuests;
    }


    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer numberRoom) {
        this.number = numberRoom;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean isVip() {
        return vip;
    }

    public void setVip(Boolean vip) {
        this.vip = vip;
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
                "number=" + number +
                ", price=" + price +
                ", vip=" + vip +
                ", hotelGuests=" + hotelGuests +
                '}';
    }
}
