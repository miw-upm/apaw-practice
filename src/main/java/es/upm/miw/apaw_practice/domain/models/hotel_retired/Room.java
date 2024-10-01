package es.upm.miw.apaw_practice.domain.models.hotel_retired;

import java.math.BigDecimal;
import java.util.Objects;

public class Room {

    private Boolean occupied;
    private int numBeds;
    private Boolean single;
    private BigDecimal price;

    public Room() {
        // empty for framework
    }

    public Room(Boolean occupied, int numBeds, Boolean single, BigDecimal price) {
        this.occupied = occupied;
        this.numBeds = numBeds;
        this.single = single;
        this.price = price;
    }

    public Boolean getSingle() {
        return single;
    }

    public Boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

    public int getNumBeds() {
        return numBeds;
    }

    public void setNumBeds(int numBeds) {
        this.numBeds = numBeds;
    }

    public void setSingle(Boolean single) {
        this.single = single;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return numBeds == room.numBeds && Objects.equals(occupied, room.occupied) && Objects.equals(single, room.single) && Objects.equals(price, room.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(occupied, numBeds, single, price);
    }

    @Override
    public String toString() {
        return "Room{" +
                "occupied=" + occupied +
                ", numBeds=" + numBeds +
                ", single=" + single +
                ", price=" + price +
                '}';
    }
}
