package es.upm.miw.apaw_practice.domain.models.hotel_retired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Room {

    private int num;
    private Boolean occupied;
    private Boolean single;
    private BigDecimal price;
    private List<Booking> bookings;

    public Room() {
        // empty for framework
    }

    public Room(int num, Boolean occupied, Boolean single, BigDecimal price, List<Booking> bookings) {
        this.num = num;
        this.occupied = occupied;
        this.single = single;
        this.price = price;
        this.bookings = bookings;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

    public Boolean getSingle() {
        return single;
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

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return num == room.num && Objects.equals(occupied, room.occupied) && Objects.equals(single, room.single) && Objects.equals(price, room.price) && Objects.equals(bookings, room.bookings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, occupied, single, price, bookings);
    }

    @Override
    public String toString() {
        return "Room{" +
                "num=" + num +
                ", occupied=" + occupied +
                ", single=" + single +
                ", price=" + price +
                ", bookings=" + bookings +
                '}';
    }
}
