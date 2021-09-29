package es.upm.miw.apaw_practice.domain.models.hotel;

import java.math.BigDecimal;
import java.util.List;

public class Room {
    private Integer nbrRoom;
    private BigDecimal priceRoom;
    private List<HotelGuest> hotelGuests;

    Room() {
        //Empty for framework
    }

    Room(Integer nbrRoom, BigDecimal priceRoom,  List<HotelGuest> hotelGuests) {
        this.nbrRoom = nbrRoom;
        this.priceRoom = priceRoom;
        this.hotelGuests = hotelGuests;
    }


    public Integer getNbrRoom() {
        return nbrRoom;
    }

    public void setNbrRoom(Integer nbrRoom) {
        this.nbrRoom = nbrRoom;
    }

    public BigDecimal getPriceRoom() {
        return priceRoom;
    }

    public void setPriceRoom(BigDecimal priceRoom) {
        this.priceRoom = priceRoom;
    }

    public List<HotelGuest> getHotelGuests() {
        return hotelGuests;
    }

    public void setHotelGuests(List<HotelGuest> hotelGuests) {
        this.hotelGuests = hotelGuests;
    }

    public boolean isOccupied(){
        return this.hotelGuests.size() > 0;
    }

    @Override
    public String toString() {
        return "Room{" +
                "nbrRoom=" + nbrRoom +
                ", priceRoom=" + priceRoom +
                ", hotelGuests=" + hotelGuests +
                '}';
    }
}
