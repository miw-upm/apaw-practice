package es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.daos.BookingRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.daos.GuestRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.entities.BookingEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.entities.GuestEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Booking;
import es.upm.miw.apaw_practice.domain.persistence_ports.hotel_retired.BookingPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("bookingPersistence")
public class BookingPersistenceMongodb implements BookingPersistence {

    private final BookingRepository bookingRepository;
    private final GuestRepository guestRepository;

    @Autowired
    public BookingPersistenceMongodb(
            BookingRepository bookingRepository,
            GuestRepository guestRepository
    ) {
        this.bookingRepository = bookingRepository;
        this.guestRepository = guestRepository;
    }

    @Override
    public Stream<Booking> readAll() {
        return this.bookingRepository
                .findAll().stream()
                .map(BookingEntity::toBooking);
    }

    @Override
    public Booking create(Booking booking) {
        GuestEntity guestEntity = this.guestRepository
                .findByNif(booking.getGuest().getNif())
                .orElseThrow(() -> new NotFoundException("Guest nif: " + booking.getGuest().getNif()));
        return this.bookingRepository
                .save(
                        new BookingEntity(
                                booking.getConfirmed(),
                                booking.getDateIn(),
                                booking.getDateOut(),
                                guestEntity)
                )
                .toBooking();
    }

    @Override
    public Booking update(String id, Booking booking) {
        BookingEntity bookingEntity = this.bookingRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Booking id: " + id));
        bookingEntity.fromBooking(booking);
        return this.bookingRepository
                .save(bookingEntity)
                .toBooking();
    }

    @Override
    public Booking read(String id) {
        return this.bookingRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Booking id: " + id))
                .toBooking();
    }

    @Override
    public boolean existsBooking(String id) {
        return this.bookingRepository
                .findById(id)
                .isPresent();
    }
}
