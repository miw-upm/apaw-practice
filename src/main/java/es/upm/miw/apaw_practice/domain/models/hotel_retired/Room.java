package es.upm.miw.apaw_practice.domain.models.hotel_retired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Room implements Accomodation {

    private String num;
    private Boolean occupied;
    private int numBeds;
    private BigDecimal price;
    private List<Booking> bookings;

    public Room() {
        // empty for framework
    }

    public Room(String num, Boolean occupied, int numBeds, BigDecimal price, List<Booking> bookings) {
        this.num = num;
        this.occupied = occupied;
        this.numBeds = numBeds;
        this.price = price;
        this.bookings = bookings;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
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

    @Override
    public void add(Accomodation accomodation) {
        // //Do nothing because is a leaf
    }

    @Override
    public void remove(Accomodation accomodation) {
        //Do nothing because is a leaf
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
    public boolean isComposite() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return numBeds == room.numBeds && Objects.equals(num, room.num) && Objects.equals(occupied, room.occupied) && Objects.equals(price, room.price) && Objects.equals(bookings, room.bookings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, occupied, numBeds, price, bookings);
    }

    @Override
    public String toString() {
        return "Room{" +
                "num='" + num + '\'' +
                ", occupied=" + occupied +
                ", numBeds=" + numBeds +
                ", price=" + price +
                ", bookings=" + bookings +
                '}';
    }
}
