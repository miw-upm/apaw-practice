package es.upm.miw.apaw.adapters.out.roombooking.postgres;

import es.upm.miw.apaw.domain.model.roombooking.Booking;
import es.upm.miw.apaw.domain.ports.out.roombooking.BookingGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookingAdapter implements BookingGateway {

    private final BookingRepository bookingRepository;

    @Override
    @Transactional
    public Booking create(Booking booking) {
        BookingEntity bookingEntity = new BookingEntity(booking);
        BookingEntity saved = this.bookingRepository.save(bookingEntity);
        return saved.toDomain();
    }

    @Override
    public List<UserBookingStat> findUserBookingStats() {
        return this.bookingRepository.findUserBookingStats();
    }
}