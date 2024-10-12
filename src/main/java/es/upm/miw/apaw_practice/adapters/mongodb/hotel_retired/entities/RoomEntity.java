package es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.entities;

import es.upm.miw.apaw_practice.domain.models.hotel_retired.Booking;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Room;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Document
public class RoomEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String num;
    private boolean occupied;
    private int numBeds;
    private BigDecimal price;
    @DBRef
    private List<BookingEntity> bookingEntities;

    public RoomEntity() {
        // empty for framework
    }

    public RoomEntity(String num, Boolean occupied, int numBeds, BigDecimal price, List<BookingEntity> bookingEntities) {
        this.id = UUID.randomUUID().toString();
        this.num = num;
        this.occupied = occupied;
        this.numBeds = numBeds;
        this.price = price;
        this.bookingEntities = bookingEntities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public int getNumBeds() {
        return numBeds;
    }

    public void setNumBeds(int numBeds) {
        this.numBeds = numBeds;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<BookingEntity> getBookingEntities() {
        return bookingEntities;
    }

    public void setBookingEntities(List<BookingEntity> bookingEntities) {
        this.bookingEntities = bookingEntities;
    }

    public void fromRoom(Room room) {
        BeanUtils.copyProperties(room, this);
    }

    public Room toRoom() {
        Room room = new Room();

        BeanUtils.copyProperties(this, room, "bookingEntities");
        List<Booking> bookings = this.bookingEntities.stream()
                .map(BookingEntity::toBooking)
                .collect(Collectors.toList());
        room.setBookings(bookings);

        return room;
    }
}
