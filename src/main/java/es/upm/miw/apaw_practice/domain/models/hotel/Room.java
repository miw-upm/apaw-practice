package es.upm.miw.apaw_practice.domain.models.hotel;

import java.math.BigDecimal;

public class Room {
    private Integer nbrRoom;
    private BigDecimal priceRoom;
    private Boolean isOccupied;

    Room(){
        //Empty for framework
    }

    Room(Integer nbrRoom, BigDecimal priceRoom, Boolean isOccupied){
        this.nbrRoom = nbrRoom;
        this.priceRoom = priceRoom;
        this.isOccupied = isOccupied;
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

    public Boolean getOccupied() {
        return isOccupied;
    }

    public void setOccupied(Boolean occupied) {
        isOccupied = occupied;
    }

    @Override
    public String toString() {
        return "Room{" +
                "nbrRoom=" + nbrRoom +
                ", priceRoom=" + priceRoom +
                ", isOccupied=" + isOccupied +
                '}';
    }
}
