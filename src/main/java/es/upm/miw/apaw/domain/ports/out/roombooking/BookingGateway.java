package es.upm.miw.apaw.domain.ports.out.roombooking;

import es.upm.miw.apaw.adapters.out.roombooking.postgres.UserBookingStat;
import es.upm.miw.apaw.domain.model.roombooking.Booking;
import es.upm.miw.apaw.domain.model.roombooking.BookingFindCriteria;

import java.util.List;

public interface BookingGateway {
    Booking create(Booking booking);
    List<UserBookingStat> findUserBookingStats();
    List<Booking> find(BookingFindCriteria criteria);
}